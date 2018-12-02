package methodsthest.cdn.runesreforged

import com.videumcorp.datadragonwrapperkotlin.datadragon.DataDragon
import com.videumcorp.datadragonwrapperkotlin.datadragon.constants.Platform
import com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.cdn.runesreforged.dto.Rune
import com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.cdn.runesreforged.dto.RuneReforged
import org.junit.Test
import utils.TestParameters
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ExecutionException

class RuneReforgedMethodsAsyncTest {

    @Test
    @Throws(ExecutionException::class, InterruptedException::class)
    fun main() {

        val dataDragon = DataDragon(Platform.NA)

        val futureRuneReforgedList1 = CompletableFuture<List<RuneReforged>>()
        val futureRuneReforgedList2 = CompletableFuture<List<RuneReforged>>()
        val futureRuneReforged1 = CompletableFuture<RuneReforged>()
        val futureRuneReforged2 = CompletableFuture<RuneReforged>()
        val futureRuneReforged3 = CompletableFuture<RuneReforged>()
        val futureRuneReforged4 = CompletableFuture<RuneReforged>()
        val futureRune1 = CompletableFuture<Rune>()
        val futureRune2 = CompletableFuture<Rune>()
        val futureRune3 = CompletableFuture<Rune>()
        val futureRune4 = CompletableFuture<Rune>()

        dataDragon.getRuneReforgedListAsync(TestParameters.LOCALE1, TestParameters.VERSION) {
            onSuccess = { futureRuneReforgedList1.complete(it) }
        }

        println(futureRuneReforgedList1.get()?.toString())

        dataDragon.getRuneReforgedListAsync(TestParameters.LOCALE2, TestParameters.VERSION) {
            onSuccess = { futureRuneReforgedList2.complete(it) }
        }

        println(futureRuneReforgedList2.get()?.toString())

        dataDragon.getRuneReforgedAsync(8000, TestParameters.LOCALE1, TestParameters.VERSION) {
            onSuccess = { futureRuneReforged1.complete(it) }
        }

        println(futureRuneReforged1.get()?.toString())

        dataDragon.getRuneReforgedAsync(8000, TestParameters.LOCALE2, TestParameters.VERSION) {
            onSuccess = { futureRuneReforged2.complete(it) }
        }

        println(futureRuneReforged2.get()?.toString())

        dataDragon.getRuneReforgedAsync("Resolve", TestParameters.LOCALE1, TestParameters.VERSION) {
            onSuccess = { futureRuneReforged3.complete(it) }
        }

        println(futureRuneReforged3.get()?.toString())

        dataDragon.getRuneReforgedAsync("Resolve", TestParameters.LOCALE2, TestParameters.VERSION) {
            onSuccess = { futureRuneReforged4.complete(it) }
        }

        println(futureRuneReforged4.get()?.toString())

        dataDragon.getRuneAsync(8112, TestParameters.LOCALE1, TestParameters.VERSION) {
            onSuccess = { futureRune1.complete(it) }
        }

        println(futureRune1.get()?.toString())

        dataDragon.getRuneAsync(8112, TestParameters.LOCALE2, TestParameters.VERSION) {
            onSuccess = { futureRune2.complete(it) }
        }

        println(futureRune2.get()?.toString())

        dataDragon.getRuneAsync("PressTheAttack", TestParameters.LOCALE1, TestParameters.VERSION) {
            onSuccess = { futureRune3.complete(it) }
        }

        println(futureRune3.get()?.toString())

        dataDragon.getRuneAsync("PressTheAttack", TestParameters.LOCALE2, TestParameters.VERSION) {
            onSuccess = { futureRune4.complete(it) }
        }

        println(futureRune4.get()?.toString())
    }
}