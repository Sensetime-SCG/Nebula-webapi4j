package com.sensetime.nebula.webapi4j

import com.sensetime.nebula.webapi4j.lib.api.*
import com.sensetime.nebula.webapi4j.lib.base.*
import com.sensetime.nebula.webapi4j.lib.dto.*
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.KeyStore
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.TrustManagerFactory
import javax.net.ssl.X509TrustManager
import java.time.Duration


class WebApi(
    val serverUrl: String,
    val user: String,
    val password: String,
    val verifySSL: Boolean = false,
    val validTime: Short = 1440
) {

    private lateinit var endPoint: RemoteDataSource

    private var interceptorAuth: InterceptorAuth = InterceptorAuth(refreshCallback = {
        refreshToken()
    })


    private fun refreshToken() {
        runBlocking {
            val retrofit = Retrofit.Builder().baseUrl(serverUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getUnsafeOkHttpClient())
                .build()
            RemoteDataSourceImpl(retrofit.create(EndPoint::class.java)).authLogin(
                RequestAuth(user, password, validTime)
            ).map {
                interceptorAuth.token = it.token
            }.mapError { println("get failed") }
        }
    }

    fun getEndpoint(): RemoteDataSource {
        if (!this::endPoint.isInitialized) {
            val retrofit = getUnsafeOkHttpClient().let {
                if (verifySSL) {
                    Retrofit.Builder()
                        .baseUrl(serverUrl)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                } else {
                    Retrofit.Builder()
                        .baseUrl(serverUrl)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(it)
                        .build()
                }
            }
            endPoint = RemoteDataSourceImpl(retrofit.create(EndPoint::class.java))
        }
        return endPoint
    }

    private fun getUnsafeOkHttpClient(): OkHttpClient {
        return try {
            val trustAllCerts = arrayOf<TrustManager>(
                object : X509TrustManager {
                    @Throws(CertificateException::class)
                    override fun checkClientTrusted(
                        chain: Array<X509Certificate?>?,
                        authType: String?
                    ) {
                    }

                    @Throws(CertificateException::class)
                    override fun checkServerTrusted(
                        chain: Array<X509Certificate?>?,
                        authType: String?
                    ) {
                    }

                    override fun getAcceptedIssuers(): Array<X509Certificate?> {
                        return arrayOf()
                    }
                }
            )

            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, SecureRandom())
            val sslSocketFactory = sslContext.socketFactory
            val trustManagerFactory: TrustManagerFactory =
                TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
            trustManagerFactory.init(null as KeyStore?)
            val trustManagers: Array<TrustManager> =
                trustManagerFactory.trustManagers
            check(!(trustManagers.size != 1 || trustManagers[0] !is X509TrustManager)) {
                "Unexpected default trust managers:" + trustManagers.contentToString()
            }

            val trustManager =
                trustManagers[0] as X509TrustManager


            val builder = OkHttpClient.Builder()
                .sslSocketFactory(sslSocketFactory, trustManager)
                .hostnameVerifier { _, _ -> true }
                .addInterceptor(interceptorAuth)
                .callTimeout(Duration.ofSeconds(300))
                .readTimeout(Duration.ofSeconds(300))
            builder.build()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}