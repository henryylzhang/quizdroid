package edu.washington.zhang007.quizdroid

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.topic_overview.*

class TopicOverviewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.topic_overview)

        val navBarTitle = intent.getStringExtra(ViewHolder.TOPIC_NAME)
        val overview = intent.getStringExtra(ViewHolder.TOPIC_OVERVIEW)
        val questions = intent.getStringArrayExtra(ViewHolder.TOPIC_QUESTIONS)
        val answers = intent.getStringArrayExtra(ViewHolder.TOPIC_ANSWERS)

        supportActionBar?.title = navBarTitle
        textView_overview.text = overview
        textView_numberQuestions.text = questions.size.toString() + " Question(s)"
    }
}