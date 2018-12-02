package com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.cdn.runesreforged

import com.videumcorp.datadragonwrapperkotlin.datadragon.constants.Locale
import com.videumcorp.datadragonwrapperkotlin.datadragon.constants.Platform
import com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.cdn.runesreforged.dto.Rune
import com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.cdn.runesreforged.dto.RuneReforged
import com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.createDataDragonServiceCdn
import com.videumcorp.datadragonwrapperkotlin.datadragon.utils.CallbackFun
import com.videumcorp.datadragonwrapperkotlin.datadragon.utils.DataDragonException
import com.videumcorp.datadragonwrapperkotlin.datadragon.utils.ErrorCode
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.util.*
import kotlin.collections.ArrayList

object RunesReforgedMethods {

    //CoroutinesMethods
    suspend fun getRuneReforgedListDeferred(platform: Platform, locale: Locale, version: String): List<RuneReforged>? {

        val request = createDataDragonServiceCdn(platform, locale, version).GetRunesReforgedDeferred()

        return try {
            val response = request.await()

            if (response.isSuccessful) {
                val runeReforgedList = response.body()
                runeReforgedList?.sortedWith(compareBy { it.key })

                runeReforgedList
            } else {
                val errorCode = ErrorCode(response.code(), response.message())
                throw DataDragonException(errorCode)
            }
        } catch (t: Throwable) {
            throw Throwable(t)
        }
    }

    suspend fun getRuneReforgedDeferred(
            rune_reforged_id: Int,
            platform: Platform,
            locale: Locale,
            version: String
    ): RuneReforged? {

        val request = createDataDragonServiceCdn(platform, locale, version).GetRunesReforgedDeferred()

        try {
            val response = request.await()

            if (response.isSuccessful) {
                val runeReforgedList = response.body()

                for (runeReforged in Objects.requireNonNull<List<RuneReforged>>(runeReforgedList)) {
                    if (runeReforged.id == rune_reforged_id) {
                        return runeReforged
                    }
                }

                val errorCode = ErrorCode(404, "Not found")
                throw DataDragonException(errorCode)
            } else {
                val errorCode = ErrorCode(response.code(), response.message())
                throw DataDragonException(errorCode)
            }
        } catch (t: Throwable) {
            throw Throwable(t)
        }
    }

    suspend fun getRuneReforgedDeferred(
            rune_reforged_key: String,
            platform: Platform,
            locale: Locale,
            version: String
    ): RuneReforged? {

        val request = createDataDragonServiceCdn(platform, locale, version).GetRunesReforgedDeferred()

        try {
            val response = request.await()

            if (response.isSuccessful) {
                val runeReforgedList = response.body()

                for (runeReforged in Objects.requireNonNull<List<RuneReforged>>(runeReforgedList)) {
                    if (runeReforged.key == rune_reforged_key) {
                        return runeReforged
                    }
                }

                val errorCode = ErrorCode(404, "Not found")
                throw DataDragonException(errorCode)
            } else {
                val errorCode = ErrorCode(response.code(), response.message())
                throw DataDragonException(errorCode)
            }
        } catch (t: Throwable) {
            throw Throwable(t)
        }
    }

    suspend fun getRuneDeferred(rune_id: Int, platform: Platform, locale: Locale, version: String): Rune? {

        val runeReforgedList = getRuneReforgedListDeferred(platform, locale, version)

        try {
            if (runeReforgedList != null) {
                for (runeReforged in runeReforgedList) {
                    for (i in 0 until runeReforged.slots?.size!!) {
                        for (k in 0 until runeReforged.slots[i]?.runes?.size!!) {
                            if (rune_id == runeReforged.slots[i]?.runes?.get(k)?.id) {
                                return runeReforged.slots[i]?.runes?.get(k)
                            }
                        }
                    }
                }
            }
            val errorCode = ErrorCode(404, "Not found")
            throw DataDragonException(errorCode)
        } catch (t: Throwable) {
            throw Throwable(t)
        }
    }

