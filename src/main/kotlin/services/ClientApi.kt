package services

import retrofit2.Retrofit
import services.endpoints.*
import services.interceptors.TokenProvider

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

object ClientApi {

    lateinit var tokenProvider: TokenProvider

    private fun platformRetrofit(platformRoutes: PlatformRoutes): Retrofit =
        RetrofitInjector(platformRoutes.route).get()

    private fun regionalRetrofit(regionalRoutes: RegionalRoutes): Retrofit =
        RetrofitInjector(regionalRoutes.route).get()

    private fun stubRetrofit(): Retrofit =
        RetrofitInjector(RegionalRoutes.AMERICAS.route).get()

    object Services {
        fun accountV1(regionalRoutes: RegionalRoutes): AccountV1 =
            regionalRetrofit(regionalRoutes).create(AccountV1::class.java)

        fun championMasteryV4(platformRoutes: PlatformRoutes): ChampionMasteryV4 =
            platformRetrofit(platformRoutes).create(ChampionMasteryV4::class.java)

        fun championV3(platformRoutes: PlatformRoutes): ChampionV3 =
            platformRetrofit(platformRoutes).create(ChampionV3::class.java)

        fun clashV1(platformRoutes: PlatformRoutes): ClashV1 =
            platformRetrofit(platformRoutes).create(ClashV1::class.java)

        fun leagueExpV4(platformRoutes: PlatformRoutes): LeagueExpV4 =
            platformRetrofit(platformRoutes).create(LeagueExpV4::class.java)

        fun leagueV4(platformRoutes: PlatformRoutes): LeagueV4 =
            platformRetrofit(platformRoutes).create(LeagueV4::class.java)

        fun lolStatusV3(platformRoutes: PlatformRoutes): LolStatusV3 =
            platformRetrofit(platformRoutes).create(LolStatusV3::class.java)

        fun lorRankedV1(regionalRoutes: RegionalRoutes): LorRankedV1 =
            regionalRetrofit(regionalRoutes).create(LorRankedV1::class.java)

        fun matchV4(platformRoutes: PlatformRoutes): MatchV4 =
            platformRetrofit(platformRoutes).create(MatchV4::class.java)

        fun spectatorV4(platformRoutes: PlatformRoutes): SpectatorV4 =
            platformRetrofit(platformRoutes).create(SpectatorV4::class.java)

        fun summonerV4(platformRoutes: PlatformRoutes): SummonerV4 =
            platformRetrofit(platformRoutes).create(SummonerV4::class.java)

        fun tftLeagueV1(platformRoutes: PlatformRoutes): TftLeagueV1 =
            platformRetrofit(platformRoutes).create(TftLeagueV1::class.java)

        fun tftMatchV1(regionalRoutes: RegionalRoutes): TftMatchV1 =
            regionalRetrofit(regionalRoutes).create(TftMatchV1::class.java)

        fun tftSummonerV1(platformRoutes: PlatformRoutes): TftSummonerV1 =
            platformRetrofit(platformRoutes).create(TftSummonerV1::class.java)

        fun thirdPartyCodeV4(platformRoutes: PlatformRoutes): ThirdPartyCodeV4 =
            platformRetrofit(platformRoutes).create(ThirdPartyCodeV4::class.java)

        fun tournamentStubV4(): TournamentStubV4 = stubRetrofit().create(TournamentStubV4::class.java) // america only

        //        val tournamentV4: TournamentV4 = platformRoute.create(TournamentV4::class.java)
//        val valContentV1: ValContentV1 = platformRoute.create(ValContentV1::class.java)

        fun valMatchV1(regionalRoutes: RegionalRoutes): ValMatchV1 =
            regionalRetrofit(regionalRoutes).create(ValMatchV1::class.java)
    }
}