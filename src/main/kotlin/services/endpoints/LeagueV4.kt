package services.endpoints

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LeagueV4 {
    /**
     * Get the challenger league for given queue
     */
    @GET("/lol/league/v4/challengerleagues/by-queue/{queue}")
    fun getChallengerLeagueByQueue(@Path("queue") queue: String): Call<LeagueListDTO>

    /**
     * Get league entries in all queues for a given summoner ID
     */
    @GET("/lol/league/v4/entries/by-summoner/{encryptedSummonerId}")
    fun getLeagueEntriesBySummoner(@Path("encryptedSummonerId") encryptedSummonerId: String): Call<Set<LeagueEntryDTO>>

    /**
     * Get all the league entries
     */
    @GET("/lol/league/v4/entries/{queue}/{tier}/{division}")
    fun getAllLeagueEntries(
        @Path("queue") queue: String, // Note that the queue value must be a valid ranked queue
        @Path("tier") tier: String,
        @Path("division") division: String,
        @Query("page") page: Int
    ): Call<Set<LeagueListDTO>>

    /**
     * Get the grandmaster league of a specific queue
     * Consistently looking up league ids that don't exist will result in a blacklist.
     */
    @GET("/lol/league/v4/grandmasterleagues/by-queue/{queue}")
    fun getGrandmasterLeagueByQueue(
        @Path("queue") queue: String // Note that the queue value must be a valid ranked queue
    ): Call<LeagueListDTO>

    /**
     * Get league with given ID, including inactive entries
     */
    @GET("/lol/league/v4/leagues/{leagueId}")
    fun getLeagueById(
        @Path("leagueId") leagueId: String // The UUID of the league
    ): Call<LeagueListDTO>

    /**
     * Get the master league for given queue
     */
    @GET("/lol/league/v4/masterleagues/by-queue/{queue}")
    fun getMasterLeagueByQueue(
        @Path("queue") queue: String // The UUID of the league
    ): Call<LeagueListDTO>

}

data class LeagueListDTO(
    val leagueId: String,
    val entries: List<LeagueItemDTO>,
    val tier: String,
    val name: String,
    val queue: String
)

data class LeagueItemDTO(
    val freshBlood: Boolean,
    val wins: Int,                  // Winning team on Summoners Rift
    val summonerName: String,
    val miniSeries: MiniSeriesDTO,
    val inactive: Boolean,
    val veteran: Boolean,
    val hotStreak: Boolean,
    val rank: String,
    val leaguePoints: Int,
    val losses: Int,                // Losing team on Summoners Rift
    val summonerId: String          // Player's encrypted summonerId
)

data class MiniSeriesDTO(
    val losses: Int,
    val progress: String,
    val target: Int,
    val wins: Int
)

data class LeagueEntryDTO(
    val leagueId: String,
    val summonerId: String,
    val summonerName: String,
    val queueType: String,
    val tier: String,
    val rank: String,
    val leaguePoints: Int,
    val wins: Int,
    val losses: Int,
    val hotStreak: Boolean,
    val veteran: Boolean,
    val freshBlood: Boolean,
    val inactive: Boolean,
    val miniSeries: MiniSeriesDTO
)