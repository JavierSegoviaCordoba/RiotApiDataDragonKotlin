package methodsthest.cdn.summonerspell

import com.videumcorp.datadragonwrapperkotlin.datadragon.DataDragon
import com.videumcorp.datadragonwrapperkotlin.datadragon.constants.Platform
import org.junit.Test
import utils.TestParameters

class SummonerSpellMethodsTest {

    @Test
    fun main() {

        val dataDragon = DataDragon(Platform.NA)

        dataDragon.getSummonerSpellList(TestParameters.LOCALE1, TestParameters.VERSION) {
            onSuccess = { println(it.toString()) }
        }

        dataDragon.getSummonerSpellList(TestParameters.LOCALE2, TestParameters.VERSION) {
            onSuccess = { println(it.toString()) }
        }

        dataDragon.getSummonerSpell("SummonerDot", TestParameters.LOCALE1, TestParameters.VERSION) {
            onSuccess = { println(it.toString()) }
        }

        dataDragon.getSummonerSpell("ErrorSummoner", TestParameters.LOCALE1, TestParameters.VERSION) {
            onErrorCode = { println(it.toString()) }
        }

        dataDragon.getSummonerSpell("SummonerDot", TestParameters.LOCALE2, TestParameters.VERSION) {
            onSuccess = { println(it.toString()) }
        }
    }
}