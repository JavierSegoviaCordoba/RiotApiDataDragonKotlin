package methodsthest.realms

import com.videumcorp.datadragonwrapperkotlin.datadragon.DataDragon
import com.videumcorp.datadragonwrapperkotlin.datadragon.constants.Platform
import kotlinx.coroutines.runBlocking
import org.junit.Test

class RealmsMethodsCoroutinesTest {

    @Test
    fun main() {

        val dataDragon = DataDragon(Platform.NA)

        runBlocking {
            val realms = dataDragon.getRealmsDeferred()
            println(realms.toString())
        }
    }
}