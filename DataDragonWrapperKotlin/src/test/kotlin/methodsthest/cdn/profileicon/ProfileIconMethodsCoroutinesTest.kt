package methodsthest.cdn.profileicon

import com.videumcorp.datadragonwrapperkotlin.datadragon.DataDragon
import com.videumcorp.datadragonwrapperkotlin.datadragon.constants.Locale
import com.videumcorp.datadragonwrapperkotlin.datadragon.constants.Platform
import kotlinx.coroutines.runBlocking
import org.junit.Test

class ProfileIconMethodsCoroutinesTest {

    @Test
    fun main() {

        val dataDragon = DataDragon(Platform.NA)

        runBlocking {
            println(dataDragon.getProfileIconDeferred(10, Locale.EN_US, "8.21.1"))
            println(dataDragon.getProfileIconDeferred(10, Locale.ES_ES, "8.21.1"))
            println(dataDragon.getProfileIconListDeferred(Locale.EN_US, "8.21.1"))
            println(dataDragon.getProfileIconListDeferred(Locale.ES_ES, "8.21.1"))
        }

        println("\nthreads: " + Thread.activeCount())
    }
}