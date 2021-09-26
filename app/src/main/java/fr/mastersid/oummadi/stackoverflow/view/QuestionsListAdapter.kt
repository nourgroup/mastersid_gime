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

//ListAdapter < Weather ,WeatherItemViewHolder >( Weather . DiffCallback () )
class QuestionsListAdapter(private val item : QuestionX) : ListAdapter<Item,QuestionItemViewHolder>(Item.DiffCallback()) {

    override fun onCreateViewHolder (parent : ViewGroup , viewType : Int ): QuestionItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.itemlayout , parent , false )
        return QuestionItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuestionItemViewHolder, position: Int) {
        //val aa = getItemId(position)
        holder.titre.text = item.items[position].title
        Log.i("onBindViewHolder",item.items[position].title + ", count : ${item.items[position].answer_count}")
        holder.answerCount.text = "${item.items[position].answer_count}"
    }

    override fun getItemCount(): Int {
        return item.items.size
    }
}