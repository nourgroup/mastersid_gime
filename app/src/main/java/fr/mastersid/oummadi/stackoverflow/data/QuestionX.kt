package fr.mastersid.oummadi.stackoverflow.data

data class QuestionX(
    val has_more: Boolean,
    val items: List<Item>,
    val quota_max: Int,
    val quota_remaining: Int
)