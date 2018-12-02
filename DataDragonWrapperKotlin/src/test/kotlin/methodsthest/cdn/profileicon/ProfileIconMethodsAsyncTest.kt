package methodsthest.cdn.profileicon

import com.videumcorp.datadragonwrapperkotlin.datadragon.DataDragon
import com.videumcorp.datadragonwrapperkotlin.datadragon.constants.Platform
import com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.cdn.profileicon.dto.ProfileIcon
import org.junit.Test
import utils.TestParameters
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ExecutionException

class ProfileIconMethodsAsyncTest {

    @Test
    @Throws(ExecutionException::class, InterruptedException::class)
    fun main() {

        val dataDragon = DataDragon(Platform.NA)

        val futureProfileIcon1 = CompletableFuture<ProfileIcon?>()
        val futureProfileIcon2 = CompletableFuture<ProfileIcon?>()
        val futureProfileIconList1 = CompletableFuture<List<ProfileIcon?>?>()
        val futureProfileIconList2 = CompletableFuture<List<ProfileIcon?>?>()

        dataDragon.getProfileIconAsync(10, TestParameters.LOCALE1, TestParameters.VERSION) {
            onSuccess = { futureProfileIcon1.complete(it) }
        }

        println(futureProfileIcon1.get().toString())

        dataDragon.getProfileIconAsync(10, TestParameters.LOCALE2, TestParameters.VERSION) {
            onSuccess = { futureProfileIcon2.complete(it) }
        }

        println(futureProfileIcon2.get().toString())

        dataDragon.getProfileIconListAsync(TestParameters.LOCALE1, TestParameters.VERSION) {
            onSuccess = { futureProfileIconList1.complete(it) }
        }

        println(futureProfileIconList1.get().toString())

        dataDragon.getProfileIconListAsync(TestParameters.LOCALE2, TestParameters.VERSION) {
            onSuccess = { futureProfileIconList2.complete(it) }
        }

        println(futureProfileIconList2.get().toString())
    }
}