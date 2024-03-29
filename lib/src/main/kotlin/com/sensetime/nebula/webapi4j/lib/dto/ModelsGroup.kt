package com.sensetime.nebula.webapi4j.lib.dto



data class ResponseGroup(
    val name: String,
    val type: Int,
    val group_id: Long,
    val rule_id: Long,
    val create_at: Long,
    val update_at: Long
)

data class RequestGroup(
    val name: String,
    val type: Int,
    val group_id: Long,
    val rule_id: Long
)
