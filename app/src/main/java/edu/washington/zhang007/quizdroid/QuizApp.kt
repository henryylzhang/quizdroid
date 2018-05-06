package edu.washington.zhang007.quizdroid

import android.app.Application
import android.util.Log

class QuizApp : Application() {

    private val TAG = "QuizDroid"

    override fun onCreate() {
        super.onCreate()
        Log.i(TAG, "QuizDroid up and running!")
    }

    fun accessRepository(repo: TopicRepository) {
        
    }
}

interface TopicRepository {
    data class Topic(val title: String, val shortDesc: String, val longDesc: String, val questions:Array<Question>)

    data class Question(val questionText: String, val answers: Array<String>, val correctIndex: Int)
}