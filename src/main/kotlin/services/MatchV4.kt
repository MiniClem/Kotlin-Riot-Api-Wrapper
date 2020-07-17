package services

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MatchV4 {

    /**
     * Get match IDs by tournament code
     * @param tournamentCode The tournament code
     */
    @GET("/lol/match/v4/matches/by-tournament-code/{tournamentCode}/ids")
    fun getMatchIdsByTournamentCode(@Path("tournamentCode") tournamentCode: String): Call<Long>

    /**
     * Get match by match ID
     * @param matchId The match ID
     */
    @GET("/lol/match/v4/matches/{matchId}")
    fun getMatchById(@Path("matchId") matchId: Long): Call<MatchDto>

    /**
     * Get match by match ID and tournament code
     * @param matchId The match ID
     * @param tournamentCode The tournament code
     */
    @GET("/lol/match/v4/matches/{matchId}/by-tournament-code/{tournamentCode}")
    fun getMatchByIdAndTournament(
        @Path("matchId") matchId: Long,
        @Path("tournamentCode") tournamentCode: String
    ): Call<MatchDto>

    /**
     * Get matchlist for games played on given account ID and platform ID and filtered using given filter parameters, if any.
     *
     * A number of optional parameters are provided for filtering. It is up to the caller to ensure that the combination
     * of filter parameters provided is valid for the requested account, otherwise, no matches may be returned. If
     * beginIndex is specified, but not endIndex, then endIndex defaults to beginIndex+100. If endIndex is specified,
     * but not beginIndex, then beginIndex defaults to 0. If both are specified, then endIndex must be greater than
     * beginIndex. The maximum range allowed is 100, otherwise a 400 error code is returned. If beginTime is specified,
     * but not endTime, then endTime defaults to the the current unix timestamp in milliseconds (the maximum time range
     * limitation is not observed in this specific case). If endTime is specified, but not beginTime, then beginTime
     * defaults to the start of the account's match history returning a 400 due to the maximum time range limitation.
     * If both are specified, then endTime should be greater than beginTime. The maximum time range allowed is one week,
     * otherwise a 400 error code is returned.
     *
     * @param champion Set of champion IDs for filtering the matchlist
     * @param queue Set of queue IDs for filtering the matchlist
     * @param season DEPRECATED This field should not be considered reliable for the purposes of filtering matches by season.
     * @param endTime The end time to use for filtering matchlist specified as epoch milliseconds. If beginTime is
     *  specified, but not endTime, then endTime defaults to the the current unix timestamp in milliseconds (the maximum
     *  time range limitation is not observed in this specific case). If endTime is specified, but not beginTime,
     *  then beginTime defaults to the start of the account's match history returning a 400 due to the maximum time
     *  range limitation. If both are specified, then endTime should be greater than beginTime. The maximum time range
     *  allowed is one week, otherwise a 400 error code is returned.
     * @param beginTime The begin time to use for filtering matchlist specified as epoch milliseconds. If beginTime is
     *  specified, but not endTime, then endTime defaults to the the current unix timestamp in milliseconds
     *  (the maximum time range limitation is not observed in this specific case). If endTime is specified, but not
     *  beginTime, then beginTime defaults to the start of the account's match history returning a 400 due to the
     *  maximum time range limitation. If both are specified, then endTime should be greater than beginTime. The
     *  maximum time range allowed is one week, otherwise a 400 error code is returned
     * @param endIndex The end index to use for filtering matchlist. If beginIndex is specified, but not endIndex, then
     *  endIndex defaults to beginIndex+100. If endIndex is specified, but not beginIndex, then beginIndex defaults to
     *  0. If both are specified, then endIndex must be greater than beginIndex. The maximum range allowed is 100,
     *  otherwise a 400 error code is returned
     * @param beginIndex The begin index to use for filtering matchlist. If beginIndex is specified, but not endIndex,
     *  then endIndex defaults to beginIndex+100. If endIndex is specified, but not beginIndex, then beginIndex defaults
     *  to 0. If both are specified, then endIndex must be greater than beginIndex. The maximum range allowed is 100,
     *  otherwise a 400 error code is returned
     */
    @GET("/lol/match/v4/matchlists/by-account/{encryptedAccountId}")
    fun getMatchlistByAccount(
        @Path("encryptedAccountId") encryptedAccountId: String,
        @Query("champion") champion: Set<Int>?,
        @Query("season") season: Set<Int>?,
        @Query("endTime") endTime: Long?,
        @Query("beginTime") beginTime: Long?,
        @Query("endIndex") endIndex: Int?,
        @Query("beginIndex") beginIndex: Int?
    ): Call<MatchlistDto>


    /**
     * Get match timeline by match ID
     * Not all matches have timeline data
     *
     * @param matchId The match ID
     */
    @GET("/lol/match/v4/timelines/by-match/{matchId}")
    fun getMatchTimelineById(@Path("matchId") matchId: Long): Call<MatchTimelineDto>
}

