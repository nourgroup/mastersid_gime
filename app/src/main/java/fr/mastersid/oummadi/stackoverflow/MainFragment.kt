package fr.mastersid.oummadi.stackoverflow

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import dagger.hilt.android.AndroidEntryPoint
import fr.mastersid.oummadi.stackoverflow.ViewModel.QuestionsViewModel
import fr.mastersid.oummadi.stackoverflow.data.backend.StackDao
import fr.mastersid.oummadi.stackoverflow.data.backend.WebServiceApiStackOverfow
import fr.mastersid.oummadi.stackoverflow.data.repository.DataRepository
import fr.mastersid.oummadi.stackoverflow.view.QuestionsListAdapter
import javax.inject.Inject
//import fr.mastersid.oummadi.stackoverflow.databinding.FragmentQuestionsBinding

@AndroidEntryPoint
class MainFragment : Fragment() {


    @Inject
    lateinit var mWebServiceApiStackOverfow: WebServiceApiStackOverfow

    @Inject
    lateinit var mStackDao: StackDao

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        val mySwipeRefreshLayout = view.findViewById<SwipeRefreshLayout>(R.id.mSwipeRefreshLayout)
        val questionAdapter = QuestionsListAdapter()
        val model = QuestionsViewModel(DataRepository(mWebServiceApiStackOverfow,mStackDao))
        //val model : QuestionsViewModel by viewModels()

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = questionAdapter
        }

        mySwipeRefreshLayout.setOnRefreshListener{
            model.updateQuestionList()
        }

        model.questionList.observe(viewLifecycleOwner)  {
            Log.i("LISTE","chargement de la liste a commencé !")
            mySwipeRefreshLayout.isRefreshing = false
            questionAdapter.submitList(it)
            // cacher loading widget SwipeRefreshLayout
            Log.i("LISTE","chargement a terminé !")
        }

        return view
    }
}