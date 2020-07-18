package services.endpoints

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface SummonerV4 {
    @Headers(
        "X-Riot-Token: RGAPI-92c3deb2-2b8f-4634-92fc-03388771002f"
    )
    @GET("/lol/summoner/v4/summoners/by-account/{encryptedAccountId}")
    fun getSummonerByAccount(@Path("encryptedAccountId") encryptedAccountId: String): Call<SummonerDTO>

    /**
     * @param summonerName : Summoner Name
     */
    @Headers(
        "X-Riot-Token: RGAPI-92c3deb2-2b8f-4634-92fc-03388771002f"
    )
    @GET("/lol/summoner/v4/summoners/by-name/{summonerName}")
    fun getSummonerByName(@Path("summonerName") summonerName: String): Call<SummonerDTO>

    /**
     * @param encryptedPUUID : Summoner Id
     */
    @Headers(
        "X-Riot-Token: RGAPI-92c3deb2-2b8f-4634-92fc-03388771002f"
    )
    @GET("/lol/summoner/v4/summoners/by-puuid/{encryptedPUUID}")
    fun getSummonerByPUUID(@Path("encryptedPUUID") encryptedPUUID: String): Call<SummonerDTO>

    /**
     * Consistently looking up summoner ids that don't exist will result in a blacklist
     *
     * @param encryptedSummonerId : Summoner Id
     */
    @Headers(
        "X-Riot-Token: RGAPI-92c3deb2-2b8f-4634-92fc-03388771002f"
    )
    @GET("/lol/summoner/v4/summoners/{encryptedSummonerId}")
    fun getSummonerBySummonerId(@Path("encryptedSummonerId") encryptedSummonerId: String): Call<SummonerDTO>
}

data class SummonerDTO(
    val accountId: String,  // Encrypted account ID. Max length 56 characters.
    val profileIconId: Int, // ID of the summoner icon associated with the summoner
    val revisionDate: Long, // Date summoner was last modified specified as epoch milliseconds. The following events will update this timestamp: summoner name change, summoner level change, or profile icon change.
    val name: String,       // Summoner name
    val id: String,         // Encrypted summoner ID. Max length 63 characters
    val puuid: String,      // Encrypted PUUID. Exact length of 78 characters.
    val summonerLevel: Long // Summoner level associated with the summoner
)