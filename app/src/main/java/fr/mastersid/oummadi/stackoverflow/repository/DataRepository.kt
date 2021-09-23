package fr.mastersid.oummadi.stackoverflow.repository

import fr.mastersid.oummadi.stackoverflow.backend.ApiService
import fr.mastersid.oummadi.stackoverflow.data.Question
import javax.inject.Inject

class DataRepository @Inject constructor(val api : ApiService) {

    suspend fun getQuestion() : List<Question> {
        return api.getQuestion()
    }
}