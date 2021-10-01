package fr.mastersid.oummadi.stackoverflow.data.backend


import com.squareup.moshi.FromJson
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fr.mastersid.oummadi.stackoverflow.data.Item
import fr.mastersid.oummadi.stackoverflow.data.Question
import fr.mastersid.oummadi.stackoverflow.data.QuestionX
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://api.stackexchange.com/2.3/"

@Module
@InstallIn(SingletonComponent::class)
object QuestionWebserviceModule {

    @Provides
    @Singleton
    fun provideMoshi():Moshi {
        return Moshi.Builder ()
                .add(KotlinJsonAdapterFactory())
                .add(QuestionsMoshiAdapter())
                .build()
    }

    @Provides
    fun provideRetrofit(moshi:Moshi) : Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BASE_URL).build()
    }

    @Provides
    fun provideQuestionWebservice(retrofit:Retrofit): WebServiceApiStackOverfow {
        return retrofit.create(WebServiceApiStackOverfow::class.java)
    }

    class QuestionsMoshiAdapter {
        @FromJson
        fun fromJson(listQuestionsJson : QuestionX): List <Question> {
            return listQuestionsJson.items.map {
                    questionsJson -> Question( questionsJson.title , questionsJson.answer_count ) } }
    }
}
