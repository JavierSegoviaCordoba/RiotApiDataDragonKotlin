package com.videumcorp.riotapidatadragon

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.videumcorp.datadragonwrapperkotlin.datadragon.DataDragon
import com.videumcorp.datadragonwrapperkotlin.datadragon.constants.Locale
import com.videumcorp.datadragonwrapperkotlin.datadragon.constants.Platform
import com.videumcorp.datadragonwrapperkotlin.datadragon.utils.DataDragonException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dataDragon = DataDragon(Platform.NA)

        /*runBlocking(Dispatchers.Main) {
            dataDragon.getChampion("Graves", Locale.ES_ES, "8.22.1") {
                onSuccess = { println(it.toString()) }

                onErrorCode = { println(it.toString()) }

                onFailure = { println(it.message) }
            }
        }*/

        dataDragon.getChampionAsync("Graves", Locale.ES_ES, "8.22.1") {
            onSuccess = { println(it.toString()) }

            onErrorCode = { println("${it.code}: ${it.message}") }

            onFailure = { println(it.message) }
        }

        GlobalScope.launch(Dispatchers.Main) {
            try {
                val champion = dataDragon.getChampionDeferred("Graves", Locale.ES_ES, "8.22.1")
                println(champion.toString())
            } catch (t: Throwable) {
                when (t) {
                    is DataDragonException -> println(t.message) // Example: '403: Forbidden'
                    else -> println(t.message)
                }
            }
        }

    }
}
