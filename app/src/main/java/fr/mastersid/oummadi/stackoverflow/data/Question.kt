package fr.mastersid.oummadi.stackoverflow.data

import javax.inject.Inject

data class Question @Inject constructor(val titre : String,val answerCount : Int)