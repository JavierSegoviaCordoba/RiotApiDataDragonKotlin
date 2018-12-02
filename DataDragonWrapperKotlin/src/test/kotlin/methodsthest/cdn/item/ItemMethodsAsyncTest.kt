package methodsthest.cdn.item

import com.videumcorp.datadragonwrapperkotlin.datadragon.DataDragon
import com.videumcorp.datadragonwrapperkotlin.datadragon.constants.Platform
import com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.cdn.item.dto.Item
import org.junit.Test
import utils.TestParameters
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ExecutionException

class ItemMethodsAsyncTest {

    @Test
    @Throws(ExecutionException::class, InterruptedException::class)
    fun main() {

        val dataDragon = DataDragon(Platform.NA)

        val futureItem1 = CompletableFuture<Item?>()
        val futureItem2 = CompletableFuture<Item?>()
        val futureItemList1 = CompletableFuture<List<Item?>?>()
        val futureItemList2 = CompletableFuture<List<Item?>?>()

        dataDragon.getItemAsync(1001, TestParameters.LOCALE1, TestParameters.VERSION) {
            onSuccess = { futureItem1.complete(it) }
        }

        println(futureItem1.get().toString())


        dataDragon.getItemAsync(1001, TestParameters.LOCALE2, TestParameters.VERSION) {
            onSuccess = { futureItem2.complete(it) }
        }

        println(futureItem2.get().toString())

        dataDragon.getItemListAsync(TestParameters.LOCALE1, TestParameters.VERSION) {
            onSuccess = { futureItemList1.complete(it) }
        }

        println(futureItemList1.get()?.get(2).toString())

        dataDragon.getItemListAsync(TestParameters.LOCALE2, TestParameters.VERSION) {
            onSuccess = { futureItemList2.complete(it) }
        }

        println(futureItemList2.get()?.get(2).toString())
    }
}