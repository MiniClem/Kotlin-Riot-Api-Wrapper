package services

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ThirdPartyCodeV4 {

    /**
     * Get third party code for a given summoner ID
     *
     * Valid codes must be no longer than 256 characters and only use valid characters: 0-9, a-z, A-Z, and -
     */
    @GET("/lol/platform/v4/third-party-code/by-summoner/{encryptedSummonerId}")
    fun get3rdPartyCodeBySummonerId(@Path("encryptedSummonerId") encryptedSummonerId: String): Call<String>
}