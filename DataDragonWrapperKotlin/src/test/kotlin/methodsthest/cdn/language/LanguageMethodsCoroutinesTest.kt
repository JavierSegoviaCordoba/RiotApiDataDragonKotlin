package methodsthest.cdn.language

import com.videumcorp.datadragonwrapperkotlin.datadragon.DataDragon
import com.videumcorp.datadragonwrapperkotlin.datadragon.constants.Locale
import com.videumcorp.datadragonwrapperkotlin.datadragon.constants.Platform
import kotlinx.coroutines.runBlocking
import org.junit.Test

class LanguageMethodsCoroutinesTest {

    @Test
    fun main() {

        val dataDragon = DataDragon(Platform.NA)

        runBlocking {
            println(dataDragon.getLanguageDeferred(Locale.EN_US, "8.21.1"))
            println(dataDragon.getLanguageDeferred(Locale.ES_ES, "8.21.1"))
        }
    }
}