package services

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface AccountV1 {
    /**
     * Get account by puuid
     */
    @GET("/riot/account/v1/accounts/by-puuid/{puuid}")
    fun getAccountByPuuid(@Path("puuid") puuid: String): Call<AccountDto>

    /**
     * Get account by riot id
     */
    @GET("/riot/account/v1/accounts/by-riot-id/{gameName}/{tagLine}")
    fun getAccountById(@Path("gameName") gameName: String, @Path("tagLine") tagLine: String): Call<AccountDto>

    /**
     * Get active shard for a player
     */
    @GET("/riot/account/v1/active-shards/by-game/{game}/by-puuid/{puuid}")
    fun getActiveShard(@Path("game") game: String, @Path("puuid") puuid: String): Call<ActiveSharDto>
}

data class AccountDto(
    val puuid: String,
    val gameName: String,
    val tagLine: String
)

data class ActiveSharDto(
    val puuid: String,
    val game: String,
    val activeShard: String
)