package methodsthest.cdn.championfulllist

import com.videumcorp.datadragonwrapperkotlin.datadragon.DataDragon
import com.videumcorp.datadragonwrapperkotlin.datadragon.constants.Platform
import com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.cdn.championfulllist.dto.ChampionFull
import org.junit.Test
import utils.TestParameters
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ExecutionException

class ChampionFullFullListAsyncMethodsTest {

    @Test
    @Throws(ExecutionException::class, InterruptedException::class)
    fun main() {
        val dataDragon = DataDragon(Platform.NA)

        val futureChampionFull1 = CompletableFuture<List<ChampionFull>>()
        val futureChampionFull2 = CompletableFuture<List<ChampionFull>>()

        dataDragon.getChampionFullListAsync(TestParameters.LOCALE1, TestParameters.VERSION) {
            onSuccess = { futureChampionFull1.complete(it) }
        }

        println(futureChampionFull1.get()[0].toString())

        dataDragon.getChampionFullListAsync(TestParameters.LOCALE2, TestParameters.VERSION) {
            onSuccess = { futureChampionFull2.complete(it) }
        }

        println(futureChampionFull2.get()[0].toString())
    }
}