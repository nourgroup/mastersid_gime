package fr.mastersid.oummadi.stackoverflow.data.backend

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fr.mastersid.oummadi.stackoverflow.data.QuestionX
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

//@Module
//@InstallIn(SingletonComponent::class)
interface WebServiceApiStackOverfow {
    @GET("questions?pagesize=20&order=desc&sort=activity&site=stackoverflow")
    suspend fun getQuestionList (
            //@Query("site") site : String = "stackoverflow"
    ): List<QuestionX>//Call<String>
}