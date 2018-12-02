package com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.cdn.champion.dto

data class Stats(
        val hp: Int?,
        val hpperlevel: Int?,
        val mp: Int?,
        val mpperlevel: Int?,
        val movespeed: Int?,
        val armor: Int?,
        val armorperlevel: Double?,
        val spellblock: Double?,
        val spellblockperlevel: Double?,
        val attackrange: Int?,
        val hpregen: Int?,
        val hpregenperlevel: Double?,
        val mpregen: Int?,
        val mpregenperlevel: Int?,
        val crit: Int?,
        val critperlevel: Int?,
        val attackdamage: Int?,
        val attackdamageperlevel: Int?,
        val attackspeedperlevel: Double?
)