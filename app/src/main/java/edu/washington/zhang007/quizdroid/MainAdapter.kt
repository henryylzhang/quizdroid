package edu.washington.zhang007.quizdroid

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.topic_layout.view.*
import java.io.Serializable

class MainAdapter : RecyclerView.Adapter<ViewHolder>() {

    private val topicTitles = listOf("Math", "Physics", "Marvel Superheroes")
    private val topicOverviews = arrayOf("These are numbers", "Physics is math but worse", "Marvel > DC")
    private val mathQuestions = arrayOf("What is 2 + 2?", "How many degrees is a right angle?")
    private val physicsQuestions = arrayOf("F = ")
    private val superQuestions = arrayOf("What was the first Marvel movie?")
    private val questions = arrayOf(mathQuestions, physicsQuestions, superQuestions)
    private val mathAnswers = arrayOf(arrayOf("1, 2, 3, 4"), arrayOf("60, 90, 120, 180"))
    private val physicsAnswers = arrayOf("ma, mc^2, 42, meow?")
    private val superAnswers = arrayOf("Iron Man, Thor, Incredible Hulk, Avengers")
    private val answers = arrayOf(mathAnswers, physicsAnswers, superAnswers)

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
        holder.answers = answers[position]
    }
}

class ViewHolder(val view: View, var topics: String? = null,
                 var overviews: String? = null, var questions: Array<String>? = null,
                 var answers: Array<out Serializable>? = null) : RecyclerView.ViewHolder(view) {

    companion object {
        val TOPIC_NAME = "TOPIC_NAME"
        val TOPIC_OVERVIEW = "TOPIC_OVERVIEW"
        val TOPIC_QUESTIONS = "TOPIC_QUESTION"
        val TOPIC_ANSWERS = "TOPIC_ANSWER"
    }

    init {
        view.setOnClickListener {
            val intent = Intent(view.context, TopicOverviewActivity::class.java)

            intent.putExtra(TOPIC_NAME, topics)
            intent.putExtra(TOPIC_OVERVIEW, overviews)
            intent.putExtra(TOPIC_QUESTIONS, questions)
            intent.putExtra(TOPIC_ANSWERS, answers)

            view.context.startActivity(intent)
        }
    }
}