package fr.mastersid.oummadi.stackoverflow.data.backend

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fr.mastersid.oummadi.stackoverflow.data.QuestionX
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServiceApiStackOverfow {
    @GET("questions?pagesize=20&order=desc&sort=activity")
    suspend fun getQuestionList (
            @Query("site") site : String = "stackoverflow"
    ): QuestionX
}