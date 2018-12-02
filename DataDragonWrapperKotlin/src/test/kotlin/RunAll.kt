import methodsthest.cdn.champion.ChampionMethodsTest
import methodsthest.cdn.championfulllist.ChampionFullFullListMethodsTest
import methodsthest.cdn.championshortlist.ChampionShortListMethodsTest
import methodsthest.cdn.item.ItemMethodsTest
import methodsthest.cdn.language.LanguageMethodsTest
import methodsthest.cdn.languages.LanguagesMethodsTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
        ChampionMethodsTest::class,
        ChampionShortListMethodsTest::class,
        ChampionFullFullListMethodsTest::class,
        ItemMethodsTest::class,
        LanguageMethodsTest::class,
        LanguagesMethodsTest::class)
class RunAll