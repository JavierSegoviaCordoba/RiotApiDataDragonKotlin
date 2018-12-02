package methodsthest.realms

import com.videumcorp.datadragonwrapperkotlin.datadragon.DataDragon
import com.videumcorp.datadragonwrapperkotlin.datadragon.constants.Platform
import com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.realms.realms.dto.Realms
import org.junit.Test
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ExecutionException

class RealmsMethodsAsyncTest {

    @Test
    @Throws(ExecutionException::class, InterruptedException::class)
    fun main() {

        val dataDragon = DataDragon(Platform.NA)

        val completableFuture = CompletableFuture<Realms>()

        dataDragon.getRealmsAsync {
            onSuccess = { completableFuture.complete(it) }

            onErrorCode = { println(it.toString()) }

            onFailure = { println(it.message) }
        }

        println(completableFuture.get().toString())
    }
}