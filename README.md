# Kotlin-Riot-Api-Wrapper
Easy to use library, available to both Kotlin and Java.  
A wrapper for the different endpoints Riot API provides, using Retrofit/OKHttp libraries.  
Support synchronous and asynchronous call.  
Simply put your API key and go !

## Import
### Gradle  
*app or module build.gradle*  
```
repositories {
    mavenCentral()
}
```
*module build.gradle*  
```
implementation "io.github.miniclem:kotlin-api-riot-wrapper:0.2"
```

### Maven
```xml
<dependency>
  <groupId>io.github.miniclem</groupId>
  <artifactId>kotlin-api-riot-wrapper</artifactId>
  <version>0.2</version>
</dependency>
```

## How to use
### Basic usage  
#### Kotlin
```kotlin
    ClientApi.apply {
        // You must provide your API key, 
        tokenProvider = ParamProperties()
    }
    // OR simply put
    ClientApi.apply {
        tokenProvider = object : TokenProvider {
            override fun getToken(): String {
                return "YOUR API KEY"
            }
        }
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

#### Java
```java
        ClientApi.INSTANCE.setTokenProvider(new TokenProvider() {
            @NotNull
            @Override
            public String getToken() {
                return "YOUR API KEY";
            }
        });

        ClientApi.INSTANCE.championV3(PlatformRoutes.EUW1).getChampionRotations().enqueue(new retrofit2.Callback<ChampionInfo>() {
            @Override
            public void onResponse(@NotNull Call<ChampionInfo> call, @NotNull Response<ChampionInfo> response) {
                // Use your response data
            }

            @Override
            public void onFailure(@NotNull Call<ChampionInfo> call, @NotNull Throwable t) {
                t.printStackTrace();
            }
        });    
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
  
*param.properties:*
```kotlin
TOKEN=YOUR API KEY HERE
```  
## Supported API endpoints  
### League of Legends  
- CHAMPION-MASTERY-V4
- CHAMPION-V3
- CLASH-V1
- LEAGUE-EXP-V4
- LEAGUE-V4
- LOL-STATUS-V3
- MATCH-V4
- SPECTATOR-V4
- SUMMONER-V4  
- THIRD-PARTY-CODE-V4
- TOURNAMENT-STUB-V4

### Valorant
- ACCOUNT-V1
- VAL-MATCH-V1  

### Teamfight Tactics  
- TFT-LEAGUE-V1
- TFT-MATCH-V1
- TFT-SUMMONER-V1

### Legends of Runeterra  
- LOR-RANKED-V1

## Issues
Feel free to post [issues](https://github.com/MiniClem/Kotlin-Riot-Api-Wrapper/issues) while you find one, I would gladly try to fix it or accept pull request :)
