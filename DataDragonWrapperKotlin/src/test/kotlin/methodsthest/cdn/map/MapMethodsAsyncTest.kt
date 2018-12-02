package methodsthest.cdn.map

import com.videumcorp.datadragonwrapperkotlin.datadragon.DataDragon
import com.videumcorp.datadragonwrapperkotlin.datadragon.constants.Platform
import com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.cdn.map.dto.Map
import org.junit.Test
import utils.TestParameters
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ExecutionException

class MapMethodsAsyncTest {

    @Test
    @Throws(ExecutionException::class, InterruptedException::class)
    fun main() {

        val dataDragon = DataDragon(Platform.NA)

        val futureMap1 = CompletableFuture<Map>()
        val futureMap2 = CompletableFuture<Map>()
        val futureMapList1 = CompletableFuture<List<Map>>()
        val futureMapList2 = CompletableFuture<List<Map>>()

        dataDragon.getMapAsync(10, TestParameters.LOCALE1, TestParameters.VERSION) {
            onSuccess = { futureMap1.complete(it) }
        }

        println(futureMap1.get().toString())

        dataDragon.getMapAsync(10, TestParameters.LOCALE2, TestParameters.VERSION) {
            onSuccess = { futureMap2.complete(it) }
        }

        println(futureMap2.get().toString())

        dataDragon.getMapListAsync(TestParameters.LOCALE1, TestParameters.VERSION) {
            onSuccess = { futureMapList1.complete(it) }
        }

        println(futureMapList1.get().toString())


        dataDragon.getMapListAsync(TestParameters.LOCALE2, TestParameters.VERSION) {
            onSuccess = { futureMapList2.complete(it) }
        }

        println(futureMapList2.get().toString())
    }
}