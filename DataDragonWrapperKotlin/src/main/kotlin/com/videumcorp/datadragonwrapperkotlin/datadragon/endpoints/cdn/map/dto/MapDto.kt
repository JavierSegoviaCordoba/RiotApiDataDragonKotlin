package com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.cdn.map.dto

data class MapDto(
        val type: String?,
        val version: String?,
        val data: kotlin.collections.Map<Int, Map>?
)