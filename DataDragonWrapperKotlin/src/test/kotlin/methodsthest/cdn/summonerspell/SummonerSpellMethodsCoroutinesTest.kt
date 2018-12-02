package methodsthest.cdn.summonerspell

import com.videumcorp.datadragonwrapperkotlin.datadragon.DataDragon
import com.videumcorp.datadragonwrapperkotlin.datadragon.constants.Locale
import com.videumcorp.datadragonwrapperkotlin.datadragon.constants.Platform
import kotlinx.coroutines.runBlocking
import org.junit.Test

class SummonerSpellMethodsCoroutinesTest {

    @Test
    fun main() {

        val dataDragon = DataDragon(Platform.NA)

        runBlocking {
            println(dataDragon.getSummonerSpellListDeferred(Locale.EN_US, "8.21.1"))
            println(dataDragon.getSummonerSpellListDeferred(Locale.ES_ES, "8.21.1"))
            println(dataDragon.getSummonerSpellDeferred("SummonerDot", Locale.EN_US, "8.21.1"))
            println(dataDragon.getSummonerSpellDeferred("SummonerDot", Locale.ES_ES, "8.21.1"))
        }
    }
}