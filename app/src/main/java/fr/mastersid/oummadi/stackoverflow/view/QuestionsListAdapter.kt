package fr.mastersid.oummadi.stackoverflow.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.mastersid.oummadi.stackoverflow.R
import fr.mastersid.oummadi.stackoverflow.data.Question

class QuestionsListAdapter(val item : List<Question>) : RecyclerView.Adapter <QuestionItemViewHolder>() {

    override fun onCreateViewHolder (parent : ViewGroup , viewType : Int ): QuestionItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.itemlayout , parent , false )
        return QuestionItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuestionItemViewHolder, position: Int) {
        //val aa = getItemId(position)
        holder.titre.text = item[position].titre
        holder.answerCount.text = "${item[position].answerCount}"
    }

    override fun getItemCount(): Int {
        return item.size
    }

}