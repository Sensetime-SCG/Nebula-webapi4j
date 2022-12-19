package com.sensetime.nebula.webapi4j.lib.base

import com.google.gson.GsonBuilder
import java.nio.charset.Charset
import java.security.KeyFactory
import java.security.Signature
import java.security.spec.PKCS8EncodedKeySpec
import java.util.Base64
import kotlin.text.Charsets.UTF_8


data class JwtHeader(
    val alg: String,
    val typ: String = "JWT"
)

data class JwtPayload(
    val iss: String,
    val iat: Long,
    val exp: Long,
    val userId: String
)

object Jwt {
    private const val verifyAlgorithm = "HmacSHA512"
    private const val tokenSeparate = '.'

    private val gson = GsonBuilder().create()
    private val decoder = Base64.getDecoder()



    /**
     * Decode JWT token.
     * @param token The JWT token to decode as a String.
     * @return JWT token object.
     */
    fun decode(
        token: String
    ): JwtToken? {
        val parts = token.split(tokenSeparate)

        return if (parts.size >= 2) {
            val header = gson.fromJson(decoder.decode(parts[0]).toString(UTF_8), JwtHeader::class.java)
            val payload = gson.fromJson(decoder.decode(parts[1]).toString(UTF_8), JwtPayload::class.java)
            JwtToken(header, payload)
        } else {
            null
        }
    }

    /**
     * Verifies the provided JWT String with the provided JWK object (RSA public key).
     * @param token: The JWK String to validate.
     * @param decoder: Base64 decoder for decoding the JWT signature.
     * @return True if validation was successful, false if not.
     */
    fun verify(token: String, signature: String = ""): Boolean {
        return if (token.isEmpty()) {
            false
        } else {
            val parts = token.split(tokenSeparate)

            if (parts.size == 3) {

                val gson = GsonBuilder().create()
                val decoder = Base64.getDecoder()
                if(signature.isEmpty()){
                    val payload = gson.fromJson(decoder.decode(parts[1]).toString(UTF_8), JwtPayload::class.java)
                    if(payload.exp > (System.currentTimeMillis()/1000)){
                        return true
                    }
                    return false
                }
                val header = parts[0].toByteArray()
                val payload = parts[1].toByteArray()
                val rsaSignature = Signature.getInstance(verifyAlgorithm)
                val tokenSignature = decoder.decode(parts[2])
                rsaSignature.update(header)
                rsaSignature.update(tokenSeparate.code.toByte())
                rsaSignature.update(payload)
                rsaSignature.verify(tokenSignature)
            } else {
                false
            }
        }
    }

    private fun sign(
        algorithm: Algorithm,
        secret: String,
        data: String,
        encoder: Base64Encoder,
        decoder: Base64Decoder,
        charset: Charset
    ): String {

        val factory = KeyFactory.getInstance(algorithm.keyAlg)
        val keySpec = PKCS8EncodedKeySpec(decoder.decode(secret.toByteArray(charset)))
        val key = factory.generatePrivate(keySpec)

        val sig = Signature.getInstance(algorithm.alg)
        sig.initSign(key)
        sig.update(data.toByteArray(charset))

        return encoder.encodeURLSafe(sig.sign())
    }
}

enum class Algorithm(val alg: String, val keyAlg: String) {
    HS256("HmacSHA256",""),
    HS384("HmacSHA384",""),
    HS512("HmacSHA384",""),
    RS256("SHA256withRSA", "RSA"),
    RS384("SHA384withRSA", "RSA"),
    RS512("SHA512withRSA", "RSA"),
    ES256("SHA256withECDSA", "EC"),
    ES384("SHA384withECDSA", "EC"),
    ES512("SHA512withECDSA", "EC"),
    PS256("SHA256WithRSA/PSS", "RSA"),
    PS384("SHA384WithRSA/PSS", "RSA"),
    PS512("SHA512WithRSA/PSS", "RSA"),
}

interface Base64Encoder {
    /**
     * Base64 encodes the provided bytes in an URL safe way.
     * @param bytes The ByteArray to be encoded.
     * @return The encoded String.
     */
    fun encodeURLSafe(bytes: ByteArray): String

    /**
     * Base64 encodes the provided bytes.
     * @param bytes The ByteArray to be encoded.
     * @return The encoded String.
     */
    fun encode(bytes: ByteArray): String
}

interface Base64Decoder {
    /**
     * Base64 encodes the provided bytes.
     * @param bytes The ByteArray to be decoded.
     * @return The decoded bytes.
     */
    fun decode(bytes: ByteArray): ByteArray

    /**
     * Base64 encodes the provided String.
     * @param string The String to be decoded.
     * @return The decoded String as a ByteArray.
     */
    fun decode(string: String): ByteArray
}

data class JwtToken(
    val header: JwtHeader,
    val payload: JwtPayload,
    val signature: ByteArray? = null
)
