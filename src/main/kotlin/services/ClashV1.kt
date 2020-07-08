package services

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ClashV1 {

    /**
     * Get players by summoner ID
     * This endpoint returns a list of active Clash players for a given summoner ID. If a summoner registers for
     * multiple tournaments at the same time (e.g., Saturday and Sunday) then both registrations would appear in this
     * list
     */
    @GET("/lol/clash/v1/players/by-summoner/{summonerId}")
    fun getPlayerBySummonerId(@Path("summonerId") summonerId: String): Call<List<PlayerDTO>>

    /**
     * Get team by ID
     */
    @GET("/lol/clash/v1/teams/{teamId}")
    fun getTeamById(@Path("teamId") teamId: String): Call<TeamDTO>

    /**
     * Get all active or upcoming tournaments
     * Returns a list of active and upcoming tournaments.
     */
    @GET("/lol/clash/v1/tournaments")
    fun getAllActiveOrUpcomingTournaments(): Call<List<TournamentDTO>>

    /**
     * Get tournament by team ID
     */
    @GET("/lol/clash/v1/tournaments/by-team/{teamId}")
    fun getTournamentByTeamId(@Path("teamId") teamId: String): Call<TournamentDTO>

    /**
     * Get tournament by ID
     */
    @GET("/lol/clash/v1/tournaments/{tournamentId}")
    fun getTournamentById(@Path("tournamentId") tournamentId: Int): Call<TournamentDTO>
}

data class PlayerDTO(
    val summonerId: String,
    val teamId: String,
    val position: String,   // (Legal values: UNSELECTED, FILL, TOP, JUNGLE, MIDDLE, BOTTOM, UTILITY)
    val role: String        // (Legal values: CAPTAIN, MEMBER)
)

data class TeamDTO(
    val id: String,
    val tournamentId: Int,
    val name: String,
    val iconId: Int,
    val tier: Int,
    val captain: String,            // Summoner ID of the team captain
    val abbreviation: String,
    val players: List<PlayerDTO>    // Team members.
)

data class TournamentDTO(
    val id: Int,
    val themeId: Int,
    val nameKey: String,
    val nameKeySecondary: String,
    val schedule: List<TournamentPhaseDTO>  // Tournament phase
)

data class TournamentPhaseDTO(
    val id: Int,
    val registrationTime: Long,
    val startTime: Long,
    val cancelled: Boolean
)