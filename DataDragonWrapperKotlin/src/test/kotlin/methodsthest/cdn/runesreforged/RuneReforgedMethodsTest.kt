package methodsthest.cdn.runesreforged

import com.videumcorp.datadragonwrapperkotlin.datadragon.DataDragon
import com.videumcorp.datadragonwrapperkotlin.datadragon.constants.Platform
import org.junit.Test
import utils.TestParameters

class RuneReforgedMethodsTest {

    @Test
    fun main() {

        val dataDragon = DataDragon(Platform.NA)

        dataDragon.getRuneReforgedList(TestParameters.LOCALE1, TestParameters.VERSION) {
            onSuccess = { println(it.toString()) }
        }

        dataDragon.getRuneReforgedList(TestParameters.LOCALE2, TestParameters.VERSION) {
            onSuccess = { println(it.toString()) }
        }

        dataDragon.getRuneReforged(8300, TestParameters.LOCALE1, TestParameters.VERSION) {
            onSuccess = { println(it.toString()) }
        }

        dataDragon.getRuneReforged(999999, TestParameters.LOCALE1, TestParameters.VERSION) {
            onErrorCode = { println(it.toString()) }
        }

        dataDragon.getRuneReforged(8300, TestParameters.LOCALE2, TestParameters.VERSION) {
            onSuccess = { println(it.toString()) }
        }

        dataDragon.getRuneReforged("Sorcery", TestParameters.LOCALE1, TestParameters.VERSION) {
            onSuccess = { println(it.toString()) }
        }

        dataDragon.getRuneReforged("ErrorRune", TestParameters.LOCALE1, TestParameters.VERSION) {
            onErrorCode = { println(it.toString()) }
        }

        dataDragon.getRuneReforged("Sorcery", TestParameters.LOCALE2, TestParameters.VERSION) {
            onSuccess = { println(it.toString()) }
        }

        dataDragon.getRune(8112, TestParameters.LOCALE1, TestParameters.VERSION) {
            onSuccess = { println(it.toString()) }
        }

        dataDragon.getRune(999999, TestParameters.LOCALE1, TestParameters.VERSION) {
            onErrorCode = { println(it.toString()) }
        }

        dataDragon.getRune(8112, TestParameters.LOCALE2, TestParameters.VERSION) {
            onSuccess = { println(it.toString()) }
        }

        dataDragon.getRune("PressTheAttack", TestParameters.LOCALE1, TestParameters.VERSION) {
            onSuccess = { println(it.toString()) }
        }

        dataDragon.getRune("ErrorRune", TestParameters.LOCALE1, TestParameters.VERSION) {
            onErrorCode = { println(it.toString()) }
        }

        dataDragon.getRune("PressTheAttack", TestParameters.LOCALE2, TestParameters.VERSION) {
            onSuccess = { println(it.toString()) }
        }
    }
}