package com.videumcorp.datadragonwrapperkotlin.datadragon

import com.videumcorp.datadragonwrapperkotlin.datadragon.constants.Locale
import com.videumcorp.datadragonwrapperkotlin.datadragon.constants.Platform
import com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.api.versions.VersionsMethods
import com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.cdn.champion.ChampionMethods
import com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.cdn.champion.ChampionMethods.getChampionKeyIdListDeferred
import com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.cdn.champion.dto.Champion
import com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.cdn.championfulllist.ChampionFullListMethods
import com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.cdn.championfulllist.dto.ChampionFull
import com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.cdn.championshortlist.ChampionShortListMethods
import com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.cdn.championshortlist.dto.ChampionKeyId
import com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.cdn.championshortlist.dto.ChampionShort
import com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.cdn.item.ItemMethods
import com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.cdn.item.dto.Item
import com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.cdn.language.LanguageMethods
import com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.cdn.language.dto.Language
import com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.cdn.languages.LanguagesMethods
import com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.cdn.map.MapMethods
import com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.cdn.map.dto.Map
import com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.cdn.profileicon.ProfileIconMethods
import com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.cdn.profileicon.dto.ProfileIcon
import com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.cdn.runesreforged.RunesReforgedMethods
import com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.cdn.runesreforged.dto.Rune
import com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.cdn.runesreforged.dto.RuneReforged
import com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.cdn.sticker.StickerMethods
import com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.cdn.sticker.dto.Sticker
import com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.cdn.summonerspell.SummonerSpellMethods
import com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.cdn.summonerspell.dto.SummonerSpell
import com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.realms.realms.RealmsMethods
import com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.realms.realms.dto.Realms
import com.videumcorp.datadragonwrapperkotlin.datadragon.utils.CallbackFun

class DataDragon(private val platform: Platform) {

    //api___________________________________________________________________________________________________________________

    //VersionListDeferredMethods
    suspend fun getVersionListDeferred(): List<String>? {
        return VersionsMethods.getVersionListDeferred()
    }

    //VersionMethods
    fun getVersionsList(callback: CallbackFun<List<String>>.() -> Unit) {

        VersionsMethods.getVersionsList(callback)
    }

    //VersionMethodsAsync
    fun getVersionsListAsync(callback: CallbackFun<List<String>>.() -> Unit) {

        VersionsMethods.getVersionsListAsync(callback)
    }

    //cdn___________________________________________________________________________________________________________________
    //ChampionMethodsCoroutines

    suspend fun getChampionDeferred(champion_name: String, locale: Locale, version: String): Champion? {
        return ChampionMethods.getChampionDeferred(champion_name, platform, locale, version)
    }

    suspend fun getChampionDeferred(champion_key: Int, locale: Locale, version: String): Champion? {
        return ChampionMethods.getChampionDeferred(champion_key, platform, locale, version)
    }

    suspend fun getChampionKeyDeferred(champion_id: String, locale: Locale, version: String): Int? {
        return ChampionMethods.getChampionKeyDeferred(champion_id, platform, locale, version)
    }

    suspend fun getChampionIdDeferred(champion_key: Int, locale: Locale, version: String): String? {
        return ChampionMethods.getChampionIdDeferred(champion_key, platform, locale, version)
    }

    suspend fun getChampionKeyIdListDeferred(
        locale: Locale,
        version: String
    ): List<ChampionKeyId> {
        return getChampionKeyIdListDeferred(platform, locale, version)
    }

    //ChampionMethods

    fun getChampion(
        champion_name: String, locale: Locale, version: String,
        callback: CallbackFun<Champion>.() -> Unit

    ) {

        ChampionMethods.getChampion(champion_name, platform, locale, version, callback)
    }


    fun getChampion(
        champion_key: Int, locale: Locale, version: String,
        callback: CallbackFun<Champion>.() -> Unit
    ) {

        ChampionMethods.getChampion(champion_key, platform, locale, version, callback)
    }

    fun getChampionKey(
        champion_id: String,
        locale: Locale,
        version: String,
        callback: CallbackFun<Int>.() -> Unit
    ) {

        ChampionMethods.getChampionKey(champion_id, platform, locale, version, callback)
    }

