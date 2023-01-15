package com.sensetime.nebula.webapi4j

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.slf4j.LoggerFactory
import java.net.InetAddress
import java.nio.charset.Charset
import java.util.*
import javax.jmdns.JmDNS
import javax.jmdns.ServiceEvent
import javax.jmdns.ServiceInfo
import javax.jmdns.ServiceListener

data class Device(
    val Model: String,
    val SN: String,
    val IPv4: Array<java.net.Inet4Address>,
    val IPv6: Array<java.net.Inet6Address>
)

object Discover {
    private const val deviceFilterType: String = "_http._tcp.local."
    private const val deviceFilterName: String = "sensetime"
    private var devices: Array<Device> = arrayOf()

    private fun generateDevice(info: ServiceInfo): Device? {
        if (info.name == deviceFilterName) {
            val txt = info.textBytes ?: return null
            var pos = 0
            var txtArray: Array<ByteArray> = arrayOf()
            while (pos < txt.size) {
                val len = txt[pos]
                txtArray += Arrays.copyOfRange(txt, pos + 1, pos + 1 + len.toInt())
                pos += (1 + len)
            }
            var sn = ""
            var model = ""
            txtArray.forEach {
                if (model.isEmpty()) {
                    if (it[0].compareTo(109 /* m of ASCII dec */) == 0 && it[1].compareTo(111 /* o of ASCII dec */) == 0) {
                        model = String(Arrays.copyOfRange(it, 5 + 1, it.size - 1), Charset.defaultCharset())
                    }
                }
                if (sn.isEmpty()) {
                    if (it[0].compareTo(115 /* s of ASCII dec */) == 0 && it[1].compareTo(110 /* n of ASCII dec */) == 0) {
                        sn = String(Arrays.copyOfRange(it, 2 + 1, it.size - 1), Charset.defaultCharset())
                    }
                }
            }
            return Device(Model = model, SN = sn, IPv4 = info.inet4Addresses, IPv6 = info.inet6Addresses)
        }
        return null
    }

    fun run(waitTimeMills: Long = 3000): Array<Device> {
        devices = arrayOf() //clear all elements when every call
        try {
            runBlocking {
                val jmDNS = withContext(Dispatchers.IO) {
                    JmDNS.create(InetAddress.getLocalHost())
                }
                jmDNS.addServiceListener(deviceFilterType, object : ServiceListener {
                    override fun serviceAdded(event: ServiceEvent?) {
                        //nothing
                    }

                    override fun serviceRemoved(event: ServiceEvent?) {
                        val info = event?.info ?: return
                        val device = generateDevice(info)?:return
                        devices.forEachIndexed{ idx,it ->
                            if(it.SN == device.SN){
                                devices.drop(idx)
                            }
                        }
                    }

                    override fun serviceResolved(event: ServiceEvent?) {
                        val info = event?.info ?: return
                        val device = generateDevice(info)?:return
                        var found = false
                        devices.forEachIndexed { index, it ->
                            if (it.SN == device.SN) { //just update device info
                                devices[index] = device
                                found = true
                            }
                        }
                        if(!found) devices +=device
                    }
                })
                withContext(Dispatchers.IO) {
                    Thread.sleep(waitTimeMills)
                }
            }
        } catch (e: Exception) {
            println(e.message)
        }
        return devices
    }
}