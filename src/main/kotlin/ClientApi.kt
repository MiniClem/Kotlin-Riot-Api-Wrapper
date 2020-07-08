import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import services.*

class ClientApi {
    companion object Instance {
        private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://euw1.api.riotgames.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val championV3Service: ChampionV3 = retrofit.create(ChampionV3::class.java)
        val championMasteryV4: ChampionMasteryV4 = retrofit.create(ChampionMasteryV4::class.java)
        val leagueV4: LeagueV4 = retrofit.create(LeagueV4::class.java)
        val summonerV4: SummonerV4 = retrofit.create(SummonerV4::class.java)
        val clashV1: ClashV1 = retrofit.create(ClashV1::class.java)
        val leagueExpV4: LeagueExpV4 = retrofit.create(LeagueExpV4::class.java)
        val lolStatusV3: LolStatusV3 = retrofit.create(LolStatusV3::class.java)
        val matchV4: MatchV4 = retrofit.create(MatchV4::class.java)
        val spectatorV4: SpectatorV4 = retrofit.create(SpectatorV4::class.java)
        val tftLeagueV1: TftLeagueV1 = retrofit.create(TftLeagueV1::class.java)
    }
}

/*
builder.addInterceptor(chain -> {
Request request = chain.request().newBuilder().addHeader("key", "value").build();
return chain.proceed(request);
});
*/