package services

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import services.endpoints.*
import services.interceptors.BaseUrlInterceptor
import services.interceptors.HeaderInterceptor
import services.interceptors.TokenProvider

object ClientApi {

    lateinit var tokenProvider: TokenProvider
    var platformRoutes: PlatformRoutes = PlatformRoutes.EUW1
        set(value) {
            platformUrlInterceptor.setHost("https://${value.route}")
            field = value
        }
    var regionalRoutes: RegionalRoutes = RegionalRoutes.EUROPE
        set(value) {
            regionUrlInterceptor.setHost("https://${value.route}")
            field = value
        }

    private val platformUrlInterceptor = BaseUrlInterceptor(platformRoutes.route)
    private val regionUrlInterceptor = BaseUrlInterceptor(regionalRoutes.route)

    private val platformClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .apply {
                interceptors().add(platformUrlInterceptor)
                interceptors().add(HeaderInterceptor(tokenProvider))
            }.build()
    }

    private val regionClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .apply {
                interceptors().add(regionUrlInterceptor)
                interceptors().add(HeaderInterceptor(tokenProvider))
            }.build()
    }

    private val stubClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .apply {
                interceptors().add(HeaderInterceptor(tokenProvider))
            }.build()
    }

    private val platformRetrofit: Retrofit by lazy {
        Retrofit.Builder()
            .client(platformClient)
            .baseUrl("https://${platformRoutes.route}")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val regionalRetrofit: Retrofit by lazy {
        Retrofit.Builder()
            .client(regionClient)
            .baseUrl("https://${regionalRoutes.route}")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val stubRetrofit: Retrofit by lazy {
        Retrofit.Builder()
            .client(stubClient)
            .baseUrl("https://${RegionalRoutes.AMERICAS.route}")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    object Services {
        val accountV1: AccountV1 = regionalRetrofit.create(AccountV1::class.java)
        val championMasteryV4: ChampionMasteryV4 = platformRetrofit.create(ChampionMasteryV4::class.java)
        val championV3: ChampionV3 = platformRetrofit.create(ChampionV3::class.java)
        val clashV1: ClashV1 = platformRetrofit.create(ClashV1::class.java)
        val leagueExpV4: LeagueExpV4 = platformRetrofit.create(LeagueExpV4::class.java)
        val leagueV4: LeagueV4 = platformRetrofit.create(LeagueV4::class.java)
        val lolStatusV3: LolStatusV3 = platformRetrofit.create(LolStatusV3::class.java)
        val lorRankedV1: LorRankedV1 = regionalRetrofit.create(LorRankedV1::class.java)
        val matchV4: MatchV4 = platformRetrofit.create(MatchV4::class.java)
        val spectatorV4: SpectatorV4 = platformRetrofit.create(SpectatorV4::class.java)
        val summonerV4: SummonerV4 = platformRetrofit.create(SummonerV4::class.java)
        val tftLeagueV1: TftLeagueV1 = platformRetrofit.create(TftLeagueV1::class.java)
        val tftMatchV1: TftMatchV1 = regionalRetrofit.create(TftMatchV1::class.java)
        val tftSummonerV1: TftSummonerV1 = platformRetrofit.create(TftSummonerV1::class.java)
        val thirdPartyCodeV4: ThirdPartyCodeV4 = platformRetrofit.create(ThirdPartyCodeV4::class.java)
        val tournamentStubV4: TournamentStubV4 = stubRetrofit.create(TournamentStubV4::class.java) // america only

        //        val tournamentV4: TournamentV4 = platformRoute.create(TournamentV4::class.java)
//        val valContentV1: ValContentV1 = platformRoute.create(ValContentV1::class.java)
        val valMatchV1: ValMatchV1 = platformRetrofit.create(ValMatchV1::class.java)
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
