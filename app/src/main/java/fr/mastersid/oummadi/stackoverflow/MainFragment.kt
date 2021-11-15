package fr.mastersid.oummadi.stackoverflow

import android.Manifest
import android.app.NotificationManager
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.telephony.SmsManager
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import dagger.hilt.android.AndroidEntryPoint
import fr.mastersid.oummadi.stackoverflow.ViewModel.QuestionsViewModel
import fr.mastersid.oummadi.stackoverflow.data.RequestState
import fr.mastersid.oummadi.stackoverflow.data.backend.StackDao
import fr.mastersid.oummadi.stackoverflow.data.backend.WebServiceApiStackOverfow
import fr.mastersid.oummadi.stackoverflow.data.repository.DataRepository
import fr.mastersid.oummadi.stackoverflow.databinding.FragmentMainBinding
import fr.mastersid.oummadi.stackoverflow.view.QuestionsListAdapter
import java.lang.Exception
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import fr.mastersid.oummadi.stackoverflow.Worker.createChannel
import fr.mastersid.oummadi.stackoverflow.Worker.sendNotificationUpdateDone
import fr.mastersid.oummadi.stackoverflow.data.Question

import javax.inject.Inject
//import fr.mastersid.oummadi.stackoverflow.viewbi

@AndroidEntryPoint
class MainFragment : Fragment() {

    /*@Inject
    lateinit var mWebServiceApiStackOverfow: WebServiceApiStackOverfow

    @Inject
    lateinit var mStackDao: StackDao
    */

    lateinit var _binding : FragmentMainBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMainBinding.inflate(inflater)
        return _binding.root
        //return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*
        * popup for permission
        * */

        val requestPermissionLauncher =
            registerForActivityResult (
                ActivityResultContracts.RequestPermission ()
            ) { isGranted : Boolean ->
                if ( isGranted ) {
                    Toast.makeText(context, "you can send SMS", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(context, "you can't send SMS", Toast.LENGTH_SHORT).show()
                    Snackbar.make (
                        _binding?.coordinator , " Permission is needed to send SMS ",
                        Snackbar.LENGTH_INDEFINITE
                    ).setAction ("GO TO SETTINGS") {
                        startActivity (
                            Intent (
                                Settings.ACTION_APPLICATION_DETAILS_SETTINGS ,
                                Uri. parse ("package:" + requireActivity () . packageName ) ) )
                    }.show()
                }
            }

        val questionAdapter = QuestionsListAdapter()
        { question ->
            Log.d("SMS","init")
            Log.d("SMS","on click")
            try{
                /*
                 * request for permission
                 */

                when {
                    ContextCompat.checkSelfPermission (
                        requireContext () ,
                        Manifest.permission.SEND_SMS
                    ) == PackageManager.PERMISSION_GRANTED -> // permission d´ej`a donn ´ee (4 -> 8a)
                    {
                        Toast.makeText(context, "you ca send SMS", Toast.LENGTH_SHORT).show()
                        sendSMS(question)
                    }
                    shouldShowRequestPermissionRationale (
                        Manifest.permission.SEND_SMS
                    ) -> // l’ application devrait donner une explication (5a -> 5b)
                    {
                        //Toast.makeText(context, "PLEASE !!", Toast.LENGTH_SHORT).show()
                        Snackbar.make (
                            _binding?.coordinator , " Permission is needed to send SMS ",
                            Snackbar.LENGTH_INDEFINITE
                        ).setAction ("GO TO SETTINGS") {
                            startActivity (
                                Intent (
                                    Settings.ACTION_APPLICATION_DETAILS_SETTINGS ,
                                    Uri. parse ("package:" + requireActivity () . packageName ) ) )
                        }.show()
                    }
                    // avant de demander la permission
                    else -> // demander la permission (6)
                    {
                        //Toast.makeText(context, "REQUEST FOR THE PERMISSION !!", Toast.LENGTH_SHORT).show()
                        Snackbar.make(
                            _binding?.coordinator ,
                            "Permission is needed to send SMS" ,
                            Snackbar . LENGTH_INDEFINITE
                        ).setAction(" ALLOW ") {
                            requestPermissionLauncher.launch(Manifest.permission.SEND_SMS)
                        }.show()
                    }
                }
            }catch (ex : Exception){
                Log.d("SMS",ex.message+"")
            }
        }
        //val model = QuestionsViewModel(DataRepository(mWebServiceApiStackOverfow,mStackDao))
        val model : QuestionsViewModel by viewModels()

        /*
            application de layoutManager pour recyclerView
         */
        _binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = questionAdapter
        }

        /*
         * le swipe declenche la fonction dans ViewModel
         */
        _binding.mSwipeRefreshLayout.setOnRefreshListener{
            model.updateQuestionList()
        }

        model.questionList.observe(viewLifecycleOwner){
            Log.i("LISTE","chargement de la liste a commencé !")
            questionAdapter.submitList(it)
            // cacher loading widget SwipeRefreshLayout
            Log.i("LISTE","chargement a terminé !")
        }

        model.vm_view.observe(viewLifecycleOwner){
            when(it){
                RequestState.NONE_OR_DONE -> _binding.mSwipeRefreshLayout.isRefreshing = false
                RequestState.PENDING -> _binding.mSwipeRefreshLayout.isRefreshing = true
            }
            Log.d("LOADING","DONE activity")
        }
        /*

         */
        val notificationManager = ContextCompat.getSystemService(
            requireContext(),
            NotificationManager::class.java
        )
        notificationManager?.createChannel(requireContext())
        notificationManager?.sendNotificationUpdateDone(requireContext(),"data","data")
    }

    fun sendSMS(question : Question){
        Log.d("SMS","before smsManager")
        val smsManager = SmsManager.getDefault()
        Log.d("SMS","before sendText")
        smsManager.sendTextMessage("+33758922837",null,question.title,null,null)
        Log.d("SMS","send +33758922837")

    }
}