package fr.mastersid.oummadi.stackoverflow.data.repository

import android.app.Application
import android.util.Log
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import fr.mastersid.oummadi.stackoverflow.Worker.UpdateWorker
import fr.mastersid.oummadi.stackoverflow.data.Item
import fr.mastersid.oummadi.stackoverflow.data.QuestionX
import fr.mastersid.oummadi.stackoverflow.data.RequestState
import fr.mastersid.oummadi.stackoverflow.data.backend.QuestionWebserviceModule
import fr.mastersid.oummadi.stackoverflow.data.backend.StackDao
import fr.mastersid.oummadi.stackoverflow.data.backend.WebServiceApiStackOverfow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class DataRepository @Inject constructor(
    private val mWebServiceApiStackOverfow:WebServiceApiStackOverfow,
    private val mStackDoa: StackDao,
    private val application: Application
    ) {

    var repo_vm = MutableStateFlow(RequestState.NONE_OR_DONE)
    // base de données
    var questionList = mStackDoa.getQuestionsList()

    suspend fun updateWeatherList () {
        Log.d("workManager","just updateWeatherList")
        val workManager = WorkManager.getInstance(application)
        /*
        val dataBuilder = Data . Builder ()
        dataBuilder . putFloat ( UpdateWorker . LATITUDE_KEY , latitude )
        dataBuilder . putFloat ( UpdateWorker . LONGITUDE_KEY , longitude )
        val data = dataBuilder . build ()
        */
        val updateWorkRequest = OneTimeWorkRequestBuilder<UpdateWorker>()
            //.setInputData ( data )
            .build ()
        workManager.enqueue(updateWorkRequest)
        Log.d("workManager","after enqueue")
    }
    suspend fun insertInNoSqlAndGetQuestionWebService() {
            repo_vm.emit(RequestState.PENDING)
        try{
            /*
            simuler un retard pour tester le WorkerManager
            l'action à faire pour tester est :
            Sortir de l'appli et remarquer que l'affichage
            toujours tourne.
             */
            for(i in 1..10) {
                Log.d("workManager", "Delay i=$i")
                delay(1000)
            }
            /**
             *
             */
            mStackDoa.getQuestionsList()
            //Log.d("LOADING","APPEL PI")
            mStackDoa.insertAll(mWebServiceApiStackOverfow.getQuestionList())
            //Log.d("LOADING","INSERT IN DB")
            Log.d("workManager","after insterAll()")
        }finally {
            /*
            Remarquer que j'ai utilisé deux instances de
            repository un dans ViewModel et 2ème dans
            WorkerManager
            La solution est d'utiliser @Singleton
             */
            repo_vm.emit(RequestState.NONE_OR_DONE)
            Log.d("workManager","emit")
            Log.d("workManager",RequestState.NONE_OR_DONE.toString())
        }
    }
}