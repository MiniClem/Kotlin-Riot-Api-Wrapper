package services

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import services.endpoints.*

object ClientApi {

    private val retrofit: Retrofit by lazy {
        val client = OkHttpClient()
            .apply {
                interceptors().add(HeaderInterceptor(ParamProperties()))
            }

        Retrofit.Builder()
            .client(client)
            .baseUrl("https://euw1.api.riotgames.com")
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