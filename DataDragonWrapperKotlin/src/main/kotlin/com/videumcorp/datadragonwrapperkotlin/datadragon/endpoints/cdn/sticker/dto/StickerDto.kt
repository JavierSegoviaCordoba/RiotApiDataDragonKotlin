package com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.cdn.sticker.dto

data class StickerDto(
        val type: String?,
        val version: String?,
        val data: Map<String, Sticker>?
)