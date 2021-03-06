package com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.cdn.summonerspell.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class SummonerSpellDto(
        val type: String?,
        val version: String?,
        @JsonProperty("data") val summonerSpell: Map<String, SummonerSpell>?
)