    suspend fun getRuneDeferred(rune_key: String, platform: Platform, locale: Locale, version: String): Rune? {

        val runeReforgedList = getRuneReforgedListDeferred(platform, locale, version)

        try {
            if (runeReforgedList != null) {
                for (runeReforged in runeReforgedList) {
                    for (i in 0 until runeReforged.slots?.size!!) {
                        for (k in 0 until runeReforged.slots[i]?.runes?.size!!) {
                            if (rune_key == runeReforged.slots[i]?.runes?.get(k)?.key) {
                                return runeReforged.slots[i]?.runes?.get(k)
                            }
                        }

                    }
                }
            }
            val errorCode = ErrorCode(404, "Not found")
            throw DataDragonException(errorCode)
        } catch (t: Throwable) {
            throw Throwable(t)
        }
    }

//SyncMethods_______________________________________________________________________________________________________

    fun getRuneReforgedList(
            platform: Platform, locale: Locale, version: String,
            callback: CallbackFun<List<RuneReforged>>.() -> Unit
    ) {

        val call = createDataDragonServiceCdn(platform, locale, version).GetRunesReforged()

        val callbackFun = CallbackFun<List<RuneReforged>>()
        callback.invoke(callbackFun)

        try {
            val response = call.execute()

            if (response.isSuccessful) {

                val runeReforgedList = response.body()

                runeReforgedList?.sortedWith(compareBy { it.key })

                callbackFun.onSuccess?.invoke(runeReforgedList)
            } else {
                callbackFun.onErrorCode(ErrorCode(response.code(), response.message()))
            }
        } catch (e: IOException) {
            e.printStackTrace()
            callbackFun.onFailure?.invoke(e)
        }

    }

    fun getRuneReforged(
            runeReforgedId: Int, platform: Platform, locale: Locale, version: String,
            callback: CallbackFun<RuneReforged>.() -> Unit
    ) {
        val call = createDataDragonServiceCdn(platform, locale, version).GetRunesReforged()

        val callbackFun = CallbackFun<RuneReforged>()
        callback.invoke(callbackFun)

        try {
            val response = call.execute()

            if (response.isSuccessful) {
                val runeReforgedList = ArrayList(response.body())

                val filteredList = runeReforgedList.filter { it.id == runeReforgedId }

                if (filteredList.isNotEmpty()) {
                    val runeReforged = filteredList.first()
                    callbackFun.onSuccess?.invoke(runeReforged)
                } else {
                    callbackFun.onErrorCode?.invoke(ErrorCode(404, "Not found"))
                }

            } else {
                callbackFun.onErrorCode?.invoke(ErrorCode(response.code(), response.message()))
            }
        } catch (e: IOException) {
            e.printStackTrace()
            callbackFun.onFailure?.invoke(e)
        }

    }

    fun getRuneReforged(
            runeReforgedKey: String, platform: Platform, locale: Locale, version: String,
            callback: CallbackFun<RuneReforged>.() -> Unit
    ) {

        val call = createDataDragonServiceCdn(platform, locale, version).GetRunesReforged()

        val callbackFun = CallbackFun<RuneReforged>()
        callback.invoke(callbackFun)

        try {
            val response = call.execute()

            if (response.isSuccessful) {
                val runeReforgedList = ArrayList(response.body())

                val filteredList = runeReforgedList.filter { it.key == runeReforgedKey }

                if (filteredList.isNotEmpty()) {
                    val runeReforged = filteredList.first()
                    callbackFun.onSuccess?.invoke(runeReforged)
                } else {
                    callbackFun.onErrorCode?.invoke(ErrorCode(404, "Not found"))
                }

            } else {
                callbackFun.onErrorCode?.invoke(ErrorCode(response.code(), response.message()))
            }
        } catch (e: IOException) {
            e.printStackTrace()
            callbackFun.onFailure?.invoke(e)
        }
    }

