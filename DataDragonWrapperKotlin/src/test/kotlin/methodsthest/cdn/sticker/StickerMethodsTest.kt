package methodsthest.cdn.sticker

import com.videumcorp.datadragonwrapperkotlin.datadragon.DataDragon
import com.videumcorp.datadragonwrapperkotlin.datadragon.constants.Platform
import org.junit.Test
import utils.TestParameters

class StickerMethodsTest {

    @Test
    fun main() {

        val dataDragon = DataDragon(Platform.NA)

        dataDragon.getStickerList(TestParameters.LOCALE1, TestParameters.VERSION) {
            onSuccess = { println(it.toString()) }
        }

        dataDragon.getStickerList(TestParameters.LOCALE2, TestParameters.VERSION) {
            onSuccess = { println(it.toString()) }
        }

        dataDragon.getSticker("poro-angry", TestParameters.LOCALE1, TestParameters.VERSION) {
            onSuccess = { println(it.toString()) }
        }

        dataDragon.getSticker("ErrorSticker", TestParameters.LOCALE1, TestParameters.VERSION) {
            onErrorCode = { println(it.toString()) }
        }

        dataDragon.getSticker("poro-angry", TestParameters.LOCALE2, TestParameters.VERSION) {
            onSuccess = { println(it.toString()) }
        }
    }
}