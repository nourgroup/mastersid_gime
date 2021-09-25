package fr.mastersid.oummadi.stackoverflow.data.backend

import fr.mastersid.oummadi.stackoverflow.data.Question
import javax.inject.Inject

class DummyWebservice @Inject constructor() {

    suspend fun getQuestion() : List<Question>{
        return listOf(
                Question("Question 1",2),
                Question("Question 2",31),
                Question("Question 3",31),
        )
    }
}