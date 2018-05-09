package edu.washington.zhang007.quizdroid

import android.app.Application
import android.graphics.drawable.Drawable
import android.util.Log
import java.io.Serializable

class QuizApp : Application() {

    private val TAG = "QuizDroid"

    override fun onCreate() {
        super.onCreate()
        Log.i(TAG, "QuizDroid up and running!")
    }

    companion object {
        val quizData = QuizData()

        init { // why does it have to be in init?
            this.quizData.loadJSONData()
        }
    }
}

interface TopicRepository {
    fun addTopic(t: Topic)
    fun getRepository(): ArrayList<Topic>
    fun removeTopic(t: Topic)
}

data class Topic(val title: String, val shortDesc: String, val longDesc: String,
                 val questions:Array<Question>, val topicIcon: Int) : Serializable

data class Question(val questionText: String, val answerOptions: Array<String>,
                    val correctIndex: Int) : Serializable