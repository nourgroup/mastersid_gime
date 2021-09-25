package fr.mastersid.oummadi.stackoverflow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import fr.mastersid.oummadi.stackoverflow.data.repository.DataRepository
import fr.mastersid.oummadi.stackoverflow.view.QuestionsListAdapter

@AndroidEntryPoint
class MainFragment : Fragment() {
    var questionAdapter: QuestionsListAdapter? = null

    //lateinit var mWebServiceApiStackOverfow: WebServiceApiStackOverfow

    lateinit var mDataRepository : DataRepository
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_main, container, false)
        var recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        //var mySwipeRefreshLayout = findViewById<SwipeRefreshLayout>(R.id.mySwipeRefreshLayout)

        mDataRepository = DataRepository()
        var model = QuestionsViewModel(mDataRepository)

        /*mySwipeRefreshLayout.setOnRefreshListener {
            // mettre le code pour la liste par defaut
        }*/

        model.questionList.observe(viewLifecycleOwner , Observer {
            questionAdapter = QuestionsListAdapter(it)
            recyclerView.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(context)
                adapter = questionAdapter
            }
        })
        return view
    }


}