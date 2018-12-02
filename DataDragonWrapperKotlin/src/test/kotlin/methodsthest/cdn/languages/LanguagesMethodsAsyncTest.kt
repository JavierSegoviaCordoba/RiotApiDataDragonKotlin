package methodsthest.cdn.languages

import com.videumcorp.datadragonwrapperkotlin.datadragon.DataDragon
import com.videumcorp.datadragonwrapperkotlin.datadragon.constants.Platform
import org.junit.Test
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ExecutionException

class LanguagesMethodsAsyncTest {

    @Test
    @Throws(ExecutionException::class, InterruptedException::class)
    fun main() {

        val dataDragon = DataDragon(Platform.NA)

        val futureLanguagesList = CompletableFuture<List<String>>()


        dataDragon.getLanguagesListAsync {
            onSuccess = { futureLanguagesList.complete(it) }
        }

        println(futureLanguagesList.get())
    }
}