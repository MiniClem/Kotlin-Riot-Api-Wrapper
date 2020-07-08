package services

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TftLeagueV1 {

    /**
     * Get the challenger league
     */
    @GET("/tft/league/v1/challenger")
    fun getChallengerLeague(): Call<LeagueListDTO>

    /**
     * Get league entries for a given summoner ID
     */
    @GET("/tft/league/v1/entries/by-summoner/{encryptedSummonerId}")
    fun getLeagueEntrieBySummoner(@Path("encryptedSummonerId") encryptedSummonerId: String): Call<LeagueEntryDTO>

    /**
     * Get all the league entries
     *
     * @param page Starts with page 1.
     */
    @GET("/tft/league/v1/entries/{tier}/{division}")
    fun getAllLeagueEntries(
        @Path("tier") tier: String,
        @Path("division") division: String,
        @Query("page") page: Int
    ): Call<Set<LeagueEntryDTO>>

    /**
     * Get the grandmaster league
     */
    @GET("/tft/league/v1/grandmaster")
    fun getAllGrandmasterLeague(): Call<LeagueListDTO>

    /**
     * Get league with given ID, including inactive entries
     *
     * @param leagueId The UUID of the league
     */
    @GET("/tft/league/v1/leagues/{leagueId}")
    fun getLeagueById(@Path("leagueId") leagueId: String): Call<LeagueListDTO>

    /**
     * Get the master league
     */
    @GET("/tft/league/v1/master")
    fun getMasterLeague(): Call<LeagueListDTO>
}