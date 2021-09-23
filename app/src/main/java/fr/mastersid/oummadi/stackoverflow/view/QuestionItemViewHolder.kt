package fr.mastersid.oummadi.stackoverflow.view

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.mastersid.oummadi.stackoverflow.R
import org.w3c.dom.Text

class QuestionItemViewHolder(view : View)  : RecyclerView.ViewHolder ( view ){
    val titre = view.findViewById<TextView>(R.id.title)
    val answerCount = view.findViewById<TextView>(R.id.answerCount)
}