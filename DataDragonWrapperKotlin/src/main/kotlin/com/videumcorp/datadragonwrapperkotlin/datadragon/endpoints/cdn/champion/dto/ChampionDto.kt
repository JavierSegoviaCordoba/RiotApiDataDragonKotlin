package com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.cdn.champion.dto

data class ChampionDto(
        val type: String?,
        val format: String?,
        val version: String?,
        val data: Map<String, Champion>?
)