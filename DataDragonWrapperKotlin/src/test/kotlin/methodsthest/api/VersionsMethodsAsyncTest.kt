package methodsthest.api

import com.videumcorp.datadragonwrapperkotlin.datadragon.DataDragon
import com.videumcorp.datadragonwrapperkotlin.datadragon.constants.Platform
import org.junit.Test
import java.util.concurrent.CompletableFuture

class VersionsMethodsAsyncTest {

    @Test
    fun main() {

        val dataDragon = DataDragon(Platform.NA)

        val future = CompletableFuture<List<String>>()

        dataDragon.getVersionsListAsync {
            onSuccess = { future.complete(it) }

            onErrorCode = { println(it.toString()) }

            onFailure = { println(it.message) }
        }

        println(future.get().toString())
    }
}