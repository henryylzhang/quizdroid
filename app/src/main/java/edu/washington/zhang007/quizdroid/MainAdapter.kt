package edu.washington.zhang007.quizdroid

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.topic_layout.view.*

class MainAdapter : RecyclerView.Adapter<ViewHolder>() {

    private val topicTitles = listOf("Math", "Physics", "Marvel Superheroes")

    override fun getItemCount(): Int {
        return topicTitles.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val topic = layoutInflater.inflate(R.layout.topic_layout, parent, false)
        return ViewHolder(topic)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val topicTitle = topicTitles[position]
        holder.view.textView_topic.text = topicTitle
    }
}

class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    init {
        view.setOnClickListener {
            val intent = Intent(view.context, TopicOverviewActivity::class.java)
            view.context.startActivity(intent)
        }
    }
}