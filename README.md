# DataDragon wrapper for Kotlin

## Download

Gradle: `compile 'com.videumcorp:DataDragonWrapperKotlin:0.0.1'`

Gradle (Android): `implementation 'com.videumcorp:DataDragonWrapperKotlin:0.0.1'`

Maven:
```
<dependency>
  <groupId>com.videumcorp</groupId>
  <artifactId>DataDragonWrapperKotlin</artifactId>
  <version>0.0.1</version>
  <type>pom</type>
</dependency>
```

## Usage

There are three ways to use this library: synchronous, asynchronous and coroutines

### Examples:

You can see all the methods checking all the files in the [test folder](https://github.com/JavierSegoviaCordoba/RiotApiDataDragonKotlin/tree/master/DataDragonWrapperKotlin/src/test/kotlin).
Remember test examples are for Kotlin apps so check the Android example below to understand how to use the test examples in an Android app.

Select the platform from which you want to fetch the data.
```
val dataDragon = DataDragon(Platform.NA)
```

#### Kotlin App

##### Sync:

```
dataDragon.getChampion("Graves", Locale.EN_US, "8.22.1") {

    onSuccess = { champion -> println(champion.toString()) }

    // You can use too: onSuccess = { println(it.toString()) }

    onErrorCode = { println("${it.code}: ${it.message}") }

    // ErroCode is data class with two properties: a code number (ex: '404') and a message (ex: 'Not found')

    onFailure = { println(it.message) }

    // onFailure is a throwable
}
```

##### Async:

```
dataDragon.getChampionAsync("Graves", Locale.EN_US, "8.22.1") {
    onSuccess = { println(it.toString()) }

    onErrorCode = { println("${it.code}: ${it.message}") }

    onFailure = { println(it.message) }
}
```

##### Coroutine:

```
runBlocking  {
    try {
        val champion = dataDragon.getChampionDeferred("Graves", Locale.EN_US, "8.22.1")
        println(champion.toString())
    } catch (t: Throwable) {
        when (t) {
            is DataDragonException -> println(t.message) // Example: '403: Forbidden'
            else -> println(t.message)
        }
    }
}
```

#### Android App

You can't use sync directly so you should use async and coroutine ways.

##### Async:

```
dataDragon.getChampionAsync("Graves", Locale.ES_ES, "8.22.1") {
    onSuccess = { println(it.toString()) }

    onErrorCode = { println(it.toString()) }

    onFailure = { println(it.message) }
}
```

##### Coroutine:

```
GlobalScope.launch (Dispatchers.Main)   {
    try {
        val champion = dataDragon.getChampionDeferred("Graves", Locale.ES_ES, "8.22.1")
        println(champion.toString())
    } catch (t: Throwable) {
        when (t) {
            is DataDragonException -> println(t.message) // Example: '403: Forbidden'
            else -> println(t.message)
        }
    }
}
```

### TODO
- Create Wiki