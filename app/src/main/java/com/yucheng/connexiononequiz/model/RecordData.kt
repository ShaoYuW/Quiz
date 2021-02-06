package com.yucheng.connexiononequiz.model

abstract class RecordData {
    abstract val type: Int
}

data class Money(
    override val type: Int = 0,
    val count: Int = 100,
    val value: Int = 1999,
    val percent: Int = 5,
    val interactiveReward: String = "--",
    val communityReward: String = "--"
) : RecordData()

data class Ad(
    override val type: Int = 1,
):RecordData()