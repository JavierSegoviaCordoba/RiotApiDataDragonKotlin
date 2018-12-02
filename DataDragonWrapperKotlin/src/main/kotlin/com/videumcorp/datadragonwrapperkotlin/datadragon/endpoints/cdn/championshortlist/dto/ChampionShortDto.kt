package com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.cdn.championshortlist.dto

data class ChampionShortDto(
        val type: String?,
        val format: String?,
        val version: String?,
        val data: Map<String, ChampionShort>?

)