package com.sensetime.nebula.webapi4j.lib

data class ModelAiQualityRect(
    val left:Int,
    val right:Int,
    val top:Int,
    val bottom:Int
)

data class ModelAiQualityResult(
    val code:Int,
    val info:String
)
data class AiQuality(
    val yaw:Double,
    val pitch:Double,
    val roll:Double,
    val rect:ModelAiQualityRect,
    val result:ModelAiQualityResult
)
