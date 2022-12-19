package com.sensetime.nebula.webapi4j.lib.dto

data class ModelRuleSchedulePeriodTime(
    val hour: Short,
    val min: Short,
    val sec: Short
)

data class ModelRuleSchedulePeriod(
    val start_time: ModelRuleSchedulePeriodTime,
    val end_time: ModelRuleSchedulePeriodTime
)

data class ModelRuleScheduleSpecialDay(
    val year: Int,
    val month: Int,
    val day: Int,
    val today_period: List<ModelRuleSchedulePeriod>
)

data class ModelRuleSchedule(
    val onset_point: Long,
    val expire_point: Long,
    var mon_period: List<ModelRuleSchedulePeriod>,
    val the_period: List<ModelRuleSchedulePeriod>,
    val wed_period: List<ModelRuleSchedulePeriod>,
    val thur_period: List<ModelRuleSchedulePeriod>,
    val fri_period: List<ModelRuleSchedulePeriod>,
    val sat_period: List<ModelRuleSchedulePeriod>,
    val sun_period: List<ModelRuleSchedulePeriod>,
    val special_days: List<ModelRuleScheduleSpecialDay>
)

data class RequestRule(
    val rule_id: Long,
    val name: String,
    val schedule: ModelRuleSchedule
)

data class ResponseRule(
    val rule_id: Long,
    val name: String,
    val schedule: ModelRuleSchedule,
    val create_at: Long,
    val update_at: Long
)

data class ResponseRuleItems(
    val offset:Short,
    val limit:Short,
    val count:Int,
    val total:Int,
    val items:List<ResponseRule>
)