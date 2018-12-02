package methodsthest.cdn.item

import com.videumcorp.datadragonwrapperkotlin.datadragon.DataDragon
import com.videumcorp.datadragonwrapperkotlin.datadragon.constants.Locale
import com.videumcorp.datadragonwrapperkotlin.datadragon.constants.Platform
import kotlinx.coroutines.runBlocking
import org.junit.Test

class ItemMethodsCoroutinesTest {

    @Test
    fun main() {

        val dataDragon = DataDragon(Platform.NA)

        runBlocking {
            println(dataDragon.getItemDeferred(1001, Locale.EN_US, "8.21.1"))
            println(dataDragon.getItemDeferred(1001, Locale.ES_ES, "8.21.1"))
            println(dataDragon.getItemListDeferred(Locale.EN_US, "8.21.1").toString())
            println(dataDragon.getItemListDeferred(Locale.ES_ES, "8.21.1").toString())
        }
    }
}