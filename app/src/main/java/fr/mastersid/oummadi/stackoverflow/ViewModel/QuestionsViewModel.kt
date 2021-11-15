package fr.mastersid.oummadi.stackoverflow.ViewModel

import android.app.Application
import android.telephony.SmsManager
import android.util.Log
import androidx.lifecycle.*
import androidx.work.WorkManager
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.mastersid.oummadi.stackoverflow.data.Item
import fr.mastersid.oummadi.stackoverflow.data.Question
import fr.mastersid.oummadi.stackoverflow.data.QuestionX
import fr.mastersid.oummadi.stackoverflow.data.RequestState
import fr.mastersid.oummadi.stackoverflow.data.repository.DataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class QuestionsViewModel @Inject constructor(
    var mDataRepository : DataRepository,
    var application : Application
    ) : ViewModel() {

    var questionList = mDataRepository.questionList

    // On a remplacé le type de MutableLiveData (1) en une seule ligne
    // qui fait l'observation (l'écoute de changement) de la valeur vm_view
    //(1) var vm_view = MutableLiveData(RequestState.PENDING)
    var vm_view = mDataRepository.repo_vm.asLiveData()

    init {
        //updateQuestionList()

        /*(2)   viewModelScope.launch(Dispatchers.IO){
                mDataRepository.repo_vm.collect{
                    vm_view.postValue(it)
                }
            }
        */
        }
    /*
        invoke function
    */
    fun updateQuestionList(){
        viewModelScope.launch(Dispatchers.IO){
            //vm_view.postValue(RequestState.PENDING)
            //Log.i("LIST", mDataRepository.getQuestionWebService().toString())
            //Appeler la fonction qui charge les données à partir de l'API
            Log.i("LOADING","Before InsertInNoSqlAndGetQuestionWebService")
            // cette fonction est déplacer au worker qui va l'excuter
            //mDataRepository.insertInNoSqlAndGetQuestionWebService()
            /*
                Solution avec les Workers:
                passer au repo -> executer Worker ->
            */
            mDataRepository.updateWeatherList()
            Log.i("LOADING","After InsertInNoSqlAndGetQuestionWebService")
            //vm_view.postValue(RequestState.NONE_OR_DONE)
        }
    }


}