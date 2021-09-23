package fr.mastersid.oummadi.stackoverflow.repository

import fr.mastersid.oummadi.stackoverflow.data.Question
import javax.inject.Inject

class DataRepository @Inject constructor() {

    fun getQuestion() : List<Question> {
        return listOf(
            Question("Question 1",2),
            Question("Question 2",31),
                Question("Question 3",31),
        )
    }
}