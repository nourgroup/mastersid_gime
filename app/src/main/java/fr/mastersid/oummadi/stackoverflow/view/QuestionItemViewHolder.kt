package fr.mastersid.oummadi.stackoverflow.view

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.mastersid.oummadi.stackoverflow.R
import org.w3c.dom.Text

class QuestionItemViewHolder(view : View)  : RecyclerView.ViewHolder ( view ){
    val titre : TextView= view.findViewById(R.id.title)
    val answerCount : TextView= view.findViewById(R.id.answerCount)
    val sendSMS : ImageView= view.findViewById(R.id.sendSMS)
}