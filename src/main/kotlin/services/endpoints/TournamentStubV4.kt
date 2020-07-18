package services.endpoints

import retrofit2.Call
import retrofit2.http.*

interface TournamentStubV4 {

    /**
     * Create a mock tournament code for the given tournament.
     *
     * @param count The number of codes to create (max 1000)
     * @param tournamentId The tournament ID
     */
    @POST("/lol/tournament-stub/v4/codes")
    fun createMockTournamentCode(
        @Query("count") count: Int = 1,
        @Query("tournamentId") tournamentId: Long,
        @Body TournamentCodeParameters: TournamentCodeParameters
    ): Call<List<String>>

    /**
     * Gets a mock list of lobby events by tournament code.
     *
     * @param tournamentCode The short code to look up lobby events for
     */
    @GET("/lol/tournament-stub/v4/lobby-events/by-code/{tournamentCode}")
    fun getLobbyEventsByCode(@Path("tournamentCode") tournamentCode: String): Call<LobbyEventDtoWrapper>

    /**
     * Creates a mock tournament provider and returns its ID.
     * Providers will need to call this endpoint first to register their callback URL and their API key with the
     * tournament system before any other tournament provider endpoints will work.
     */
    @POST("/lol/tournament-stub/v4/providers")
    fun createTournamentProvider(@Body ProviderRegistrationParameters: ProviderRegistrationParameters): Call<Int>

    /**
     * Creates a mock tournament and returns its ID.
     */
    @POST("/lol/tournament-stub/v4/tournaments")
    fun createMockTournament(@Body TournamentRegistrationParameters: TournamentRegistrationParameters): Call<Int>
}

/**
 * @param allowedSummonerIds Optional list of encrypted summonerIds in order to validate the players eligible to join
 *      the lobby. NOTE: We currently do not enforce participants at the team level, but rather the aggregate of teamOne and
 *      teamTwo. We may add the ability to enforce at the team level in the future.
 * @param metadata Optional string that may contain any data in any format, if specified at all. Used to denote any
 *      custom information about the game.
 * @param teamSize  The team size of the game. Valid values are 1-5.
 * @param pickType  The pick type of the game. (Legal values: BLIND_PICK, DRAFT_MODE, ALL_RANDOM, TOURNAMENT_DRAFT)
 * @param mapType   The map type of the game. (Legal values: SUMMONERS_RIFT, TWISTED_TREELINE, HOWLING_ABYSS)
 * @param spectatorType The spectator type of the game. (Legal values: NONE, LOBBYONLY, ALL)
 */
data class TournamentCodeParameters(
    val allowedSummonerIds: Set<String>,
    val metadata: String,
    val teamSize: Int,
    val pickType: String,
    val mapType: String,
    val spectatorType: String
)

data class LobbyEventDtoWrapper(
    val eventList: List<LobbyEventDto>
)

/**
 * @param summonerId The summonerId that triggered the event (Encrypted)
 * @param eventType The type of event that was triggered
 * @param timestamp Timestamp from the event
 */
data class LobbyEventDto(
    val summonerId: String,
    val eventType: String,
    val timestamp: String
)

/**
 * @param region The region in which the provider will be running tournaments. (Legal values: BR, EUNE, EUW, JP, LAN,
 *      LAS, NA, OCE, PBE, RU, TR)
 * @param url The provider's callback URL to which tournament game results in this region should be posted. The URL
 *      must be well-formed, use the http or https protocol, and use the default port for the protocol (http URLs must
 *      use port 80, https URLs must use port 443).
 */
data class ProviderRegistrationParameters(
    val region: String,
    val url: String
)

/**
 * @param providerId The provider ID to specify the regional registered provider data to associate this tournament.
 * @param name The optional name of the tournament.
 */
data class TournamentRegistrationParameters(
    val providerId: Int,
    val name: String
)