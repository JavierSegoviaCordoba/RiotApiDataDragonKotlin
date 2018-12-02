package methodsthest.cdn.map

import com.videumcorp.datadragonwrapperkotlin.datadragon.DataDragon
import com.videumcorp.datadragonwrapperkotlin.datadragon.constants.Platform
import org.junit.Test
import utils.TestParameters

class MapMethodsTest {

    @Test
    fun main() {

        val dataDragon = DataDragon(Platform.NA)

        dataDragon.getMap(10, TestParameters.LOCALE1, TestParameters.VERSION) {
            onSuccess = { println(it.toString()) }
        }

        dataDragon.getMap(999, TestParameters.LOCALE1, TestParameters.VERSION) {
            onErrorCode = { println(it.toString()) }
        }

        dataDragon.getMap(10, TestParameters.LOCALE2, TestParameters.VERSION) {
            onSuccess = { println(it.toString()) }
        }

        dataDragon.getMapList(TestParameters.LOCALE1, TestParameters.VERSION) {
            onSuccess = { println(it.toString()) }
        }

        dataDragon.getMapList(TestParameters.LOCALE2, TestParameters.VERSION) {
            onSuccess = { println(it.toString()) }
        }
    }
}