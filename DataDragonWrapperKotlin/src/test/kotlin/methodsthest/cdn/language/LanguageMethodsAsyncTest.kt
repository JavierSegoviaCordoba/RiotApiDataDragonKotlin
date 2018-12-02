package methodsthest.cdn.language

import com.videumcorp.datadragonwrapperkotlin.datadragon.DataDragon
import com.videumcorp.datadragonwrapperkotlin.datadragon.constants.Platform
import com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.cdn.language.dto.Language
import org.junit.Test
import utils.TestParameters
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ExecutionException

class LanguageMethodsAsyncTest {

    @Test
    @Throws(ExecutionException::class, InterruptedException::class)
    fun main() {

        val dataDragon = DataDragon(Platform.NA)

        val futureLanguage1 = CompletableFuture<Language>()
        val futureLanguage2 = CompletableFuture<Language>()

        dataDragon.getLanguageAsync(TestParameters.LOCALE1, TestParameters.VERSION) {
            onSuccess = { futureLanguage1.complete(it) }
        }

        println(futureLanguage1.get()?.Continue.toString())

        dataDragon.getLanguageAsync(TestParameters.LOCALE2, TestParameters.VERSION) {
            onSuccess = { futureLanguage2.complete(it) }
        }

        println(futureLanguage2.get().Continue.toString())
    }
}