    fun getRune(
            runeId: Int, platform: Platform, locale: Locale, version: String,
            callback: CallbackFun<Rune>.() -> Unit
    ) {

        val callbackFun = CallbackFun<Rune>()
        callback.invoke(callbackFun)

        getRuneReforgedList(platform, locale, version) {
            onSuccess = {

                var runeFound = false

                if (it != null) {
                    for (runeReforged in it) {
                        for (i in 0 until runeReforged.slots?.size!!) {
                            for (k in 0 until runeReforged.slots[i]?.runes?.size!!) {
                                if (runeId == runeReforged.slots[i]?.runes?.get(k)?.id) {
                                    val rune = runeReforged.slots[i]?.runes?.get(k)
                                    runeFound = true
                                    callbackFun.onSuccess?.invoke(rune)
                                }
                            }
                        }
                    }
                }

                if (!runeFound) {
                    callbackFun.onErrorCode?.invoke(ErrorCode(404, "Not found"))
                }
            }

            onErrorCode = { callbackFun.onErrorCode?.invoke(it) }

            onFailure = { callbackFun.onFailure?.invoke(it) }
        }
    }

    fun getRune(
            runeKey: String, platform: Platform, locale: Locale, version: String,
            callback: CallbackFun<Rune>.() -> Unit
    ) {

        val callbackFun = CallbackFun<Rune>()
        callback.invoke(callbackFun)

        getRuneReforgedList(platform, locale, version) {
            onSuccess = {

                var runeFound = false

                if (it != null) {
                    for (runeReforged in it) {
                        for (i in 0 until runeReforged.slots?.size!!) {
                            for (k in 0 until runeReforged.slots[i]?.runes?.size!!) {
                                if (runeKey == runeReforged.slots[i]?.runes?.get(k)?.key) {
                                    val rune = runeReforged.slots[i]?.runes?.get(k)
                                    runeFound = true
                                    callbackFun.onSuccess?.invoke(rune)
                                }
                            }
                        }
                    }
                }

                if (!runeFound) {
                    callbackFun.onErrorCode?.invoke(ErrorCode(404, "Not found"))
                }
            }

            onErrorCode = { callbackFun.onErrorCode?.invoke(it) }

            onFailure = { callbackFun.onFailure?.invoke(it) }
        }
    }

//AsyncMethods______________________________________________________________________________________________________

    fun getRuneReforgedListAsync(
            platform: Platform, locale: Locale, version: String,
            callback: CallbackFun<List<RuneReforged>>.() -> Unit
    ) {

        val call = createDataDragonServiceCdn(platform, locale, version).GetRunesReforged()

        val callbackFun = CallbackFun<List<RuneReforged>>()
        callback.invoke(callbackFun)

        call.enqueue(object : Callback<List<RuneReforged>> {
            override fun onResponse(call: Call<List<RuneReforged>>, response: Response<List<RuneReforged>>) {
                if (response.isSuccessful) {

                    val runeReforgedList = response.body()

                    runeReforgedList?.sortedWith(compareBy { it.key })

                    callbackFun.onSuccess?.invoke(runeReforgedList)
                } else {
                    callbackFun.onErrorCode?.invoke(ErrorCode(response.code(), response.message()))
                }
            }

            override fun onFailure(call: Call<List<RuneReforged>>, t: Throwable) {
                callbackFun.onFailure?.invoke(t)
            }
        })
    }

    fun getRuneReforgedAsync(
            runeReforgedId: Int, platform: Platform, locale: Locale, version: String,
            callback: CallbackFun<RuneReforged>.() -> Unit
    ) {

        val call = createDataDragonServiceCdn(platform, locale, version).GetRunesReforged()

        val callbackFun = CallbackFun<RuneReforged>()
        callback.invoke(callbackFun)

        call.enqueue(object : Callback<List<RuneReforged>> {
            override fun onResponse(call: Call<List<RuneReforged>>, response: Response<List<RuneReforged>>) {
                if (response.isSuccessful) {
                    val runeReforgedList = ArrayList(response.body())

                    val filteredList = runeReforgedList.filter { it.id == runeReforgedId }

                    if (filteredList.isNotEmpty()) {
                        val runeReforged = filteredList.first()
                        callbackFun.onSuccess?.invoke(runeReforged)
                    } else {
                        callbackFun.onErrorCode?.invoke(ErrorCode(404, "Not found"))
                    }

                } else {
                    callbackFun.onErrorCode?.invoke(ErrorCode(response.code(), response.message()))
                }
            }

            override fun onFailure(call: Call<List<RuneReforged>>, t: Throwable) {
                callbackFun.onFailure?.invoke(t)
            }
        })
    }

