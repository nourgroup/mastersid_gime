package fr.mastersid.oummadi.stackoverflow.data.repository

import android.util.Log
import fr.mastersid.oummadi.stackoverflow.data.Item
import fr.mastersid.oummadi.stackoverflow.data.QuestionX
import fr.mastersid.oummadi.stackoverflow.data.RequestState
import fr.mastersid.oummadi.stackoverflow.data.backend.QuestionWebserviceModule
import fr.mastersid.oummadi.stackoverflow.data.backend.StackDao
import fr.mastersid.oummadi.stackoverflow.data.backend.WebServiceApiStackOverfow
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class DataRepository @Inject constructor(
    private val mWebServiceApiStackOverfow:WebServiceApiStackOverfow,
    private val mStackDoa: StackDao
    ) {

    var repo_vm = MutableStateFlow(RequestState.NONE_OR_DONE)
    var questionList = mStackDoa.getQuestionsList()

    suspend fun loadInDBMYSQLQuestionWebService() {
        repo_vm.emit(RequestState.PENDING)
        mStackDoa.insertAll(mWebServiceApiStackOverfow.getQuestionList())
        repo_vm.emit(RequestState.NONE_OR_DONE)
    }
}