    fun getChampionId(
        champion_key: Int,
        locale: Locale,
        version: String,
        callback: CallbackFun<String>.() -> Unit
    ) {

        ChampionMethods.getChampionId(champion_key, platform, locale, version, callback)
    }

    fun getChampionKeyIdList(
        locale: Locale,
        version: String,
        callback: CallbackFun<List<ChampionKeyId>>.() -> Unit
    ) {

        ChampionMethods.getChampionKeyIdList(platform, locale, version, callback)
    }

    //ChampionMethodsAsync

    fun getChampionAsync(
        champion_name: String, locale: Locale, version: String,
        callback: CallbackFun<Champion>.() -> Unit
    ) {

        ChampionMethods.getChampionAsync(champion_name, platform, locale, version, callback)
    }

    fun getChampionAsync(
        champion_key: Int, locale: Locale, version: String,
        callback: CallbackFun<Champion>.() -> Unit
    ) {

        ChampionMethods.getChampionAsync(champion_key, platform, locale, version, callback)
    }

    fun getChampionKeyAsync(
        champion_id: String,
        locale: Locale, version: String,
        callback: CallbackFun<Int>.() -> Unit
    ) {

        ChampionMethods.getChampionKeyAsync(champion_id, platform, locale, version, callback)
    }

    fun getChampionIdAsync(
        champion_key: Int,
        locale: Locale, version: String,
        callback: CallbackFun<String>.() -> Unit
    ) {

        ChampionMethods.getChampionIdAsync(champion_key, platform, locale, version, callback)
    }

    fun getChampionKeyIdListAsync(
        locale: Locale, version: String,
        callback: CallbackFun<List<ChampionKeyId>>.() -> Unit
    ) {

        ChampionMethods.getChampionKeyIdListAsync(platform, locale, version, callback)
    }

    //ChampionFullListMethodsCoroutines

    suspend fun getChampionFullListDeferred(locale: Locale, version: String): List<ChampionFull>? {
        return ChampionFullListMethods.getChampionFullListDeferred(platform, locale, version)
    }

    //ChampionFullListMethods

    fun getChampionFullList(
        locale: Locale, version: String,
        callback: CallbackFun<List<ChampionFull>>.() -> Unit
    ) {

        ChampionFullListMethods.getChampionFullList(platform, locale, version, callback)
    }

    //ChampionFullListMethodsAsync

    fun getChampionFullListAsync(
        locale: Locale, version: String,
        callback: CallbackFun<List<ChampionFull>>.() -> Unit
    ) {

        ChampionFullListMethods.getChampionFullListAsync(platform, locale, version, callback)
    }


    //ChampionShortListDeferredMethods
    suspend fun getChampionShortListDeferred(locale: Locale, version: String): List<ChampionShort>? {

        return ChampionShortListMethods.getChampionShortListDeferred(platform, locale, version)
    }

    //ChampionShortListMethods
    fun getChampionShortList(
        locale: Locale, version: String,
        callback: CallbackFun<List<ChampionShort>>.() -> Unit
    ) {

        ChampionShortListMethods.getChampionShortList(platform, locale, version, callback)
    }

    //ChampionShortListMethodsAsync
    fun getChampionShortListAsync(
        locale: Locale, version: String,
        callback: CallbackFun<List<ChampionShort>>.() -> Unit
    ) {

        ChampionShortListMethods.getChampionShortListAsync(platform, locale, version, callback)
    }

    //ItemDeferredMethods
    suspend fun getItemDeferred(item_id: Int, locale: Locale, version: String): Item? {

        return ItemMethods.getItemDeferred(item_id, platform, locale, version)
    }

    suspend fun getItemListDeferred(locale: Locale, version: String): List<Item?>? {

        return ItemMethods.getItemListDeferred(platform, locale, version)
    }

    //ItemMethods
    fun getItem(item_id: Int, locale: Locale, version: String, callback: CallbackFun<Item>.() -> Unit) {

        ItemMethods.getItem(item_id, platform, locale, version, callback)
    }

    fun getItemList(locale: Locale, version: String, callback: CallbackFun<List<Item?>>.() -> Unit) {

        ItemMethods.getItemList(platform, locale, version, callback)
    }

