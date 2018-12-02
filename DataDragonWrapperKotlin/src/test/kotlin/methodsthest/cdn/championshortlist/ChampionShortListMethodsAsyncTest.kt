package methodsthest.cdn.championshortlist

import com.videumcorp.datadragonwrapperkotlin.datadragon.DataDragon
import com.videumcorp.datadragonwrapperkotlin.datadragon.constants.Platform
import com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.cdn.championshortlist.dto.ChampionShort
import org.junit.Test
import utils.TestParameters
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ExecutionException

class ChampionShortListMethodsAsyncTest {

    @Test
    @Throws(ExecutionException::class, InterruptedException::class)
    fun main() {

        val dataDragon = DataDragon(Platform.NA)

        val futureChampionShort1 = CompletableFuture<List<ChampionShort>>()
        val futureChampionShort2 = CompletableFuture<List<ChampionShort>>()

        dataDragon.getChampionShortListAsync(TestParameters.LOCALE1, TestParameters.VERSION) {

            onSuccess = { futureChampionShort1.complete(it) }

            onErrorCode = { println(it.toString()) }

            onFailure = { println(it.message) }
        }

        println(futureChampionShort1.get().toString())

        dataDragon.getChampionShortListAsync(TestParameters.LOCALE2, TestParameters.VERSION) {

            onSuccess = { futureChampionShort2.complete(it) }

            onErrorCode = { println(it.toString()) }

            onFailure = { println(it.message) }
        }

        println(futureChampionShort2.get().toString())
    }
}