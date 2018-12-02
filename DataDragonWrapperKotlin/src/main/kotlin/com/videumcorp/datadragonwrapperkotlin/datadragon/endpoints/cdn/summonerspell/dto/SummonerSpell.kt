package com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.cdn.summonerspell.dto

data class SummonerSpell(
        val id: String?,
        val name: String?,
        val description: String?,
        val tooltip: String?,
        val maxrank: Int?,
        val cooldown: List<Int?>?,
        val cooldownBurn: String?,
        val cost: List<Int?>?,
        val costBurn: String?,
        val datavalues: Datavalues?,
        val effect: List<Any?>?,
        val effectBurn: List<Any?>?,
        val vars: List<Any?>?,
        val key: String?,
        val summonerLevel: Int?,
        val modes: List<String?>?,
        val costType: String?,
        val maxammo: String?,
        val range: List<Int?>?,
        val rangeBurn: String?,
        val image: Image?,
        val resource: String?
)