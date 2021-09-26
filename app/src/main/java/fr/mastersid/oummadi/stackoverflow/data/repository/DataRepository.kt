package fr.mastersid.oummadi.stackoverflow.data.repository

import android.util.Log
import fr.mastersid.oummadi.stackoverflow.data.QuestionX
import fr.mastersid.oummadi.stackoverflow.data.backend.QuestionWebserviceModule
import fr.mastersid.oummadi.stackoverflow.data.backend.WebServiceApiStackOverfow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

//val api : WebServiceApiStackOverfow
class DataRepository @Inject constructor() {

    lateinit var mWebServiceApiStackOverfow : WebServiceApiStackOverfow

    suspend fun getQuestionWebService() : QuestionX{

    //lateinit var a : WebServiceApiStackOverfow //= QuestionWebserviceModule.provideQuestionWebservice(QuestionWebserviceModule.provideRetrofit(QuestionWebserviceModule.provideMoshi()))
        // le code pour retourner que du texte
        /*a.getQuestionList().enqueue(
                object : Callback < String > {
                    override fun onResponse ( call : Call < String >, response :
                    Response < String >) {
                        Log.d(" Webservice ", "OK: ${ response . body ()}") }
                    override fun onFailure ( call : Call <String >, t: Throwable ) {
                        Log.d(" Webservice ", " Error : ${t.message }") } }
        )*/
        return mWebServiceApiStackOverfow.getQuestionList()

    }
}