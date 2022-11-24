/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package com.sensetime.nebula.webapi4j

import com.sensetime.nebula.webapi4j.lib.*
import kotlinx.coroutines.*
import kotlin.test.Test
import kotlin.test.assertTrue

class GroupTest {

    private var serverUrl: String = System.getProperty("serverUrl")
    private var username: String = System.getProperty("username")
    private var password: String = System.getProperty("password")
    private val request: RemoteDataSource = WebApi(serverUrl, username, password).getEndpoint()
    private val unixMillisTime_2017_07_14 = 1500000000000

    @Test
    fun `test create search update and delete group`(): Unit = runBlocking {
        val groupName = "testGroup"
        val groupType = 1
        val groupId: Long = 123
        val ruleId: Long = 0 //not bind any
        var groupExist = false
        request.groupSearch(groupId).map {
            if (groupId == it.group_id)
                groupExist = true
        }.mapError { println(it.message) }

        if (groupExist)
            request.groupDelete(groupId).mapError { assert(false) }

        request.groupCreate(RequestGroup(groupName, groupType, groupId, ruleId)).map {
            assert(it.name == groupName)
            assert(it.type == groupType)
            assert(it.group_id == groupId)
        }.mapError {
            println(it.message)
            assert(false)
        }

        request.groupSearch(groupId).map {
            assert(it.group_id == groupId)
            assert(it.name == groupName)
            assert(it.type == groupType)
            assert(it.create_at > unixMillisTime_2017_07_14)
            assert(it.update_at > unixMillisTime_2017_07_14)
        }.mapError {
            assert(false)
        }

        val newGroupName = "newGroupTestName"
        request.groupUpdate(groupId, RequestGroup(newGroupName, groupType, groupId, ruleId)).map {
            assert(it.group_id == groupId)
            assert(it.name == newGroupName)
        }.mapError {
            assert(false)
        }

        request.groups(0,10).map {
            println(it)
        }.mapError {
            println(it.message)
        }

        request.groupDelete(groupId).mapError { assert(false) }
    }
}
