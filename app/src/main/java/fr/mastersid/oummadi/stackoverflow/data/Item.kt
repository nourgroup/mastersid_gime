package fr.mastersid.oummadi.stackoverflow.data

import androidx.recyclerview.widget.DiffUtil
import androidx.room.Entity
import javax.inject.Inject

@Entity(tableName = "question_table", primaryKeys = ["title"])
data class Item @Inject constructor(
    val answer_count: Int,
    val title: String,
){
    class DiffCallback : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame ( oldItem : Item , newItem : Item ): Boolean {
            return oldItem.title == newItem.title
        }
        override fun areContentsTheSame ( oldItem : Item , newItem : Item ) : Boolean {
            return oldItem.title == newItem.title && oldItem.answer_count ==
                    newItem.answer_count
        }
    }
}