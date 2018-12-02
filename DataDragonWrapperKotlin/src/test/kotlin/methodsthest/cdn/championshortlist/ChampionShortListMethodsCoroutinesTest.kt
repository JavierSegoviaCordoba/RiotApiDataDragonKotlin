package methodsthest.cdn.championshortlist

import com.videumcorp.datadragonwrapperkotlin.datadragon.DataDragon
import com.videumcorp.datadragonwrapperkotlin.datadragon.constants.Platform
import kotlinx.coroutines.runBlocking
import org.junit.Test
import utils.TestParameters

class ChampionShortListMethodsCoroutinesTest {

    @Test
    fun main() {

        val dataDragon = DataDragon(Platform.NA)

        runBlocking {
            println(dataDragon.getChampionShortListDeferred(TestParameters.LOCALE1, TestParameters.VERSION))
            println(dataDragon.getChampionShortListDeferred(TestParameters.LOCALE2, TestParameters.VERSION))
        }
    }
}