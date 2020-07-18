package services.endpoints

import retrofit2.Call
import retrofit2.http.GET

interface LolStatusV3 {

    /**
     * Get League of Legends status for the given shard
     * Requests to this API are not counted against the application Rate Limits
     */
    @GET("/lol/status/v3/shard-data")
    fun getLolShardStatus(): Call<ShardStatus>
}

data class ShardStatus(
    val locales: List<String>,
    val hostname: String,
    val name: String,
    val services: List<Service>,
    val slug: String,
    val region_tag: String
)

data class Service(
    val incidents: List<Incident>,
    val name: String,
    val slug: String,
    val status: String
)

data class Incident(
    val active: Boolean,
    val created_at: String,
    val id: Long,
    val updates: List<Message>
)

data class Message(
    val severity: String,
    val updated_at: String,
    val author: String,
    val translations: List<Translation>,
    val created_at: String,
    val id: String,
    val content: String
)

data class Translation(
    val updated_at: String,
    val locale: String,
    val content: String
)