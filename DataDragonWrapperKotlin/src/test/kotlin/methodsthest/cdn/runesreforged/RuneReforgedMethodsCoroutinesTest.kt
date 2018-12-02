package methodsthest.cdn.runesreforged

import com.videumcorp.datadragonwrapperkotlin.datadragon.DataDragon
import com.videumcorp.datadragonwrapperkotlin.datadragon.constants.Locale
import com.videumcorp.datadragonwrapperkotlin.datadragon.constants.Platform
import kotlinx.coroutines.runBlocking
import org.junit.Test

class RuneReforgedMethodsCoroutinesTest {

    @Test
    fun main() {

        val dataDragon = DataDragon(Platform.NA)

        runBlocking {
            println(dataDragon.getRuneReforgedListDeferred(Locale.EN_US, "8.21.1"))
            println(dataDragon.getRuneReforgedListDeferred(Locale.ES_ES, "8.21.1"))
            println(dataDragon.getRuneReforgedDeferred(8300, Locale.EN_US, "8.21.1"))
            println(dataDragon.getRuneReforgedDeferred(8300, Locale.ES_ES, "8.21.1"))
            println(dataDragon.getRuneReforgedDeferred("Sorcery", Locale.EN_US, "8.21.1"))
            println(dataDragon.getRuneReforgedDeferred("Sorcery", Locale.ES_ES, "8.21.1"))
            println(dataDragon.getRuneDeferred(8112, Locale.EN_US, "8.21.1"))
            println(dataDragon.getRuneDeferred(8112, Locale.ES_ES, "8.21.1"))
            println(dataDragon.getRuneDeferred("PressTheAttack", Locale.EN_US, "8.21.1"))
            println(dataDragon.getRuneDeferred("PressTheAttack", Locale.ES_ES, "8.21.1"))
        }

        println("\nthreads: " + Thread.activeCount())
    }
}