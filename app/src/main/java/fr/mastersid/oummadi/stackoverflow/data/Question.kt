package fr.mastersid.oummadi.stackoverflow.data

import androidx.recyclerview.widget.DiffUtil
import androidx.room.Entity
import javax.inject.Inject

@Entity(tableName = "question_table", primaryKeys = ["title"])
data class Question @Inject constructor(val title : String,val answer_count : Int){
    class DiffCallback : DiffUtil.ItemCallback<Question>() {
        override fun areItemsTheSame ( oldItem : Question , newItem : Question ): Boolean {
            return oldItem.title == newItem.title
        }
        override fun areContentsTheSame ( oldItem : Question , newItem : Question ) : Boolean {
            return oldItem.title == newItem.title && oldItem.answer_count ==
                    newItem.answer_count
        }
    }
}