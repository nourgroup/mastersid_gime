package fr.mastersid.oummadi.stackoverflow.data.backend

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fr.mastersid.oummadi.stackoverflow.data.Question

import retrofit2.http.GET
import retrofit2.http.Query

//pagesize=20&order=desc&sort=activity
interface WebServiceApiStackOverfow {
    @GET("questions?")
    suspend fun getQuestionList (
            @Query("site") site : String = "stackoverflow",
            @Query("sort") sort : String = "activity",
            @Query("order") order : String = "desc",
            @Query("pagesize") pagesize : String = "20",
    ): List<Question>
}