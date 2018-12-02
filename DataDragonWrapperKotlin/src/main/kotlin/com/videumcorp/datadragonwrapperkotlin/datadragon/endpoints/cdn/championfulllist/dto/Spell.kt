package com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.cdn.championfulllist.dto

data class Spell(
        val id: String?,
        val name: String?,
        val description: String?,
        val tooltip: String?,
        val leveltip: Leveltip?,
        val maxrank: Int?,
        val cooldown: List<Int?>?,
        val cooldownBurn: String?,
        val cost: List<Int?>?,
        val costBurn: String?,
        val datavalues: Datavalues?,
        val effect: List<Any?>?,
        val effectBurn: List<Any?>?,
        val vars: List<Any?>?,
        val costType: String?,
        val maxammo: String?,
        val range: List<Int?>?,
        val rangeBurn: String?,
        val image: Image?,
        val resource: String?
)