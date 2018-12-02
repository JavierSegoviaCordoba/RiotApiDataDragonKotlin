package methodsthest.cdn.summonerspell

import com.videumcorp.datadragonwrapperkotlin.datadragon.DataDragon
import com.videumcorp.datadragonwrapperkotlin.datadragon.constants.Platform
import com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.cdn.summonerspell.dto.SummonerSpell
import org.junit.Test
import utils.TestParameters
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ExecutionException

class SummonerSpellMethodsAsyncTest {

    @Test
    @Throws(ExecutionException::class, InterruptedException::class)
    fun main() {

        val dataDragon = DataDragon(Platform.NA)

        val completableFuture1 = CompletableFuture<List<SummonerSpell>>()
        val completableFuture2 = CompletableFuture<List<SummonerSpell>>()
        val completableFuture3 = CompletableFuture<SummonerSpell>()
        val completableFuture4 = CompletableFuture<SummonerSpell>()

        dataDragon.getSummonerSpellListAsync(TestParameters.LOCALE1, TestParameters.VERSION) {
            onSuccess = { completableFuture1.complete(it) }
        }

        println(completableFuture1.get().toString())

        dataDragon.getSummonerSpellListAsync(TestParameters.LOCALE2, TestParameters.VERSION) {
            onSuccess = { completableFuture2.complete(it) }
        }

        println(completableFuture2.get().toString())

        dataDragon.getSummonerSpellAsync("SummonerDot", TestParameters.LOCALE1, TestParameters.VERSION) {
            onSuccess = { completableFuture3.complete(it) }
        }

        println(completableFuture3.get().toString())

        dataDragon.getSummonerSpellAsync("SummonerDot", TestParameters.LOCALE2, TestParameters.VERSION) {
            onSuccess = { completableFuture4.complete(it) }
        }

        println(completableFuture4.get().toString())
    }
}