    //ItemMethodsAsync
    fun getItemAsync(
        item_id: Int,
        locale: Locale,
        version: String,
        callback: CallbackFun<Item>.() -> Unit
    ) {

        ItemMethods.getItemAsync(item_id, platform, locale, version, callback)
    }

    fun getItemListAsync(
        locale: Locale, version: String,
        callback: CallbackFun<List<Item?>>.() -> Unit
    ) {

        ItemMethods.getItemListAsync(platform, locale, version, callback)
    }

    //LanguageDeferredMethods
    suspend fun getLanguageDeferred(locale: Locale, version: String): Language? {

        return LanguageMethods.getLanguageDeferred(platform, locale, version)
    }

    //LanguageMethods
    fun getLanguage(locale: Locale, version: String, callback: CallbackFun<Language>.() -> Unit) {

        LanguageMethods.getLanguage(platform, locale, version, callback)
    }

    //LanguageMethodsAsync
    fun getLanguageAsync(locale: Locale, version: String, callback: CallbackFun<Language>.() -> Unit) {

        LanguageMethods.getLanguageAsync(platform, locale, version, callback)
    }

    //LanguagesDeferredMethods
    suspend fun getLanguagesListDeferred(): List<String>? {

        return LanguagesMethods.getLanguagesListDeferred()
    }

    //LanguagesMethods
    fun getLanguagesList(callback: CallbackFun<List<String>>.() -> Unit) {

        LanguagesMethods.getLanguagesList(callback)
    }

    //LanguageMethodsAsync
    fun getLanguagesListAsync(callback: CallbackFun<List<String>>.() -> Unit) {

        LanguagesMethods.getLanguagesListAsync(callback)
    }

    //MapDeferredMethods
    suspend fun getMapDeferred(map_id: Int, locale: Locale, version: String): Map? {

        return MapMethods.getMapDeferred(map_id, platform, locale, version)
    }

    suspend fun getMapListDeferred(locale: Locale, version: String): List<Map>? {

        return MapMethods.getMapListDeferred(platform, locale, version)
    }
    //MapMethods

    fun getMap(map_id: Int, locale: Locale, version: String, callback: CallbackFun<Map>.() -> Unit) {

        MapMethods.getMap(map_id, platform, locale, version, callback)
    }

    fun getMapList(locale: Locale, version: String, callback: CallbackFun<List<Map>>.() -> Unit) {

        MapMethods.getMapList(platform, locale, version, callback)
    }

    //MapMethodsAsync

    fun getMapAsync(map_id: Int, locale: Locale, version: String, callback: CallbackFun<Map>.() -> Unit) {

        MapMethods.getMapAsync(map_id, platform, locale, version, callback)
    }

    fun getMapListAsync(locale: Locale, version: String, callback: CallbackFun<List<Map>>.() -> Unit) {

        MapMethods.getMapListAsync(platform, locale, version, callback)
    }

    //ProfileIconDeferredMethods

    suspend fun getProfileIconDeferred(profile_icon_id: Int, locale: Locale, version: String): ProfileIcon? {

        return ProfileIconMethods.getProfileIconDeferred(profile_icon_id, platform, locale, version)
    }

    suspend fun getProfileIconListDeferred(locale: Locale, version: String): List<ProfileIcon?>? {

        return ProfileIconMethods.getProfileIconListDeferred(platform, locale, version)
    }

    //ProfileIconMethods

    fun getProfileIcon(
        profile_icon_id: Int, locale: Locale, version: String,
        callback: CallbackFun<ProfileIcon>.() -> Unit
    ) {

        ProfileIconMethods.getProfileIcon(profile_icon_id, platform, locale, version, callback)
    }

    fun getProfileIconList(
        locale: Locale, version: String,
        callback: CallbackFun<List<ProfileIcon?>>.() -> Unit
    ) {

        ProfileIconMethods.getProfileIconList(platform, locale, version, callback)
    }

    //ProfileIconMethodsAsync

    fun getProfileIconAsync(
        profile_icon_id: Int, locale: Locale, version: String,
        callback: CallbackFun<ProfileIcon>.() -> Unit
    ) {

        ProfileIconMethods.getProfileIconAsync(profile_icon_id, platform, locale, version, callback)
    }

