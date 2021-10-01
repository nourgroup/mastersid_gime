package fr.mastersid.oummadi.stackoverflow.view

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import fr.mastersid.oummadi.stackoverflow.R
import fr.mastersid.oummadi.stackoverflow.data.Item
import fr.mastersid.oummadi.stackoverflow.data.Question
import fr.mastersid.oummadi.stackoverflow.data.QuestionX

class QuestionsListAdapter : ListAdapter<Question,QuestionItemViewHolder>(Question.DiffCallback()) {

    override fun onCreateViewHolder (parent : ViewGroup , viewType : Int ): QuestionItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.itemlayout , parent , false )
        return QuestionItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuestionItemViewHolder, position: Int) {
        // Récupérer l'objet indexé par position
        val items = getItem(position)
        holder.titre.text           = items.title
        //Log.i("onBindViewHolder",items.items[position].title + ", count : ${items.items[position].answer_count}")
        holder.answerCount.text     = "${items.answer_count}"
    }
}