package fr.mastersid.oummadi.stackoverflow.data

import androidx.recyclerview.widget.DiffUtil
import javax.inject.Inject

data class QuestionX @Inject constructor(
    val has_more: Boolean,
    val items: List<Item>,
    val quota_max: Int,
    val quota_remaining: Int
){

}