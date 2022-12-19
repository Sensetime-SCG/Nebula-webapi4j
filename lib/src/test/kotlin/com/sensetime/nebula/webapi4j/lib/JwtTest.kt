package com.sensetime.nebula.webapi4j.lib

import kotlin.test.Test
import kotlin.test.assertEquals
import com.sensetime.nebula.webapi4j.lib.base.Jwt

class JwtTest {
    val jwtString: String =
        "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2Njc5ODMxMTQsImlhdCI6MTY2Nzg5NjcxNCwiaXNzIjoiU2Vuc2VUaW1lIiwidXNlcklkIjoiYWRtaW4ifQ.dWhJHoYOna21jJreUVmMix-_nrwRRiSiV-xSYdzuVho"
    @Test
    fun testJwtTokenVerify() {
        assert(!Jwt.verify(jwtString))
    }

    @Test
    fun testJwtTokenDecode(){
        val d = Jwt.decode(jwtString)
        assertEquals(d?.header?.alg, "HS256")
        assertEquals(d?.header?.typ, "JWT")
        assertEquals(d?.payload?.iss, "SenseTime")
    }
}