package services

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * The AMERICAS routing value serves NA, BR, LAN, LAS, and OCE. The ASIA routing value serves KR and JP. The EUROPE
 * routing value serves EUNE, EUW, TR, and RU.
 */
interface TftMatchV1 {

    /**
     * Get a list of match ids by PUUID
     */
    @GET("/tft/match/v1/matches/by-puuid/{puuid}/ids")
    fun getMatchIdsByPuuid(@Path("puuid") puuid: String, @Query("count") count: Int = 20): Call<List<String>>

    /**
     * Get a match by match id
     */
    @GET("/tft/match/v1/matches/{matchId}")
    fun getMatchById(@Path("matchId") matchId: String): Call<MatchDto>
}