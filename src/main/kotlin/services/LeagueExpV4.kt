package services

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LeagueExpV4 {

    /**
     * Get all the league entries
     * @param queue : Note that the queue value must be a valid ranked queue.
     */
    @GET("/lol/league-exp/v4/entries/{queue}/{tier}/{division}")
    fun getAllLeagueEntries(
        @Path("queue") queue: String,
        @Path("tier") tier: String,
        @Path("division") division: String,
        @Query("page") page: Int
    ): Call<Set<LeagueEntryDTO>>
}