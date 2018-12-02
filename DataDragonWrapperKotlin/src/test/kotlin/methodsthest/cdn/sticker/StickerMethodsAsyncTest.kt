package methodsthest.cdn.sticker

import com.videumcorp.datadragonwrapperkotlin.datadragon.DataDragon
import com.videumcorp.datadragonwrapperkotlin.datadragon.constants.Platform
import com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.cdn.sticker.dto.Sticker
import org.junit.Test
import utils.TestParameters
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ExecutionException

class StickerMethodsAsyncTest {

    @Test
    @Throws(ExecutionException::class, InterruptedException::class)
    fun main() {

        val dataDragon = DataDragon(Platform.NA)

        val completableFuture1 = CompletableFuture<List<Sticker>>()
        val completableFuture2 = CompletableFuture<List<Sticker>>()
        val completableFuture3 = CompletableFuture<Sticker>()
        val completableFuture4 = CompletableFuture<Sticker>()

        dataDragon.getStickerListAsync(TestParameters.LOCALE1, TestParameters.VERSION) {
            onSuccess = { completableFuture1.complete(it) }
        }

        println(completableFuture1.get().toString())

        dataDragon.getStickerListAsync(TestParameters.LOCALE2, TestParameters.VERSION) {
            onSuccess = { completableFuture2.complete(it) }
        }

        println(completableFuture2.get().toString())

        dataDragon.getStickerAsync("poro-angry", TestParameters.LOCALE1, TestParameters.VERSION) {
            onSuccess = { completableFuture3.complete(it) }
        }

        println(completableFuture3.get().toString())

        dataDragon.getStickerAsync("poro-angry", TestParameters.LOCALE2, TestParameters.VERSION) {
            onSuccess = { completableFuture4.complete(it) }
        }

        println(completableFuture4.get().toString())
    }
}