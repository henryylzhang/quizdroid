package edu.washington.zhang007.quizdroid

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.topic_layout.view.*

class MainAdapter(val quiz: QuizData) : RecyclerView.Adapter<ViewHolder>() {

    override fun getItemCount(): Int {
        return quiz.topicData.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val topic = layoutInflater.inflate(R.layout.topic_layout, parent, false)
        return ViewHolder(topic)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.view.textView_topic.text = quiz.topicData.get(position).title
        holder.view.textView_shortDesc.text = quiz.topicData[position].shortDesc

        holder.overview = quiz.topicData[position].longDesc
        holder.questions = quiz.topicData[position].questions
    }
}

class ViewHolder(val view: View,
                 var overview: String? = null,
                 var questions: Array<Question>? = null) : RecyclerView.ViewHolder(view) {

    companion object {
        val TOPIC_OVERVIEW = "TOPIC_OVERVIEW"
        val TOPIC_QUESTIONS = "TOPIC_QUESTION"
    }

    init {
        view.setOnClickListener {
            val intent = Intent(view.context, ControllerActivity::class.java)

            intent.putExtra(TOPIC_OVERVIEW, overview)
            intent.putExtra(TOPIC_QUESTIONS, questions)

            view.context.startActivity(intent)
        }
    }
}