data class MatchDto(
    val gameId: Long,
    val participantIdentities: List<ParticipantIdentityDto>,    // Participant identity information. Participant identity information is purposefully excluded for custom games.
    val queueId: Int,                                           // Please refer to the Game Constants documentation
    val gameType: String,                                       // Please refer to the Game Constants documentation
    val gameDuration: Long,                                     // Match duration in seconds
    val teams: List<TeamStatsDto>,                              // Team information
    val platformId: String,                                     // Platform where the match was played
    val gameCreation: Long,                                     // Designates the timestamp when champion select ended and the loading screen appeared, NOT when the game timer was at 0:00
    val seasonId: Int,                                          // Please refer to the Game Constants documentation
    val gameVersion: String,                                    // The major.minor version typically indicates the patch the match was played on
    val mapId: Int,                                             // Please refer to the Game Constants documentation
    val gameMode: String,                                       // Please refer to the Game Constants documentation
    val participants: List<ParticipantDto>                      // Participant information
)

data class ParticipantIdentityDto(
    val participantId: Int,
    val player: PlayerDTO   // Player information not included in the response for custom matches. Custom matches are considered private unless a tournament code was used to create the match.
)

data class TeamStatsDto(
    val towerKills: Int,            // Number of towers the team destroyed
    val riftHeraldKills: Int,       // Number of times the team killed Rift Herald
    val firstBlood: Boolean,        // Flag indicating whether or not the team scored the first blood
    val inhibitorKills: Int,        // Number of inhibitors the team destroyed
    val bans: List<TeamBansDto>,    // If match queueId has a draft, contains banned champion data, otherwise empty
    val firstBaron: Boolean,        // Flag indicating whether or not the team scored the first Baron kill
    val firstDragon: Boolean,       // Flag indicating whether or not the team scored the first Dragon kill
    val dominionVictoryScore: Int,  // For Dominion matches, specifies the points the team had at game end.
    val dragonKills: Int,           // Number of times the team killed Dragon
    val baronKills: Int,            // Number of times the team killed Baron
    val firstInhibitor: Boolean,    // Flag indicating whether or not the team destroyed the first inhibitor
    val firstTower: Boolean,        // Flag indicating whether or not the team destroyed the first tower
    val vilemawKills: Int,          // Number of times the team killed Vilemaw
    val firstRiftHerald: Boolean,   // Flag indicating whether or not the team scored the first Rift Herald kill.
    val teamId: Int,                // 100 for blue side. 200 for red side
    val win: String                 // String indicating whether or not the team won. There are only two values visibile in public match history. (Legal values: Fail, Win)
)

data class TeamBansDto(
    val championId: Int,            // Banned championId
    val pickTurn: Int              // Turn during which the champion was banned
)

data class ParticipantDto(
    val participantId: Int,
    val championId: Int,
    val runes: List<RuneDto>,               // List of legacy Rune information. Not included for matches played with Runes Reforged
    val stats: ParticipantStatsDto,         // Participant statistics
    val teamId: Int,                        // 100 for blue side. 200 for red side
    val timeline: ParticipantTimelineDto,   // Participant timeline data
    val spell1Id: Int,                      // First Summoner Spell id
    val spell2Id: Int,                      // Second Summoner Spell id.
    val highestAchievedSeasonTier: String,  // Highest ranked tier achieved for the previous season in a specific subset of queueIds, if any, otherwise null. Used to display border in game loading screen. Please refer to the Ranked Info documentation. (Legal values: CHALLENGER, MASTER, DIAMOND, PLATINUM, GOLD, SILVER, BRONZE, UNRANKED)
    val masteries: List<MasteryDto>         // List of legacy Mastery information. Not included for matches played with Runes Reforged
)

