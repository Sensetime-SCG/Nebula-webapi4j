package com.sensetime.nebula.webapi4j.lib



data class RequestUser(
    val user_id: Long,
    val name: String,
    val type: Int,
    val avatar: String,
    val feature: String,
    val ic_number: String,
    val id_number: String,
    val job_number: String,
    val guest_time_start: Long,
    val guest_time_end: Long,
    val groups: List<Long>,
    val is_admin: Boolean,
    val remark: String
)

data class ResponseUser(
    val user_id: Long,
    val name: String,
    val type: Int,
    val avatar: String,
    val feature: String,
    val ic_number: String,
    val id_number: String,
    val job_number: String,
    val guest_time_start: Long,
    val guest_time_end: Long,
    val groups: List<Long>,
    val is_admin: Boolean,
    val remark: String,
    val create_at: Long,
    val update_at: Long
)

data class ResponseUserItems(
    val offset:Short,
    val limit:Short,
    val count:Int,
    val total:Int,
    val items:List<ResponseUser>
)