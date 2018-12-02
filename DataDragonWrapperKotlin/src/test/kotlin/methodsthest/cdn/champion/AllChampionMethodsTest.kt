package methodsthest.cdn.champion

import com.videumcorp.datadragonwrapperkotlin.datadragon.DataDragon
import com.videumcorp.datadragonwrapperkotlin.datadragon.constants.Platform
import org.junit.Test
import utils.TestParameters

class AllChampionMethodsTest {

    @Test
    fun main() {

        val dataDragon = DataDragon(Platform.NA)

        dataDragon.getChampionKeyIdList(TestParameters.LOCALE1, TestParameters.VERSION) {
            onSuccess = {

                var i = 1

                if (it != null) {
                    for (championKeyId in it) {
                        dataDragon.getChampion(
                                championKeyId.id!!,
                                TestParameters.LOCALE1,
                                TestParameters.VERSION) {

                            onSuccess = { champion -> println("${i++} $champion") }

                            onErrorCode = { errorCode -> println(errorCode.toString()) }

                            onFailure = { throwable -> println(throwable.message) }
                        }
                    }
                }
            }

            onErrorCode = { print(it.toString()) }

            onFailure = { println(it.toString()) }
        }
    }
}