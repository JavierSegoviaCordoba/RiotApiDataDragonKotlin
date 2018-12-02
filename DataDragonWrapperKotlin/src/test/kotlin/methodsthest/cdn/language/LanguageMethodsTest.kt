package methodsthest.cdn.language

import com.videumcorp.datadragonwrapperkotlin.datadragon.DataDragon
import com.videumcorp.datadragonwrapperkotlin.datadragon.constants.Platform
import org.junit.Test
import utils.TestParameters

class LanguageMethodsTest {

    @Test
    fun main() {

        val dataDragon = DataDragon(Platform.NA)

        dataDragon.getLanguage(TestParameters.LOCALE1, TestParameters.VERSION) {
            onSuccess = { println(it?.Continue.toString()) }
        }

        dataDragon.getLanguage(TestParameters.LOCALE2, TestParameters.VERSION) {
            onSuccess = { println(it?.Continue.toString()) }
        }
    }
}