    fun getProfileIconListAsync(
        locale: Locale, version: String,
        callback: CallbackFun<List<ProfileIcon?>>.() -> Unit
    ) {

        ProfileIconMethods.getProfileIconListAsync(platform, locale, version, callback)
    }

    //RunesReforgedDeferredMethods

    suspend fun getRuneReforgedListDeferred(locale: Locale, version: String): List<RuneReforged>? {

        return RunesReforgedMethods.getRuneReforgedListDeferred(platform, locale, version)
    }

    suspend fun getRuneReforgedDeferred(rune_reforged_id: Int, locale: Locale, version: String): RuneReforged? {

        return RunesReforgedMethods.getRuneReforgedDeferred(rune_reforged_id, platform, locale, version)
    }

    suspend fun getRuneReforgedDeferred(rune_reforged_key: String, locale: Locale, version: String): RuneReforged? {

        return RunesReforgedMethods.getRuneReforgedDeferred(rune_reforged_key, platform, locale, version)
    }

    suspend fun getRuneDeferred(rune_id: Int, locale: Locale, version: String): Rune? {

        return RunesReforgedMethods.getRuneDeferred(rune_id, platform, locale, version)
    }

    suspend fun getRuneDeferred(rune_key: String, locale: Locale, version: String): Rune? {

        return RunesReforgedMethods.getRuneDeferred(rune_key, platform, locale, version)
    }

    //RunesReforgedMethods

    fun getRuneReforgedList(
        locale: Locale, version: String,
        callback: CallbackFun<List<RuneReforged>>.() -> Unit
    ) {

        RunesReforgedMethods.getRuneReforgedList(platform, locale, version, callback)
    }

    fun getRuneReforged(
        rune_reforged_id: Int, locale: Locale, version: String,
        callback: CallbackFun<RuneReforged>.() -> Unit
    ) {

        RunesReforgedMethods.getRuneReforged(rune_reforged_id, platform, locale, version, callback)
    }

    fun getRuneReforged(
        rune_reforged_key: String, locale: Locale, version: String,
        callback: CallbackFun<RuneReforged>.() -> Unit
    ) {

        RunesReforgedMethods.getRuneReforged(rune_reforged_key, platform, locale, version, callback)
    }

    fun getRune(
        rune_id: Int, locale: Locale, version: String,
        callback: CallbackFun<Rune>.() -> Unit
    ) {

        RunesReforgedMethods.getRune(rune_id, platform, locale, version, callback)
    }

    fun getRune(
        rune_key: String, locale: Locale, version: String,
        callback: CallbackFun<Rune>.() -> Unit
    ) {

        RunesReforgedMethods.getRune(rune_key, platform, locale, version, callback)
    }

    //RunesReforgedMethodsAsync

    fun getRuneReforgedListAsync(
        locale: Locale, version: String,
        callback: CallbackFun<List<RuneReforged>>.() -> Unit
    ) {

        RunesReforgedMethods.getRuneReforgedListAsync(platform, locale, version, callback)
    }

    fun getRuneReforgedAsync(
        rune_reforged_id: Int, locale: Locale, version: String,
        callback: CallbackFun<RuneReforged>.() -> Unit
    ) {

        RunesReforgedMethods.getRuneReforgedAsync(rune_reforged_id, platform, locale, version, callback)
    }

    fun getRuneReforgedAsync(
        rune_reforged_key: String, locale: Locale, version: String,
        callback: CallbackFun<RuneReforged>.() -> Unit
    ) {

        RunesReforgedMethods.getRuneReforgedAsync(rune_reforged_key, platform, locale, version, callback)
    }

    fun getRuneAsync(
        rune_id: Int, locale: Locale, version: String,
        callback: CallbackFun<Rune>.() -> Unit
    ) {

        RunesReforgedMethods.getRuneAsync(rune_id, platform, locale, version, callback)
    }

    fun getRuneAsync(
        rune_key: String, locale: Locale, version: String,
        callback: CallbackFun<Rune>.() -> Unit
    ) {

        RunesReforgedMethods.getRuneAsync(rune_key, platform, locale, version, callback)
    }

