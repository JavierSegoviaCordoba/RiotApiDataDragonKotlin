package methodsthest.cdn.sticker

import com.videumcorp.datadragonwrapperkotlin.datadragon.DataDragon
import com.videumcorp.datadragonwrapperkotlin.datadragon.constants.Locale
import com.videumcorp.datadragonwrapperkotlin.datadragon.constants.Platform
import kotlinx.coroutines.runBlocking
import org.junit.Test

class StickerMethodsCoroutinesTest {

    @Test
    fun main() {

        val dataDragon = DataDragon(Platform.NA)

        runBlocking {
            println(dataDragon.getStickerListDeferred(Locale.EN_US, "8.21.1"))
            println(dataDragon.getStickerListDeferred(Locale.ES_ES, "8.21.1"))
            println(dataDragon.getStickerDeferred("poro-angry", Locale.EN_US, "8.21.1"))
            println(dataDragon.getStickerDeferred("poro-angry", Locale.ES_ES, "8.21.1"))
            println(dataDragon.getStickerDeferred(0, Locale.EN_US, "8.21.1"))
            println(dataDragon.getStickerDeferred(0, Locale.ES_ES, "8.21.1"))
        }

    }
}