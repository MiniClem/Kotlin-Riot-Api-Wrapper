package services

import retrofit2.Call
import retrofit2.http.*

interface TournamentV4 {
    /**
     * Create a tournament code for the given tournament.
     *
     * @param count The number of codes to create (max 1000)
     * @param tournamentId The tournament ID
     */
    @POST("/lol/tournament/v4/codes")
    fun createTournamentCode(
        @Query("count") count: Int = 1,
        @Query("tournamentId") tournamentId: Long,
        @Body TournamentCodeParameters: TournamentCodeParameters
    ): Call<List<String>>


    /**
     * Returns the tournament code DTO associated with a tournament code string.
     *
     * @param tournamentCode The tournament code string
     */
    @GET("/lol/tournament/v4/codes/{tournamentCode}")
    fun getTournamentCodeDtoByCode(@Path("tournamentCode") tournamentCode: String): Call<TournamentCodeDto>

    /**
     * Update the pick type, map, spectator type, or allowed summoners for a code
     *
     * @param tournamentCode The tournament code to update
     */
    @PUT("/lol/tournament/v4/codes/{tournamentCode}")
    fun updateTournamentCode(
        @Path("tournamentCode") tournamentCode: String,
        @Body TournamentCodeUpdateParameters: TournamentCodeUpdateParameters
    )

    /**
     * Gets a list of lobby events by tournament code
     *
     * @param tournamentCode The short code to look up lobby events for
     */
    @GET("/lol/tournament/v4/lobby-events/by-code/{tournamentCode}")
    fun getLobbyEventsByCode(@Path("tournamentCode") tournamentCode: String): Call<LobbyEventDtoWrapper>

    /**
     * Creates a tournament provider and returns its ID
     * Providers will need to call this endpoint first to register their callback URL and their API key with the
     * tournament system before any other tournament provider endpoints will work
     */
    @POST("/lol/tournament/v4/providers")
    fun createTournamentProvider(@Body ProviderRegistrationParameters: ProviderRegistrationParameters): Call<Int>

    /**
     * Creates a tournament and returns its ID.
     */
    @POST("/lol/tournament/v4/tournaments")
    fun createTournament(@Body TournamentRegistrationParameters: TournamentRegistrationParameters): Call<Int>
}

/**
 * @param code The tournament code.
 * @param spectators The spectator mode for the tournament code game
 * @param lobbyName The lobby name for the tournament code game
 * @param metaData The metadata for tournament code
 * @param password The password for the tournament code game
 * @param teamSize The team size for the tournament code game
 * @param providerId The provider's ID
 * @param pickType The pick mode for tournament code game
 * @param tournamentId The tournament's ID
 * @param id The tournament code's ID
 * @param region The tournament code's region. (Legal values: BR, EUNE, EUW, JP, LAN, LAS, NA, OCE, PBE, RU, TR)
 * @param map The game map for the tournament code game
 * @param participants The summonerIds of the participants (Encrypted)
 */
data class TournamentCodeDto(
    val code: String,
    val spectators: String,
    val lobbyName: String,
    val metaData: String,
    val password: String,
    val teamSize: Int,
    val providerId: Int,
    val pickType: String,
    val tournamentId: Int,
    val id: Int,
    val region: String,
    val map: String,
    val participants: Set<String>
)

/**
 * @param allowedSummonerIds Optional list of encrypted summonerIds in order to validate the players eligible to join the lobby.
 *      NOTE: We currently do not enforce participants at the team level, but rather the aggregate of teamOne and teamTwo.
 *      We may add the ability to enforce at the team level in the future.
 * @param pickType The pick type (Legal values: BLIND_PICK, DRAFT_MODE, ALL_RANDOM, TOURNAMENT_DRAFT)
 * @param mapType The map type (Legal values: SUMMONERS_RIFT, TWISTED_TREELINE, HOWLING_ABYSS)
 * @param spectatorType The spectator type (Legal values: NONE, LOBBYONLY, ALL)
 */
data class TournamentCodeUpdateParameters(
    val allowedSummonerIds: Set<String>,
    val pickType: String,
    val mapType: String,
    val spectatorType: String
)