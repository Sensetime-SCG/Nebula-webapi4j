package com.sensetime.nebula.webapi4j

import org.junit.Test

class DiscoverTest {
    @Test
    fun discover() {
        val devices = Discover.run()
        assert(devices.isNotEmpty())
        println("Found devices count: " + devices.size)
        devices.forEach {
            println(it)
        }
    }
}