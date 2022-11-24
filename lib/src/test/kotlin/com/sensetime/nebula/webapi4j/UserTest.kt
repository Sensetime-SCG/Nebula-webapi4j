/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package com.sensetime.nebula.webapi4j

import com.sensetime.nebula.webapi4j.lib.*
import kotlinx.coroutines.*
import java.util.*
import kotlin.test.Test

class UserTest {
    private val request: RemoteDataSource = WebApi(
        System.getProperty("serverUrl"),
        System.getProperty("username"),
        System.getProperty("password")
    ).getEndpoint()
    private val unixMillisTime_2017_07_14 = 1500000000000

    @Test
    fun `test create search update and delete user`(): Unit = runBlocking {
        var userExist = false
        val userId: Long = 123
        val userName = "userTestName"
        val userType = 1
        val icNumber = "3333"
        val idNumber = "7654321"
        val jobNumber = "4544"
        val isAdmin = false
        val remark = "this is an unit test user"

        val faceBinary = this::class.java.getResource("/pictures/face_Andy-Lau.jpeg")?.readBytes()
        assert(faceBinary != null)

        val userAvatar = Base64.getEncoder().encodeToString(faceBinary)

        request.userSearch(userId).map {
            userExist = true
        }


        if (userExist)
            request.userDelete(userId).mapError {
                println(it.message)
                assert(false)
            }

        request.userCreate(
            RequestUser(
                user_id = userId,
                name = userName,
                type = userType,
                feature = "",
                avatar = userAvatar,
                ic_number = icNumber,
                id_number = idNumber,
                job_number = jobNumber,
                guest_time_start = 0,
                guest_time_end = 0,
                groups = emptyList(),
                is_admin = isAdmin,
                remark = remark
            )
        ).map {
            assert(it.user_id == userId)
            assert(it.name == userName)
            assert(it.type == userType)
            assert(it.feature.isNotEmpty())
            assert(it.avatar == userAvatar)
            assert(it.ic_number == icNumber)
            assert(it.id_number == idNumber)
            assert(it.job_number == jobNumber)
            assert(it.is_admin == isAdmin)
            assert(it.remark == remark)
            assert(it.update_at > unixMillisTime_2017_07_14)
            assert(it.create_at > unixMillisTime_2017_07_14)
        }.mapError {
            println(it.message)
            assert(false)
        }

        request.userSearch(userId).map {
            assert(it.user_id == userId)
            assert(it.name == userName)
            assert(it.type == userType)
            assert(it.feature.isNotEmpty())
            assert(it.avatar == userAvatar)
            assert(it.ic_number == icNumber)
            assert(it.id_number == idNumber)
            assert(it.job_number == jobNumber)
            assert(it.is_admin == isAdmin)
            assert(it.remark == remark)
        }.mapError {
            println(it.message)
            assert(false)
        }

        request.userUpdate(
            id = userId,
            body = RequestUser(
                user_id = userId,
                name = userName + "new",
                type = userType,
                feature = "",
                avatar = userAvatar,
                ic_number = icNumber + "new",
                id_number = idNumber + "new",
                job_number = jobNumber + "new",
                guest_time_start = 0,
                guest_time_end = 0,
                groups = emptyList(),
                is_admin = isAdmin,
                remark = remark + "new"
            )
        ).map {
            assert(it.name == userName + "new")
            assert(it.ic_number == icNumber + "new")
            assert(it.id_number == idNumber + "new")
            assert(it.job_number == jobNumber + "new")
            assert(it.remark == remark+"new")
        }.mapError {
            println(it.message)
            assert(false)
        }

        request.users(0,10).map {
            assert(it.count > 0)
            assert(it.total > 0)
            assert(it.items.isNotEmpty())
        }.mapError {
            println(it.message)
            assert(false)
        }
        request.userDelete(userId).mapError {
            println(it.message)
            assert(false)
        }
    }
}