data class RuneDto(
    val runeId: Int,
    val rank: Int
)

data class ParticipantStatsDto(
    val item0: Int,
    val item2: Int,
    val totalUnitsHealed: Int,
    val item1: Int,
    val largestMultiKill: Int,
    val goldEarned: Int,
    val firstInhibitorKill: Boolean,
    val physicalDamageTaken: Long,
    val nodeNeutralizeAssist: Int,
    val totalPlayerScore: Int,
    val champLevel: Int,
    val damageDealtToObjectives: Long,
    val totalDamageTaken: Long,
    val neutralMinionsKilled: Int,
    val deaths: Int,
    val tripleKills: Int,
    val magicDamageDealtToChampions: Long,
    val wardsKilled: Int,
    val pentaKills: Int,
    val largestCriticalStrike: Int,
    val damageSelfMitigated: Long,
    val nodeNeutralize: Int,
    val totalTimeCrowdControlDealt: Int,
    val firstTowerKill: Boolean,
    val magicDamageDealt: Long,
    val totalScoreRank: Int,
    val nodeCapture: Int,
    val wardsPlaced: Int,
    val totalDamageDealt: Long,
    val timeCCingOthers: Long,
    val magicalDamageTaken: Long,
    val largestKillingSpree: Int,
    val totalDamageDealtToChampions: Long,
    val physicalDamageDealtToChampions: Long,
    val neutralMinionsKilledTeamJungle: Int,
    val totalMinionsKilled: Int,
    val firstInhibitorAssist: Boolean,
    val visionWardsBoughtInGame: Int,
    val objectivePlayerScore: Int,
    val kills: Int,
    val firstTowerAssist: Boolean,
    val combatPlayerScore: Int,
    val inhibitorKills: Int,
    val turretKills: Int,
    val participantId: Int,
    val trueDamageTaken: Long,
    val firstBloodAssist: Boolean,
    val nodeCaptureAssist: Int,
    val assists: Int,
    val teamObjective: Int,
    val altarsNeutralized: Int,
    val goldSpent: Int,
    val damageDealtToTurrets: Long,
    val altarsCaptured: Int,
    val win: Boolean,
    val totalHeal: Long,
    val unrealKills: Int,
    val visionScore: Long,
    val physicalDamageDealt: Long,
    val firstBloodKill: Boolean,
    val LongestTimeSpentLiving: Int,
    val killingSprees: Int,
    val sightWardsBoughtInGame: Int,
    val trueDamageDealtToChampions: Long,
    val neutralMinionsKilledEnemyJungle: Int,
    val doubleKills: Int,
    val trueDamageDealt: Long,
    val quadraKills: Int,
    val item4: Int,
    val item3: Int,
    val item6: Int,
    val item5: Int,
    val playerScore0: Int,
    val playerScore1: Int,
    val playerScore2: Int,
    val playerScore3: Int,
    val playerScore4: Int,
    val playerScore5: Int,
    val playerScore6: Int,
    val playerScore7: Int,
    val playerScore8: Int,
    val playerScore9: Int,
    val perk0: Int,             //Primary path keystone rune.
    val perk0Var1: Int,         //Post game rune stats.,
    val perk0Var2: Int,         //Post game rune stats.,
    val perk0Var3: Int,         //Post game rune stats.,
    val perk1: Int,             //Primary path rune.,
    val perk1Var1: Int,         //Post game rune stats.,
    val perk1Var2: Int,         //Post game rune stats.,
    val perk1Var3: Int,         // Post game rune stats.,
    val perk2: Int,             // Primary path rune.,
    val perk2Var1: Int,         // Post game rune stats.,
    val perk2Var2: Int,         // Post game rune stats.,
    val perk2Var3: Int,         // Post game rune stats.,
    val perk3: Int,             // Primary path rune.,
    val perk3Var1: Int,         // Post game rune stats.,
    val perk3Var2: Int,         // Post game rune stats.,
    val perk3Var3: Int,         // Post game rune stats.,
    val perk4: Int,             // Secondary path rune.,
    val perk4Var1: Int,         // Post game rune stats.,
    val perk4Var2: Int,         // Post game rune stats.,
    val perk4Var3: Int,         // Post game rune stats.,
    val perk5: Int,             // Secondary path rune.,
    val perk5Var1: Int,         // Post game rune stats.,
    val perk5Var2: Int,         // Post game rune stats.,
    val perk5Var3: Int,         // Post game rune stats.,
    val perkPrimaryStyle: Int,  // Primary rune path,
    val perkSubStyle: Int      // Secondary rune path,
)

