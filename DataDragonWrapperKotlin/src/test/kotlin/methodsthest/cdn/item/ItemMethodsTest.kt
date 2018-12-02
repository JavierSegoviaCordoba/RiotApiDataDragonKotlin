package methodsthest.cdn.item

import com.videumcorp.datadragonwrapperkotlin.datadragon.DataDragon
import com.videumcorp.datadragonwrapperkotlin.datadragon.constants.Platform
import org.junit.Test
import utils.TestParameters

class ItemMethodsTest {

    @Test
    fun main() {

        val dataDragon = DataDragon(Platform.NA)

        dataDragon.getItem(1001, TestParameters.LOCALE1, TestParameters.VERSION) {
            onSuccess = { println(it.toString()) }
        }

        dataDragon.getItem(999999, TestParameters.LOCALE1, TestParameters.VERSION) {
            onErrorCode = { println(it.toString()) }
        }

        dataDragon.getItem(1001, TestParameters.LOCALE2, TestParameters.VERSION) {
            onSuccess = { println(it.toString()) }
        }

        dataDragon.getItemList(TestParameters.LOCALE1, TestParameters.VERSION) {
            onSuccess = { println(it?.get(2).toString()) }
        }

        dataDragon.getItemList(TestParameters.LOCALE2, TestParameters.VERSION) {
            onSuccess = { println(it?.get(2).toString()) }
        }
    }
}