package methodsthest.cdn.languages

import com.videumcorp.datadragonwrapperkotlin.datadragon.DataDragon
import com.videumcorp.datadragonwrapperkotlin.datadragon.constants.Platform
import org.junit.Test

class LanguagesMethodsTest {

    @Test
    fun main() {

        val dataDragon = DataDragon(Platform.NA)

        dataDragon.getLanguagesList {
            onSuccess = { println(it.toString()) }
        }
    }
}