    fun getRuneReforgedAsync(
            runeReforgedKey: String, platform: Platform, locale: Locale, version: String,
            callback: CallbackFun<RuneReforged>.() -> Unit
    ) {

        val call = createDataDragonServiceCdn(platform, locale, version).GetRunesReforged()

        val callbackFun = CallbackFun<RuneReforged>()
        callback.invoke(callbackFun)

        call.enqueue(object : Callback<List<RuneReforged>> {
            override fun onResponse(call: Call<List<RuneReforged>>, response: Response<List<RuneReforged>>) {
                if (response.isSuccessful) {
                    val runeReforgedList = ArrayList(response.body())

                    val filteredList = runeReforgedList.filter { it.key == runeReforgedKey }

                    if (filteredList.isNotEmpty()) {
                        val runeReforged = filteredList.first()
                        callbackFun.onSuccess?.invoke(runeReforged)
                    } else {
                        callbackFun.onErrorCode?.invoke(ErrorCode(404, "Not found"))
                    }

                } else {
                    callbackFun.onErrorCode?.invoke(ErrorCode(response.code(), response.message()))
                }
            }

            override fun onFailure(call: Call<List<RuneReforged>>, t: Throwable) {
                callbackFun.onFailure?.invoke(t)
            }
        })
    }

    fun getRuneAsync(
            runeId: Int, platform: Platform, locale: Locale, version: String,
            callback: CallbackFun<Rune>.() -> Unit
    ) {

        val callbackFun = CallbackFun<Rune>()
        callback.invoke(callbackFun)

        getRuneReforgedListAsync(platform, locale, version) {

            onSuccess = {

                var runeFound = false

                if (it != null) {
                    for (runeReforged in it) {
                        for (i in 0 until runeReforged.slots?.size!!) {
                            for (k in 0 until runeReforged.slots[i]?.runes?.size!!) {
                                if (runeId == runeReforged.slots[i]?.runes?.get(k)?.id) {
                                    val rune = runeReforged.slots[i]?.runes?.get(k)
                                    runeFound = true
                                    callbackFun.onSuccess?.invoke(rune)
                                }
                            }
                        }
                    }
                }

                if (!runeFound) {
                    callbackFun.onErrorCode?.invoke(ErrorCode(404, "Not found"))
                }
            }

            onErrorCode = { callbackFun.onErrorCode?.invoke(it) }

            onFailure = { callbackFun.onFailure?.invoke(it) }
        }
    }

    fun getRuneAsync(
            runeKey: String, platform: Platform, locale: Locale, version: String,
            callback: CallbackFun<Rune>.() -> Unit
    ) {

        val callbackFun = CallbackFun<Rune>()
        callback.invoke(callbackFun)

        getRuneReforgedListAsync(platform, locale, version) {

            onSuccess = {

                var runeFound = false

                if (it != null) {
                    for (runeReforged in it) {
                        for (i in 0 until runeReforged.slots?.size!!) {
                            for (k in 0 until runeReforged.slots[i]?.runes?.size!!) {
                                if (runeKey == runeReforged.slots[i]?.runes?.get(k)?.key) {
                                    val rune = runeReforged.slots[i]?.runes?.get(k)
                                    runeFound = true
                                    callbackFun.onSuccess?.invoke(rune)
                                }
                            }
                        }
                    }
                }

                if (!runeFound) {
                    callbackFun.onErrorCode?.invoke(ErrorCode(404, "Not found"))
                }
            }

            onErrorCode = { callbackFun.onErrorCode?.invoke(it) }

            onFailure = { callbackFun.onFailure?.invoke(it) }
        }
    }
}
