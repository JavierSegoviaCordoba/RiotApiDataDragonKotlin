package com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.cdn.language.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class LanguageDto(
        val type: String?,
        val version: String?,
        @JsonProperty("data") val language: Language?,
        val tree: Tree?
)