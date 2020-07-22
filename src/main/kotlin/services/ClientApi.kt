package services

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import services.endpoints.*

object ClientApi {

    lateinit var interceptorInterface: InterceptorInterface
    var platformRoutes: PlatformRoutes = PlatformRoutes.EUW1
    var regionalRoutes: RegionalRoutes = RegionalRoutes.EUROPE

    private val client: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .apply {
                interceptors().add(HeaderInterceptor(interceptorInterface))
            }.build()
    }

    private val regionalRoute: Retrofit by lazy {
        Retrofit.Builder()
            .client(client)
            .baseUrl("https://${regionalRoutes.route}")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val platformRoute: Retrofit by lazy {
        Retrofit.Builder()
            .client(client)
            .baseUrl("https://${platformRoutes.route}")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val stubRoute: Retrofit by lazy {
        Retrofit.Builder()
            .client(client)
            .baseUrl("https://${RegionalRoutes.AMERICAS.route}")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    object Services {
        val accountV1: AccountV1 = regionalRoute.create(AccountV1::class.java)
        val championMasteryV4: ChampionMasteryV4 = platformRoute.create(ChampionMasteryV4::class.java)
        val championV3: ChampionV3 = platformRoute.create(ChampionV3::class.java)
        val clashV1: ClashV1 = platformRoute.create(ClashV1::class.java)
        val leagueExpV4: LeagueExpV4 = platformRoute.create(LeagueExpV4::class.java)
        val leagueV4: LeagueV4 = platformRoute.create(LeagueV4::class.java)
        val lolStatusV3: LolStatusV3 = platformRoute.create(LolStatusV3::class.java)
        val lorRankedV1: LorRankedV1 = regionalRoute.create(LorRankedV1::class.java)
        val matchV4: MatchV4 = platformRoute.create(MatchV4::class.java)
        val spectatorV4: SpectatorV4 = platformRoute.create(SpectatorV4::class.java)
        val summonerV4: SummonerV4 = platformRoute.create(SummonerV4::class.java)
        val tftLeagueV1: TftLeagueV1 = platformRoute.create(TftLeagueV1::class.java)
        val tftMatchV1: TftMatchV1 = regionalRoute.create(TftMatchV1::class.java)
        val tftSummonerV1: TftSummonerV1 = platformRoute.create(TftSummonerV1::class.java)
        val thirdPartyCodeV4: ThirdPartyCodeV4 = platformRoute.create(ThirdPartyCodeV4::class.java)
        val tournamentStubV4: TournamentStubV4 = stubRoute.create(TournamentStubV4::class.java) // america only
//        val tournamentV4: TournamentV4 = platformRoute.create(TournamentV4::class.java)
//        val valContentV1: ValContentV1 = platformRoute.create(ValContentV1::class.java)
        val valMatchV1: ValMatchV1 = platformRoute.create(ValMatchV1::class.java)
    }
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
