package methodsthest.cdn.champion

import com.videumcorp.datadragonwrapperkotlin.datadragon.DataDragon
import com.videumcorp.datadragonwrapperkotlin.datadragon.constants.Platform
import kotlinx.coroutines.runBlocking
import org.junit.Test
import utils.TestParameters

class ChampionMethodsCoroutinesTest {

    @Test
    fun main() {

        val dataDragon = DataDragon(Platform.NA)

        runBlocking {
            println(dataDragon.getChampionDeferred("Graves", TestParameters.LOCALE1, TestParameters.VERSION))
            try {
                println(dataDragon.getChampionDeferred("ErrorChamp", TestParameters.LOCALE1, TestParameters.VERSION))
            } catch (t: Throwable) {
                println(t.message)
            }
            println(dataDragon.getChampionDeferred("Graves", TestParameters.LOCALE2, TestParameters.VERSION))

            println(dataDragon.getChampionDeferred(2, TestParameters.LOCALE1, TestParameters.VERSION))
            try {
                println(dataDragon.getChampionDeferred(999999, TestParameters.LOCALE1, TestParameters.VERSION))
            } catch (t: Throwable) {
                println(t.message)
            }
            println(dataDragon.getChampionDeferred(2, TestParameters.LOCALE2, TestParameters.VERSION))

            println(dataDragon.getChampionKeyDeferred("Yasuo", TestParameters.LOCALE2, TestParameters.VERSION))
            try {
                println(dataDragon.getChampionKeyDeferred("ErrorChamp", TestParameters.LOCALE1, TestParameters.VERSION))
            } catch (t: Throwable) {
                println(t.toString())
            }

            println(dataDragon.getChampionIdDeferred(3, TestParameters.LOCALE2, TestParameters.VERSION))
            try {
                println(dataDragon.getChampionIdDeferred(999999, TestParameters.LOCALE1, TestParameters.VERSION))
            } catch (t: Throwable) {
                println(t.toString())
            }

            println(dataDragon.getChampionKeyIdListDeferred(TestParameters.LOCALE1, TestParameters.VERSION))
        }
    }
}