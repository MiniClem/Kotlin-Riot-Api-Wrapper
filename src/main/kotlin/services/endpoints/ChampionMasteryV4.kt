package services.endpoints

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface ChampionMasteryV4 {

    /**
     * @param encryptedSummonerId : Summoner ID associated with the player
     */
    @GET("/lol/champion-mastery/v4/champion-masteries/by-summoner/{encryptedSummonerId}")
    fun getChampionMasteriesBySummoner(@Path("encryptedSummonerId") encryptedSummonerId: String): Call<ChampionMasteryDTO>

    /**
     * @param encryptedSummonerId : Summoner ID associated with the player
     * @param championId : Champion ID to retrieve Champion Mastery for
     */
    @GET("/lol/champion-mastery/v4/champion-masteries/by-summoner/{encryptedSummonerId}/by-champion/{championId}")
    fun getChampionMasteriesBySummonerAndChampion(
        @Path("encryptedSummonerId") encryptedSummonerId: String,
        @Path("championId") championId: Long
    ): Call<ChampionMasteryDTO>

    @GET("/lol/champion-mastery/v4/scores/by-summoner/{encryptedSummonerId}")
    fun getChampionMasteriesScoreBySummoner(
        @Path("encryptedSummonerId") encryptedSummonerId: String
    ): Call<Int>
}

data class ChampionMasteryDTO(
    val championPointsUntilNextLevel: Long, // Number of points needed to achieve next level. Zero if player reached maximum champion level for this champion
    val chestGranted: Boolean,              // Is chest granted for this champion or not in current season
    val championId: Long,                   // Champion ID for this entry
    val lastPlayTime: Long,                 // Last time this champion was played by this player - in Unix milliseconds time format
    val championLevel: Int,                 // Champion level for specified player and champion combination
    val summonerId: String,                 // Summoner ID for this entry. (Encrypted)
    val championPoints: Int,                // Total number of champion points for this player and champion combination - they are used to determine championLevel
    val championPointsSinceLastLevel: Long, // Number of points earned since current level has been achieved
    val tokensEarned: Int                   // The token earned for this champion to levelup
)