package ru.topbun.data.api

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import ru.topbun.data.BuildConfig
import ru.topbun.domain.entity.IssueEntity
import ru.topbun.data.api.dto.GetModsResponse
import ru.topbun.data.api.dto.ModDto
import ru.topbun.domain.entity.ModEntity
import ru.topbun.domain.entity.ModSortType
import ru.topbun.domain.entity.ModType

interface ApiService {

    @POST("/v1/apps/{id}/issue")
    suspend fun createIssue(@Path("id") id: Int = BuildConfig.APP_ID, @Body issue: IssueEntity)

    @GET("/v1/apps/{appId}/mod/{status}")
    suspend fun getMods(
        @Query("q") q: String,
        @Query("category") category: ModType?,
        @Query("sort_key") sortKey: ModSortType,
        @Query("skip") skip: Int,
        @Query("take") take: Int = 10,
        @Path("appId") appId: Int = BuildConfig.APP_ID,
        @Path("status") status: String = "actived"
    ): GetModsResponse

    @GET("/v1/mod/{id}")
    suspend fun getMod(@Path("id") id: Int): ModDto
}