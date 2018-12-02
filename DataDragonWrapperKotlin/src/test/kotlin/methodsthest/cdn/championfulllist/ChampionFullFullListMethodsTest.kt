package methodsthest.cdn.championfulllist

import com.videumcorp.datadragonwrapperkotlin.datadragon.DataDragon
import com.videumcorp.datadragonwrapperkotlin.datadragon.constants.Platform
import org.junit.Test
import utils.TestParameters

class ChampionFullFullListMethodsTest {

    @Test
    fun main() {
        val dataDragon = DataDragon(Platform.NA)

        dataDragon.getChampionFullList(TestParameters.LOCALE1, TestParameters.VERSION) {
            onSuccess = { println(it?.get(0).toString()) }
        }

        dataDragon.getChampionFullList(TestParameters.LOCALE2, TestParameters.VERSION) {
            onSuccess = { println(it?.get(0).toString()) }
        }
    }
}