package methodsthest.cdn.championfulllist

import com.videumcorp.datadragonwrapperkotlin.datadragon.DataDragon
import com.videumcorp.datadragonwrapperkotlin.datadragon.constants.Locale
import com.videumcorp.datadragonwrapperkotlin.datadragon.constants.Platform
import kotlinx.coroutines.runBlocking
import org.junit.Test

class ChampionFullFullListMethodsCoroutinesTest {

    @Test
    fun main() {
        val dataDragon = DataDragon(Platform.NA)

        runBlocking {
            println(dataDragon.getChampionFullListDeferred(Locale.EN_US, "8.21.1")?.size)
            println(dataDragon.getChampionFullListDeferred(Locale.EN_US, "8.21.1")?.get(0))
            println(dataDragon.getChampionFullListDeferred(Locale.ES_ES, "8.21.1")?.get(0))
        }

        println("\nthreads:" + Thread.activeCount())
    }
}