package methodsthest.cdn.championshortlist

import com.videumcorp.datadragonwrapperkotlin.datadragon.DataDragon
import com.videumcorp.datadragonwrapperkotlin.datadragon.constants.Platform
import org.junit.Test
import utils.TestParameters

class ChampionShortListMethodsTest {

    @Test
    fun main() {

        val dataDragon = DataDragon(Platform.NA)

        dataDragon.getChampionShortList(TestParameters.LOCALE1, TestParameters.VERSION) {

            onSuccess = { println(it.toString()) }

            onErrorCode = { println(it.toString()) }

            onFailure = { println(it.message) }
        }

        dataDragon.getChampionShortList(TestParameters.LOCALE2, TestParameters.VERSION) {

            onSuccess = { println(it.toString()) }

            onErrorCode = { println(it.toString()) }

            onFailure = { println(it.message) }
        }
    }
}