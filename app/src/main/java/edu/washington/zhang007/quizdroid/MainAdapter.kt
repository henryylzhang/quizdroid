package edu.washington.zhang007.quizdroid

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.topic_layout.view.*

class MainAdapter : RecyclerView.Adapter<ViewHolder>() {

    private val topicTitles = listOf("Math", "Physics", "Marvel Superheroes")
    private val topicOverviews = arrayOf("These are numbers", "Physics is math but worse", "Marvel > DC")
    private val mathQuestions = arrayOf("What is 2 + 2?", "What is 2 x 2?")
    private val physicsQuestions = arrayOf("F = ")
    private val superQuestions = arrayOf("What was the first Marvel movie?")
    private val questions = arrayOf(mathQuestions, physicsQuestions, superQuestions)
    private val mathAnswers = arrayOf("1", "2", "3", "4")
    private val physicsAnswers = arrayOf("ma", "mc^2", "42", "meow?")
    private val superAnswers = arrayOf("Iron Man", "Thor", "Incredible Hulk", "Avengers")
    private val answerOptions = arrayOf(mathAnswers, physicsAnswers, superAnswers)
    private val correctAnswers = arrayOf(arrayOf("4", "4"), arrayOf("ma"), arrayOf("Iron Man"))

    override fun getItemCount(): Int {
        return topicTitles.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val topic = layoutInflater.inflate(R.layout.topic_layout, parent, false)
        return ViewHolder(topic)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.view.textView_topic.text = topicTitles[position]

        holder.topics = topicTitles[position]
        holder.overviews = topicOverviews[position]
        holder.questions = questions[position]
        holder.answerOptions = answerOptions[position]
        holder.correctAnswers = correctAnswers[position]
    }
}

class ViewHolder(val view: View, var topics: String? = null,
                 var overviews: String? = null, var questions: Array<String>? = null,
                 var answerOptions: Array<String>? = null,
                 var correctAnswers: Array<String>? = null) : RecyclerView.ViewHolder(view) {

    companion object {
        val TOPIC_NAME = "TOPIC_NAME"
        val TOPIC_OVERVIEW = "TOPIC_OVERVIEW"
        val TOPIC_QUESTIONS = "TOPIC_QUESTION"
        val ANSWER_OPTIONS = "ANSWER_OPTIONS"
        val CORRECT_ANSWERS = "CORRECT_ANSWERS"
    }

    init {
        view.setOnClickListener {
            val intent = Intent(view.context, OverviewActivity::class.java)

            intent.putExtra(TOPIC_NAME, topics)
            intent.putExtra(TOPIC_OVERVIEW, overviews)
            intent.putExtra(TOPIC_QUESTIONS, questions)
            intent.putExtra(ANSWER_OPTIONS, answerOptions)
            intent.putExtra(CORRECT_ANSWERS, correctAnswers)

            view.context.startActivity(intent)
        }
    }
}