package fr.mastersid.oummadi.stackoverflow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.mastersid.oummadi.stackoverflow.data.Question
import fr.mastersid.oummadi.stackoverflow.repository.DataRepository
import javax.inject.Inject

@HiltViewModel
class QuestionsViewModel @Inject constructor() : ViewModel() {
    var mDataRepository = DataRepository()
    var questionList = MutableLiveData<List<Question>>()
    //var dataRepository = mDataRepository
    init {
        updateQuestionList()
    }

    fun updateQuestionList() : MutableLiveData<List<Question>>?{

        questionList?.postValue(mDataRepository.getQuestion())

        return questionList
    }
}