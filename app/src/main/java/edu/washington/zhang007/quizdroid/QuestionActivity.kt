package edu.washington.zhang007.quizdroid

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.RadioButton
import android.widget.RadioGroup
import kotlinx.android.synthetic.main.activity_question.*

class QuestionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        val navBarTitle = intent.getStringExtra("TOPIC")
        val questions = intent.getStringArrayExtra("QUESTIONS")
        val answerOptions = intent.getStringArrayExtra("ANSWER_OPTIONS")
        val correctAnswers = intent.getStringArrayExtra("CORRECT_ANSWERS")

        var selectedChoice = ""
        supportActionBar?.title = navBarTitle

        textView_question.text = questions[0]

        radio_answer_1.text = answerOptions[0]
        radio_answer_2.text = answerOptions[1]
        radio_answer_3.text = answerOptions[2]
        radio_answer_4.text = answerOptions[3]

        radioGroup_answerGroup.setOnCheckedChangeListener {_, checkedID  ->
            button_submitAnswer.isEnabled = true

            selectedChoice = findViewById<RadioButton>(checkedID).text.toString()
            //println(selectedChoice)
        }

        button_submitAnswer.setOnClickListener {
            val intent = Intent(this, AnswerActivity::class.java)

            startActivity(intent)
        }
    }
}