package methodsthest.cdn.profileicon

import com.videumcorp.datadragonwrapperkotlin.datadragon.DataDragon
import com.videumcorp.datadragonwrapperkotlin.datadragon.constants.Platform
import org.junit.Test
import utils.TestParameters

class ProfileIconMethodsTest {

    @Test
    fun main() {

        val dataDragon = DataDragon(Platform.NA)

        dataDragon.getProfileIcon(10, TestParameters.LOCALE1, TestParameters.VERSION) {
            onSuccess = { println(it.toString()) }
        }

        dataDragon.getProfileIcon(99999, TestParameters.LOCALE1, TestParameters.VERSION) {
            onSuccess = { println(it.toString()) }
        }

        dataDragon.getProfileIcon(10, TestParameters.LOCALE2, TestParameters.VERSION) {
            onSuccess = { println(it.toString()) }
        }

        dataDragon.getProfileIconList(TestParameters.LOCALE1, TestParameters.VERSION) {
            onSuccess = { println(it.toString()) }
        }

        dataDragon.getProfileIconList(TestParameters.LOCALE2, TestParameters.VERSION) {
            onSuccess = { println(it.toString()) }
        }
    }
}