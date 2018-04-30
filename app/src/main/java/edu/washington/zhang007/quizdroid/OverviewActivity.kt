package edu.washington.zhang007.quizdroid

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_overview.*

class OverviewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_overview)

        val navBarTitle = intent.getStringExtra(ViewHolder.TOPIC_NAME)
        val overview = intent.getStringExtra(ViewHolder.TOPIC_OVERVIEW)
        val questions = intent.getStringArrayExtra(ViewHolder.TOPIC_QUESTIONS)
        val answerOptions = intent.getStringArrayExtra(ViewHolder.ANSWER_OPTIONS)
        val correctAnswers = intent.getStringArrayExtra(ViewHolder.CORRECT_ANSWERS)

        supportActionBar?.title = navBarTitle
        textView_overview.text = overview
        textView_numberQuestions.text = questions.size.toString() + " Question(s)"

        button_beginQuiz.setOnClickListener {
            val intent = Intent(this, QuestionActivity::class.java)

            intent.putExtra("TOPIC", navBarTitle)
            intent.putExtra("QUESTIONS", questions)
            intent.putExtra("ANSWER_OPTIONS", answerOptions)
            intent.putExtra("CORRECT_ANSWERS", correctAnswers)

            startActivity(intent)
        }
    }
}