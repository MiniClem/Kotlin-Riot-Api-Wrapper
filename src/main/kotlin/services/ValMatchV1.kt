package services

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ValMatchV1 {

    /**
     * Get match by id
     */
    @GET("/val/match/v1/matches/{matchId}")
    fun getMatchById(@Path("matchId") matchId: String): Call<MatchDto>

    /**
     * Get matchlist for games played by puuid
     */
    @GET("/val/match/v1/matchlists/by-puuid/{puuid}")
    fun getMatchlistByPuuid(@Path("puuid") puuid: String): Call<MatchlistDto>

    data class MatchDto(
        val matchInfo: List<MatchInfoDto>,
        val players: List<PlayerDto>,
        val teams: List<TeamDto>,
        val roundResults: List<RoundResultDto>
    )
}

data class MatchInfoDto(
    val matchId: String,
    val mapId: String,
    val gameLengthMillis: Int,
    val gameStartMillis: Long,
    val provisioningFlowId: String,
    val isCompleted: Boolean,
    val customGameName: String,
    val queueId: String,
    val gameMode: String,
    val isRanked: Boolean,
    val seasonId: String
)

data class PlayerDto(
    val puuid: String,
    val teamId: String,
    val partyId: String,
    val characterId: String,
    val stats: PlayerStatsDto,
    val competitiveTier: Int,
    val playerCard: String,
    val playerTitle: String
)

data class PlayerStatsDto(
    val puuid: String,
    val kills: List<KillDto>,
    val damage: List<DamageDto>,
    val score: Int,
    val economy: EconomyDto,
    val ability: AbilityDto
)

/**
 * @param killer PUUID
 * @param victim PUUID
 * @param assistants List of PUUIDs
 */
data class KillDto(
    val gameTime: Int,
    val roundTime: Int,
    val killer: String,
    val victim: String,
    val victimLocation: LocationDto,
    val assistants: List<String>,
    val playerLocations: List<PlayerLocationsDto>,
    val finishingDamage: FinishingDamageDto
)

data class LocationDto(
    val x: Int,
    val y: Int
)

data class PlayerLocationsDto(
    val puuid: String,
    val viewRadians: Float,
    val location: LocationDto
)

data class FinishingDamageDto(
    val damageType: String,
    val damageItem: String,
    val isSecondaryFireMode: Boolean
)

/**
 * @param receiver PUUID
 */
data class DamageDto(
    val receiver: String,
    val damage: Int,
    val legshots: Int,
    val bodyshots: Int,
    val headshots: Int
)

data class EconomyDto(
    val loadoutValue: Int,
    val weapon: String,
    val armor: String,
    val remaining: Int,
    val spent: Int
)

data class AbilityDto(
    val grenadeEffects: String,
    val ability1Effects: String,
    val ability2Effects: String,
    val ultimateEffects: String
)

data class TeamDto(
    val teamId: String,
    val won: Boolean,
    val roundsPlayed: Int,
    val roundsWon: Int
)

/**
 * @param bombPlanter PUUID of player
 * @param bombDefuser PUUID of player
 */
data class RoundResultDto(
    val roundNum: Int,
    val roundResult: String,
    val roundCeremony: String,
    val winningTeam: String,
    val bombPlanter: String,
    val bombDefuser: String,
    val plantRoundTime: Int,
    val plantPlayerLocations: List<PlayerLocationsDto>,
    val plantLocation: LocationDto,
    val plantSite: String,
    val defuseRoundTime: Int,
    val defusePlayerLocations: List<PlayerLocationsDto>,
    val defuseLocation: LocationDto,
    val playerStats: List<PlayerStatsDto>,
    val roundResultCode: String
)