    suspend fun getStickerListDeferred(locale: Locale, version: String): List<Sticker>? {

        return StickerMethods.getStickerListDeferred(platform, locale, version)
    }

    suspend fun getStickerDeferred(sticker_name: String, locale: Locale, version: String): Sticker? {

        return StickerMethods.getStickerDeferred(sticker_name, platform, locale, version)
    }

    suspend fun getStickerDeferred(sticker_id: Int, locale: Locale, version: String): Sticker? {

        return StickerMethods.getStickerDeferred(sticker_id, platform, locale, version)
    }

    fun getStickerList(
        locale: Locale, version: String,
        callback: CallbackFun<List<Sticker>>.() -> Unit
    ) {

        StickerMethods.getStickerList(platform, locale, version, callback)
    }

    fun getSticker(
        sticker_name: String, locale: Locale, version: String,
        callback: CallbackFun<Sticker>.() -> Unit
    ) {

        StickerMethods.getSticker(sticker_name, platform, locale, version, callback)
    }

    fun getSticker(
        sticker_id: Int, locale: Locale, version: String,
        callback: CallbackFun<Sticker>.() -> Unit
    ) {

        StickerMethods.getSticker(sticker_id, platform, locale, version, callback)
    }

    fun getStickerListAsync(
        locale: Locale, version: String,
        callback: CallbackFun<List<Sticker>>.() -> Unit
    ) {

        StickerMethods.getStickerListAsync(platform, locale, version, callback)
    }

    fun getStickerAsync(
        sticker_name: String, locale: Locale, version: String,
        callback: CallbackFun<Sticker>.() -> Unit
    ) {

        StickerMethods.getStickerAsync(sticker_name, platform, locale, version, callback)
    }

    fun getStickerAsync(
        sticker_id: Int, locale: Locale, version: String,
        callback: CallbackFun<Sticker>.() -> Unit
    ) {

        StickerMethods.getStickerAsync(sticker_id, platform, locale, version, callback)
    }

    //SummonerSpellDeferredMethods
    suspend fun getSummonerSpellListDeferred(locale: Locale, version: String): List<SummonerSpell>? {

        return SummonerSpellMethods.getSummonerSpellListDeferred(platform, locale, version)
    }

    suspend fun getSummonerSpellDeferred(summoner_spell_id: String, locale: Locale, version: String): SummonerSpell? {

        return SummonerSpellMethods.getSummonerSpellDeferred(summoner_spell_id, platform, locale, version)
    }


    //SummonerSpellMethods

    fun getSummonerSpellList(
        locale: Locale, version: String,
        callback: CallbackFun<List<SummonerSpell>>.() -> Unit
    ) {

        SummonerSpellMethods.getSummonerSpellList(platform, locale, version, callback)
    }

    fun getSummonerSpell(
        summoner_spell_id: String, locale: Locale, version: String,
        callback: CallbackFun<SummonerSpell>.() -> Unit
    ) {

        SummonerSpellMethods.getSummonerSpell(summoner_spell_id, platform, locale, version, callback)
    }

    //SummonerSpellMethodsAsync

    fun getSummonerSpellListAsync(
        locale: Locale, version: String,
        callback: CallbackFun<List<SummonerSpell>>.() -> Unit
    ) {

        SummonerSpellMethods.getSummonerSpellListAsync(platform, locale, version, callback)
    }


    fun getSummonerSpellAsync(
        summoner_spell_id: String, locale: Locale, version: String,
        callback: CallbackFun<SummonerSpell>.() -> Unit
    ) {

        SummonerSpellMethods.getSummonerSpellAsync(summoner_spell_id, platform, locale, version, callback)
    }


    //Realms____________________________________________________________________________________________________________
    //RealmsMethods
    fun getRealms(callback: CallbackFun<Realms>.() -> Unit) {

        RealmsMethods.getRealms(platform, callback)
    }

    //RealmsMethodsAsync
    fun getRealmsAsync(callback: CallbackFun<Realms>.() -> Unit) {

        RealmsMethods.getRealmsAsync(platform, callback)
    }

    suspend fun getRealmsDeferred(): Realms? {
        return RealmsMethods.getRealmsDeferred(platform)
    }

}
