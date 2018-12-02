package methodsthest.cdn.map

import com.videumcorp.datadragonwrapperkotlin.datadragon.DataDragon
import com.videumcorp.datadragonwrapperkotlin.datadragon.constants.Locale
import com.videumcorp.datadragonwrapperkotlin.datadragon.constants.Platform
import kotlinx.coroutines.runBlocking
import org.junit.Test

class MapMethodsCoroutinesTest {

    @Test
    fun main() {

        val dataDragon = DataDragon(Platform.NA)

        runBlocking {
            println(dataDragon.getMapDeferred(10, Locale.EN_US, "8.21.1"))
            println(dataDragon.getMapDeferred(10, Locale.ES_ES, "8.21.1"))
            println(dataDragon.getMapListDeferred(Locale.EN_US, "8.21.1"))
            println(dataDragon.getMapListDeferred(Locale.ES_ES, "8.21.1"))
        }

        println("\nthreads: " + Thread.activeCount())
    }
}