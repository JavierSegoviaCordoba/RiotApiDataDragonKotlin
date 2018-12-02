package methodsthest.realms

import com.videumcorp.datadragonwrapperkotlin.datadragon.DataDragon
import com.videumcorp.datadragonwrapperkotlin.datadragon.constants.Platform
import org.junit.Test

class RealmsMethodsTest {

    @Test
    fun main() {

        val dataDragon = DataDragon(Platform.NA)
        dataDragon.getRealms {

            onSuccess = { println(it.toString()) }

            onErrorCode = { println(it.toString()) }

            onFailure = { println(it.message) }
        }
    }
}