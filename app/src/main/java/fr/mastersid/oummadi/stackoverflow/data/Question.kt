package fr.mastersid.oummadi.stackoverflow.data

import androidx.recyclerview.widget.DiffUtil
import javax.inject.Inject

data class Question @Inject constructor(val titre : String,val answer_count : Int){
    class DiffCallback : DiffUtil.ItemCallback<Question>() {
        override fun areItemsTheSame ( oldItem : Question , newItem : Question ): Boolean {
            return oldItem.titre == newItem.titre
        }
        override fun areContentsTheSame ( oldItem : Question , newItem : Question ) : Boolean {
            return oldItem.titre == newItem.titre && oldItem.answer_count ==
                    newItem.answer_count
        }
    }
}