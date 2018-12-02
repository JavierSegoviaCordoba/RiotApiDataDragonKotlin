package methodsthest.cdn.champion

import com.videumcorp.datadragonwrapperkotlin.datadragon.DataDragon
import com.videumcorp.datadragonwrapperkotlin.datadragon.constants.Platform
import org.junit.Test
import utils.TestParameters

class ChampionMethodsTest {

    @Test
    fun main() {

        val dataDragon = DataDragon(Platform.NA)

        dataDragon.getChampion("Graves", TestParameters.LOCALE1, TestParameters.VERSION) {
            onSuccess = { println(it.toString()) }

            onErrorCode = { println(it.toString()) }

            onFailure = { println(it.message) }
        }

        dataDragon.getChampion("Graves", TestParameters.LOCALE2, TestParameters.VERSION) {
            onSuccess = { println(it.toString()) }

            onErrorCode = { println(it.toString()) }

            onFailure = { println(it.message) }
        }

        dataDragon.getChampion(2, TestParameters.LOCALE1, TestParameters.VERSION) {
            onSuccess = { println(it.toString()) }

            onErrorCode = { println(it.toString()) }

            onFailure = { println(it.message) }
        }

        dataDragon.getChampion(999999, TestParameters.LOCALE1, TestParameters.VERSION) {
            onSuccess = { println(it.toString()) }

            onErrorCode = { println(it.toString()) }

            onFailure = { println(it.message) }
        }

        dataDragon.getChampion(2, TestParameters.LOCALE2, TestParameters.VERSION) {
            onSuccess = { println(it.toString()) }

            onErrorCode = { println(it.toString()) }

            onFailure = { println(it.message) }
        }

        dataDragon.getChampionKey("Yasuo", TestParameters.LOCALE1, TestParameters.VERSION) {
            onSuccess = { println(it.toString()) }

            onErrorCode = { println(it.toString()) }

            onFailure = { println(it.message) }
        }

        dataDragon.getChampionId(157, TestParameters.LOCALE1, TestParameters.VERSION) {
            onSuccess = { println(it.toString()) }

            onErrorCode = { println(it.toString()) }

            onFailure = { println(it.message) }
        }

        dataDragon.getChampionKeyIdList(TestParameters.LOCALE1, TestParameters.VERSION) {
            onSuccess = { println(it.toString()) }

            onErrorCode = { println(it.toString()) }

            onFailure = { println(it.message) }
        }
    }
}