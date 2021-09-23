package fr.mastersid.oummadi.stackoverflow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import dagger.hilt.android.AndroidEntryPoint
import fr.mastersid.oummadi.stackoverflow.repository.DataRepository
import fr.mastersid.oummadi.stackoverflow.view.QuestionsListAdapter


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    var questionAdapter: QuestionsListAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        //var mySwipeRefreshLayout = findViewById<SwipeRefreshLayout>(R.id.mySwipeRefreshLayout)

        var model = QuestionsViewModel()
        //var model = ViewModelProvider(this).get(QuestionsViewModel::class.java)
        /*mySwipeRefreshLayout.setOnRefreshListener {
            // mettre le code pour la liste par defaut
        }*/

        model.updateQuestionList()?.observe(this, Observer {
            questionAdapter = QuestionsListAdapter(it)
            recyclerView.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(context)
                adapter = questionAdapter
            }
        })

    }
}