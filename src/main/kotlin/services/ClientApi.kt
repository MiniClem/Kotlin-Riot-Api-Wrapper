package services

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import services.endpoints.*

object ClientApi {

    private lateinit var interceptorInterface: InterceptorInterface
    private var platformRoutes: PlatformRoutes = PlatformRoutes.EUW1
    private var regionalRoutes: RegionalRoutes = RegionalRoutes.EUROPE

    /**
     * You must provide this interface to the client service to use any endpoint
     */
    fun setToken(interceptorInterface: InterceptorInterface): ClientApi {
        this.interceptorInterface = interceptorInterface
        return this
    }

    /**
     * Set the route to be used with the endpoints using the platform route parameter
     * Default is [PlatformRoutes.EUW1]
     */
    fun setPlatformRoute(platformRoutes: PlatformRoutes): ClientApi {
        this.platformRoutes = platformRoutes
        return this
    }

    /**
     * Set the route to be used with the endpoints using the platform route parameter
     * Default is [PlatformRoutes.EUW1]
     */
    fun setRegionalRoute(regionalRoutes: RegionalRoutes): ClientApi {
        this.regionalRoutes = regionalRoutes
        return this
    }

    private val retrofit: Retrofit by lazy {
        val client = OkHttpClient.Builder()
            .apply {
                interceptors().add(HeaderInterceptor(interceptorInterface))
            }.build()



        Retrofit.Builder()
            .client(client)
            .baseUrl("https://${platformRoutes.name}")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val accountV1: AccountV1 = retrofit.create(AccountV1::class.java)
    val championMasteryV4: ChampionMasteryV4 = retrofit.create(ChampionMasteryV4::class.java)
    val championV3: ChampionV3 = retrofit.create(ChampionV3::class.java)
    val clashV1: ClashV1 = retrofit.create(ClashV1::class.java)
    val leagueExpV4: LeagueExpV4 = retrofit.create(LeagueExpV4::class.java)
    val leagueV4: LeagueV4 = retrofit.create(LeagueV4::class.java)
    val lolStatusV3: LolStatusV3 = retrofit.create(LolStatusV3::class.java)
    val lorRankedV1: LorRankedV1 = retrofit.create(LorRankedV1::class.java)
    val matchV4: MatchV4 = retrofit.create(MatchV4::class.java)
    val spectatorV4: SpectatorV4 = retrofit.create(SpectatorV4::class.java)
    val summonerV4: SummonerV4 = retrofit.create(SummonerV4::class.java)
    val tftLeagueV1: TftLeagueV1 = retrofit.create(TftLeagueV1::class.java)
    val tftMatchV1: TftMatchV1 = retrofit.create(TftMatchV1::class.java)
    val tftSummonerV1: TftSummonerV1 = retrofit.create(TftSummonerV1::class.java)
    val thirdPartyCodeV4: ThirdPartyCodeV4 = retrofit.create(ThirdPartyCodeV4::class.java)
    val tournamentStubV4: TournamentStubV4 = retrofit.create(TournamentStubV4::class.java)
    val tournamentV4: TournamentV4 = retrofit.create(TournamentV4::class.java)
    val valContentV1: ValContentV1 = retrofit.create(ValContentV1::class.java)
    val valMatchV1: ValMatchV1 = retrofit.create(ValMatchV1::class.java)
}

enum class PlatformRoutes(val route: String) {
    BR1("br1.api.riotgames.com"),
    EUN1("eun1.api.riotgames.com"),
    EUW1("euw1.api.riotgames.com"),
    JP1("jp1.api.riotgames.com"),
    KR("kr.api.riotgames.com"),
    LA1("la1.api.riotgames.com"),
    LA2("la2.api.riotgames.com"),
    NA1("na1.api.riotgames.com"),
    OC1("oc1.api.riotgames.com"),
    TR1("tr1.api.riotgames.com"),
    RU("ru.api.riotgames.com"),
}

enum class RegionalRoutes(val route: String) {
    AMERICAS("americas.api.riotgames.com"),
    ASIA("asia.api.riotgames.com"),
    EUROPE("europe.api.riotgames.com")
}
