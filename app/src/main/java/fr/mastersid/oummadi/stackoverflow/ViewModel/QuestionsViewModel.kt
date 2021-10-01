package fr.mastersid.oummadi.stackoverflow.ViewModel

import android.util.Log
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.mastersid.oummadi.stackoverflow.data.Item
import fr.mastersid.oummadi.stackoverflow.data.Question
import fr.mastersid.oummadi.stackoverflow.data.QuestionX
import fr.mastersid.oummadi.stackoverflow.data.repository.DataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionsViewModel @Inject constructor(var mDataRepository : DataRepository) : ViewModel() {

    var questionList = mDataRepository.questionList

    //val questionList : LiveData<QuestionX>
    //    get() = _questionList

    init {
        //updateQuestionList()
    }

    fun updateQuestionList(){
        viewModelScope.launch(Dispatchers.IO){
            //Log.i("LIST", mDataRepository.getQuestionWebService().toString())
            //Appeler la fonction qui charge les données à partir de l'API
            mDataRepository.loadInDBMYSQLQuestionWebService()
        }
    }
}