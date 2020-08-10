# Kotlin-Riot-Api-Wrapper
Kotlin wrapper for the different endpoints Riot API provide, using Retrofit/OKHttp libraries.  
Support synchrone and asynchrone call.

## Import
### Gradle 
```
implementation "io.github.miniclem:kotlin-api-riot-wrapper:0.2"
```

### Maven
```
<dependency>
  <groupId>io.github.miniclem</groupId>
  <artifactId>kotlin-api-riot-wrapper</artifactId>
  <version>0.2</version>
</dependency>
```

## How to use
### Basic usage
```kotlin
    ClientApi.apply {
        // You must provide your API key, 
        tokenProvider = ParamProperties()
    }

    ClientApi.championV3(PlatformRoutes.EUW1).getChampionRotations().enqueue(object : Callback<ChampionInfo> {
        override fun onFailure(call: Call<ChampionInfo>, t: Throwable) {
            // Call the bott... backends noobs
            t.printStackTrace()
        }

        override fun onResponse(call: Call<ChampionInfo>, response: Response<ChampionInfo>) {
            response.body()?.let {
                // Jungle around your data
            }
        }
    })
``` 

### Provide your API key
You must provide a way for your app to authenticate via the Riot API portal using you API key.  
> See the official site [Riot developer portal](https://developer.riotgames.com/)  

Back to the library you will need to implement the `TokenProvider` interface and pass it to the `ClientApi`.  
#### Example
```kotlin
class ParamProperties : Properties(), TokenProvider {

    init {
        val inputStream = ClassLoader.getSystemResourceAsStream("param.properties")
        if (inputStream != null)
            load(inputStream)
        else throw IOException("You must create a file named 'param.properties' in the resource folder containing at least the 'TOKEN' variable with your API token")
    }

    override fun getToken(): String {
        return getProperty("TOKEN")
    }
}
```
Then put a file called `param.properties` in your resources folder with your API key inside.  
  
*param.properties:*
```kotlin
TOKEN=YOUR API KEY HERE
```

## Issues
Feel free to post issues while you find one, I would gladly try to fix it or accept pull request :)
