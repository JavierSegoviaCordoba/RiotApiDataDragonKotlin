package com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.cdn.champion.dto

data class Block(
        val type: String?,
        val recMath: Boolean?,
        val recSteps: Boolean?,
        val minSummonerLevel: Int?,
        val maxSummonerLevel: Int?,
        val showIfSummonerSpell: String?,
        val hideIfSummonerSpell: String?,
        val appendAfterSection: String?,
        val visibleWithAllOf: List<String>?,
        val hiddenWithAnyOf: List<String>?,
        val items: List<Item?>?
)