data class ParticipantTimelineDto(
    val participantId: Int,
    val csDiffPerMinDeltas: Map<String, Double>,            // Creep score difference versus the calculated lane opponent(s) for a specified period.
    val damageTakenPerMinDeltas: Map<String, Double>,       // Damage taken for a specified period.
    val role: String,                                       // Participant's calculated role. (Legal values: DUO, NONE, SOLO, DUO_CARRY, DUO_SUPPORT)
    val damageTakenDiffPerMinDeltas: Map<String, Double>,   // Damage taken difference versus the calculated lane opponent(s) for a specified period.
    val xpPerMinDeltas: Map<String, Double>,                // Experience change for a specified period.
    val xpDiffPerMinDeltas: Map<String, Double>,            // Experience difference versus the calculated lane opponent(s) for a specified period.
    val lane: String,                                       // Participant's calculated lane. MID and BOT are legacy values. (Legal values: MID, MIDDLE, TOP, JUNGLE, BOT, BOTTOM)
    val creepsPerMinDeltas: Map<String, Double>,            // Creeps for a specified period.
    val goldPerMinDeltas: Map<String, Double>               // Gold for a specified period.
)

data class MasteryDto(
    val rank: Int,
    val masteryId: Int
)

/**
 * @param totalGames There is a known issue that this field doesn't correctly return the total number of games that
 *  match the parameters of the request. Please paginate using beginIndex until you reach the end of a player's matchlist.
 *
 */
data class MatchlistDto(
    val startIndex: Int,
    val totalGames: Int,
    val endIndex: Int,
    val matches: List<MatchReferenceDto>
)

data class MatchReferenceDto(
    val gameId: Long,
    val role: String,
    val season: Int,
    val platformId: String,
    val champion: Int,
    val queue: Int,
    val lane: String,
    val timestamp: Long
)

data class MatchTimelineDto(
    val frames: List<MatchFrameDto>,
    val frameInterval: Long
)

data class MatchFrameDto(
    val participantFrames: Map<String, MatchParticipantFrameDto>,
    val events: List<MatchEventDto>,
    val timestamp: Long
)

data class MatchParticipantFrameDto(
    val participantId: Int,
    val minionsKilled: Int,
    val teamScore: Int,
    val dominionScore: Int,
    val totalGold: Int,
    val level: Int,
    val xp: Int,
    val currentGold: Int,
    val position: MatchPositionDto,
    val jungleMinionsKilled: Int
)

data class MatchPositionDto(
    val x: Int,
    val y: Int
)

/**
 * @param position (Legal values: CHAMPION_KILL, WARD_PLACED, WARD_KILL, BUILDING_KILL, ELITE_MONSTER_KILL, ITEM_PURCHASED,
 *  ITEM_SOLD, ITEM_DESTROYED, ITEM_UNDO, SKILL_LEVEL_UP, ASCENDED_EVENT, CAPTURE_POInt, PORO_KING_SUMMON),
 */
data class MatchEventDto(
    val laneType: String,
    val skillSlot: Int,
    val ascendedType: String,
    val creatorId: Int,
    val afterId: Int,
    val eventType: String,
    val type: String,
    val levelUpType: String,
    val wardType: String,
    val participantId: Int,
    val towerType: String,
    val itemId: Int,
    val beforeId: Int,
    val pointCaptured: String,
    val monsterType: String,
    val monsterSubType: String,
    val teamId: Int,
    val position: MatchPositionDto,
    val killerId: Int,
    val timestamp: Long,
    val assistingParticipantIds: List<Int>,
    val buildingType: String,
    val victimId: Int
)