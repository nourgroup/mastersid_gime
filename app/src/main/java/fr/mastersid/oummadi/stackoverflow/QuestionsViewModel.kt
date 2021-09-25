package fr.mastersid.oummadi.stackoverflow

import android.util.Log
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.mastersid.oummadi.stackoverflow.data.Question
import fr.mastersid.oummadi.stackoverflow.data.repository.DataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionsViewModel @Inject constructor(var mDataRepository : DataRepository) : ViewModel() {

    private val _questionList = MutableLiveData<List<Question>>(emptyList())

    val questionList : LiveData<List<Question>>
        get() = _questionList

    init {
        updateQuestionList()
    }

    private fun updateQuestionList(){
        viewModelScope.launch(Dispatchers.IO){
            //_questionList.postValue(mDataRepository.getQuestionWebService())
            //var a : Call<List<QuestionX>> = mDataRepository.getQuestionWebService()
            mDataRepository.getQuestionWebService()
        }
    }
}