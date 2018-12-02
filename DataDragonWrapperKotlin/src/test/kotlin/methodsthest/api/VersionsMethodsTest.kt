package methodsthest.api

import com.videumcorp.datadragonwrapperkotlin.datadragon.DataDragon
import com.videumcorp.datadragonwrapperkotlin.datadragon.constants.Platform
import org.junit.Test

class VersionsMethodsTest {

    @Test
    fun main() {

        val dataDragon = DataDragon(Platform.NA)

        dataDragon.getVersionsList {
            onSuccess = { println(it?.toString()) }

            onErrorCode = { println(it.toString()) }

            onFailure = { println(it.message) }
        }
    }
}