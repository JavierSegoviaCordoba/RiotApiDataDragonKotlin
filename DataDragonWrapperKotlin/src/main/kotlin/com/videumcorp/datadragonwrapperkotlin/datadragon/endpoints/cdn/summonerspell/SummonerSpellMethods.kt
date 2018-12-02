package com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.cdn.summonerspell

import com.videumcorp.datadragonwrapperkotlin.datadragon.constants.Locale
import com.videumcorp.datadragonwrapperkotlin.datadragon.constants.Platform
import com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.cdn.summonerspell.dto.SummonerSpell
import com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.cdn.summonerspell.dto.SummonerSpellDto
import com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.createDataDragonServiceCdn
import com.videumcorp.datadragonwrapperkotlin.datadragon.utils.CallbackFun
import com.videumcorp.datadragonwrapperkotlin.datadragon.utils.DataDragonException
import com.videumcorp.datadragonwrapperkotlin.datadragon.utils.ErrorCode
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.util.*

object SummonerSpellMethods {

    //CoroutinesMethods
    suspend fun getSummonerSpellListDeferred(
            platform: Platform,
            locale: Locale,
            version: String
    ): List<SummonerSpell>? {

        val request = createDataDragonServiceCdn(platform, locale, version).GetSummonerSpellDeferred()

        return try {
            val response = request.await()

            if (response.isSuccessful) {
                val summonerSpellMap = response.body()?.summonerSpell

                ArrayList(summonerSpellMap?.toSortedMap()?.values)
            } else {
                val errorCode = ErrorCode(response.code(), response.message())
                throw DataDragonException(errorCode)
            }
        } catch (t: Throwable) {
            throw Throwable(t)
        }
    }

    suspend fun getSummonerSpellDeferred(
            summoner_spell_id: String,
            platform: Platform,
            locale: Locale,
            version: String
    ): SummonerSpell? {

        val request = createDataDragonServiceCdn(platform, locale, version).GetSummonerSpellDeferred()

        try {
            val response = request.await()

            if (response.isSuccessful) {
                val summonerSpellMap = response.body()?.summonerSpell
                val summonerSpell = summonerSpellMap?.get(summoner_spell_id)

                if (summonerSpell != null)
                    return summonerSpell
                else {
                    val errorCode = ErrorCode(404, "Not found")
                    throw DataDragonException(errorCode)
                }
            } else {
                val errorCode = ErrorCode(response.code(), response.message())
                throw DataDragonException(errorCode)
            }
        } catch (t: Throwable) {
            throw Throwable(t)
        }
    }

    //SyncMethods_______________________________________________________________________________________________________

    fun getSummonerSpellList(
            platform: Platform, locale: Locale, version: String,
            callback: CallbackFun<List<SummonerSpell>>.() -> Unit
    ) {

        val call = createDataDragonServiceCdn(platform, locale, version).GetSummonerSpell()

        val callbackFun = CallbackFun<List<SummonerSpell>>()
        callback.invoke(callbackFun)

        try {
            val response = call.execute()

            if (response.isSuccessful) {
                val summonerSpellMap = response.body()?.summonerSpell

                callbackFun.onSuccess?.invoke(ArrayList(summonerSpellMap?.toSortedMap()?.values))
            } else {
                callbackFun.onErrorCode?.invoke(ErrorCode(response.code(), response.message()))
            }
        } catch (e: IOException) {
            e.printStackTrace()
            callbackFun.onFailure?.invoke(e)
        }
    }

    fun getSummonerSpell(
            summoner_spell_id: String, platform: Platform, locale: Locale, version: String,
            callback: CallbackFun<SummonerSpell>.() -> Unit
    ) {

        val call = createDataDragonServiceCdn(platform, locale, version).GetSummonerSpell()

        val callbackFun = CallbackFun<SummonerSpell>()
        callback.invoke(callbackFun)

        try {
            val response = call.execute()

            if (response.isSuccessful) {

                val summonerSpellMap = response.body()?.summonerSpell

                val summonerSpell = summonerSpellMap?.get(summoner_spell_id)

                if (summonerSpell != null) callbackFun.onSuccess?.invoke(summonerSpell)
                else callbackFun.onErrorCode?.invoke(ErrorCode(404, "Not found"))
            } else {
                callbackFun.onErrorCode?.invoke(ErrorCode(response.code(), response.message()))
            }
        } catch (e: IOException) {
            e.printStackTrace()
            callbackFun.onFailure?.invoke(e)
        }

    }

    //AsyncMethods_______________________________________________________________________________________________________

    fun getSummonerSpellListAsync(
            platform: Platform, locale: Locale, version: String,
            callback: CallbackFun<List<SummonerSpell>>.() -> Unit
    ) {

        val call = createDataDragonServiceCdn(platform, locale, version).GetSummonerSpell()

        val callbackFun = CallbackFun<List<SummonerSpell>>()
        callback.invoke(callbackFun)

        call.enqueue(object : Callback<SummonerSpellDto> {
            override fun onResponse(call: Call<SummonerSpellDto>, response: Response<SummonerSpellDto>) {
                if (response.isSuccessful) {
                    val stickerMap = response.body()?.summonerSpell

                    callbackFun.onSuccess?.invoke(ArrayList(stickerMap?.toSortedMap()?.values))
                } else {
                    callbackFun.onErrorCode?.invoke(ErrorCode(response.code(), response.message()))
                }
            }

            override fun onFailure(call: Call<SummonerSpellDto>, t: Throwable) {
                callbackFun.onFailure?.invoke(t)
            }
        })
    }

    fun getSummonerSpellAsync(
            summoner_spell_id: String, platform: Platform, locale: Locale, version: String,
            callback: CallbackFun<SummonerSpell>.() -> Unit
    ) {

        val call = createDataDragonServiceCdn(platform, locale, version).GetSummonerSpell()

        val callbackFun = CallbackFun<SummonerSpell>()
        callback.invoke(callbackFun)

        call.enqueue(object : Callback<SummonerSpellDto> {
            override fun onResponse(call: Call<SummonerSpellDto>, response: Response<SummonerSpellDto>) {
                if (response.isSuccessful) {

                    val summonerSpellMap = response.body()?.summonerSpell

                    val summonerSpell = summonerSpellMap?.get(summoner_spell_id)

                    if (summonerSpell != null)
                        callbackFun.onSuccess?.invoke(summonerSpell)
                    else
                        callbackFun.onErrorCode?.invoke(ErrorCode(404, "Not found"))
                } else {
                    callbackFun.onErrorCode?.invoke(ErrorCode(response.code(), response.message()))
                }
            }

            override fun onFailure(call: Call<SummonerSpellDto>, t: Throwable) {
                callbackFun.onFailure?.invoke(t)
            }
        })
    }
}
