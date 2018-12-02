package com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.cdn.item.dto

data class Basic(
        val name: String?,
        val rune: Rune?,
        val gold: Gold?,
        val group: String?,
        val description: String?,
        val colloq: String?,
        val plaintext: String?,
        val consumed: Boolean?,
        val stacks: Int?,
        val depth: Int?,
        val consumeOnFull: Boolean?,
        val from: List<Any?>?,
        val into: List<Any?>?,
        val specialRecipe: Int?,
        val inStore: Boolean?,
        val hideFromAll: Boolean?,
        val requiredChampion: String?,
        val requiredAlly: String?,
        val stats: Stats?,
        val tags: List<Any?>?,
        val maps: Map<Int, Boolean?>?
)