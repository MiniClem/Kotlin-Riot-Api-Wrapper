package services.endpoints

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface TftSummonerV1 {

    /**
     * Get a summoner by account ID
     */
    @GET("/tft/summoner/v1/summoners/by-account/{encryptedAccountId}")
    fun getSummonerByAccountId(@Path("encryptedAccountId") encryptedAccountId: String): Call<SummonerDTO>

    /**
     * Get a summoner by summoner name
     *
     * @param summonerName Summoner Name
     */
    @GET("/tft/summoner/v1/summoners/by-name/{summonerName}")
    fun getSummonerByName(@Path("summonerName") summonerName: String): Call<SummonerDTO>

    /**
     * Get a summoner by PUUID
     */
    @GET("/tft/summoner/v1/summoners/by-puuid/{summonerName}")
    fun getSummonerByPuuid(@Path("summonerName") summonerName: String): Call<SummonerDTO>

    /**
     * Get a summoner by summoner ID
     */
    @GET("/tft/summoner/v1/summoners/{encryptedSummonerId}")
    fun getSummonerById(@Path("encryptedSummonerId") encryptedSummonerId: String): Call<SummonerDTO>


}