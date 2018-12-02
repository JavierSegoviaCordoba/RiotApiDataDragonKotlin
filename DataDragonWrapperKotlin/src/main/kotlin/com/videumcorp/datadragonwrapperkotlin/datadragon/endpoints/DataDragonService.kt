package com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints

import com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.cdn.champion.dto.ChampionDto
import com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.cdn.championfulllist.dto.ChampionFullDto
import com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.cdn.championshortlist.dto.ChampionShortDto
import com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.cdn.item.dto.ItemDto
import com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.cdn.language.dto.LanguageDto
import com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.cdn.map.dto.MapDto
import com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.cdn.profileicon.dto.ProfileIconDto
import com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.cdn.runesreforged.dto.RuneReforged
import com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.cdn.sticker.dto.StickerDto
import com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.cdn.summonerspell.dto.SummonerSpellDto
import com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.realms.realms.dto.Realms
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DataDragonService {

    //api___________________________________________________________________________________________________________________

    @GET("versions.json")
    fun GetVersionsList(): Call<List<String>>

    @GET("versions.json")
    fun GetVersionsListDeferred(): Deferred<Response<List<String>>>

    //cdn___________________________________________________________________________________________________________________

    @GET("champion/{champion_name}.json")
    fun GetChampion(@Path("champion_name") champion_name: String): Call<ChampionDto>

    @GET("champion/{champion_name}.json")
    fun GetChampionDeferred(@Path("champion_name") champion_name: String): Deferred<Response<ChampionDto>>

    @GET("championFull.json")
    fun GetChampionFullList(): Call<ChampionFullDto>

    @GET("championFull.json")
    fun GetChampionFullListDeferred(): Deferred<Response<ChampionFullDto>>

    @GET("champion.json")
    fun GetChampionShortList(): Call<ChampionShortDto>

    @GET("champion.json")
    fun GetChampionShortListDeferred(): Deferred<Response<ChampionShortDto>>

    @GET("item.json")
    fun GetItem(): Call<ItemDto>

    @GET("item.json")
    fun GetItemDeferred(): Deferred<Response<ItemDto>>

    @GET("language.json")
    fun GetLanguage(): Call<LanguageDto>

    @GET("language.json")
    fun GetLanguageDeferred(): Deferred<Response<LanguageDto>>

    @GET("languages.json")
    fun GetLanguages(): Call<List<String>>

    @GET("languages.json")
    fun GetLanguagesDeferred(): Deferred<Response<List<String>>>

    @GET("map.json")
    fun GetMap(): Call<MapDto>

    @GET("map.json")
    fun GetMapDeferred(): Deferred<Response<MapDto>>

    @GET("profileicon.json")
    fun GetProfileIcon(): Call<ProfileIconDto>

    @GET("profileicon.json")
    fun GetProfileIconDeferred(): Deferred<Response<ProfileIconDto>>

    @GET("runesReforged.json")
    fun GetRunesReforged(): Call<List<RuneReforged>>

    @GET("runesReforged.json")
    fun GetRunesReforgedDeferred(): Deferred<Response<List<RuneReforged>>>

    @GET("sticker.json")
    fun GetSticker(): Call<StickerDto>

    @GET("sticker.json")
    fun GetStickerDeferred(): Deferred<Response<StickerDto>>

    @GET("summoner.json")
    fun GetSummonerSpell(): Call<SummonerSpellDto>

    @GET("summoner.json")
    fun GetSummonerSpellDeferred(): Deferred<Response<SummonerSpellDto>>

    //Realms________________________________________________________________________________________________________________

    @GET("{region}.json")
    fun GetRealms(@Path("region") region: String): Call<Realms>

    @GET("{region}.json")
    fun GetRealmsDeferred(@Path("region") region: String): Deferred<Response<Realms>>
}
