package methodsthest.cdn.champion

import com.videumcorp.datadragonwrapperkotlin.datadragon.DataDragon
import com.videumcorp.datadragonwrapperkotlin.datadragon.constants.Platform
import com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.cdn.champion.dto.Champion
import com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.cdn.championshortlist.dto.ChampionKeyId
import com.videumcorp.datadragonwrapperkotlin.datadragon.utils.ErrorCode
import org.junit.Test
import utils.TestParameters
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ExecutionException

class ChampionMethodsAsyncTest {

    @Test
    @Throws(ExecutionException::class, InterruptedException::class)
    fun main() {

        val dataDragon = DataDragon(Platform.NA)

        val futureChampion = CompletableFuture<Champion>()
        val futureChampion1 = CompletableFuture<Champion>()
        val futureChampion2 = CompletableFuture<Champion>()
        val futureChampion3 = CompletableFuture<Champion>()

        val futureChampionKey = CompletableFuture<Int>()
        val futureChampionId = CompletableFuture<String>()
        val futureChampionKeyIdList = CompletableFuture<List<ChampionKeyId>>()

        val futureErrorCode = CompletableFuture<ErrorCode>()
        val futureErrorCode1 = CompletableFuture<ErrorCode>()
        val futureErrorCode2 = CompletableFuture<ErrorCode>()

        dataDragon.getChampionAsync("Graves", TestParameters.LOCALE1, TestParameters.VERSION) {

            onSuccess = { futureChampion.complete(it) }
        }

        println("${futureChampion.get().name} ${futureChampion.get()}")

        dataDragon.getChampionAsync("ErrorChamp", TestParameters.LOCALE1, TestParameters.VERSION) {

            onErrorCode = { futureErrorCode.complete(it) }
        }

        println(futureErrorCode.get().toString())

        dataDragon.getChampionAsync("Graves", TestParameters.LOCALE2, TestParameters.VERSION) {

            onSuccess = { futureChampion1.complete(it) }
        }

        println("${futureChampion1.get().name} ${futureChampion1.get()}")


        dataDragon.getChampionAsync(2, TestParameters.LOCALE1, TestParameters.VERSION) {

            onSuccess = { futureChampion2.complete(it) }
        }

        println("${futureChampion2.get().name} ${futureChampion2.get()}")


        dataDragon.getChampionAsync(2, TestParameters.LOCALE2, TestParameters.VERSION) {

            onSuccess = { futureChampion3.complete(it) }
        }

        println("${futureChampion3.get().name} ${futureChampion3.get()}")


        dataDragon.getChampionKeyAsync("Yasuo", TestParameters.LOCALE2, TestParameters.VERSION) {

            onSuccess = { futureChampionKey.complete(it) }
        }

        println(futureChampionKey.get().toString())

        dataDragon.getChampionKeyAsync("ErrorChamp", TestParameters.LOCALE2, TestParameters.VERSION) {

            onErrorCode = { futureErrorCode1.complete(it) }
        }

        println(futureErrorCode1.get().toString())

        dataDragon.getChampionIdAsync(157, TestParameters.LOCALE2, TestParameters.VERSION) {

            onSuccess = { futureChampionId.complete(it) }
        }

        println(futureChampionId.get().toString())

        dataDragon.getChampionIdAsync(99233, TestParameters.LOCALE2, TestParameters.VERSION) {

            onErrorCode = { futureErrorCode2.complete(it) }
        }

        println(futureErrorCode2.get().toString())

        dataDragon.getChampionKeyIdListAsync(TestParameters.LOCALE2, TestParameters.VERSION) {

            onSuccess = { futureChampionKeyIdList.complete(it) }
        }

        println(futureChampionKeyIdList.get().toString())
    }
}