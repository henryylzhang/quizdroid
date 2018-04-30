package edu.washington.zhang007.quizdroid

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.RadioButton
import kotlinx.android.synthetic.main.activity_question.*

class QuestionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        val navBarTitle = intent.getStringExtra("TOPIC")
        val questions = intent.getStringArrayExtra("QUESTIONS")
        val answerOptions = intent.getStringArrayExtra("ANSWER_OPTIONS")
        val correctAnswers = intent.getStringArrayExtra("CORRECT_ANSWERS")
        var numberCorrect = intent.getIntExtra("NUMBER_CORRECT", 0)
        var i = intent.getIntExtra("INDEX", 0)

        var selectedAnswer = ""

        supportActionBar?.title = navBarTitle

        textView_question.text = questions[i]

        radio_answer_1.text = answerOptions[0]
        radio_answer_2.text = answerOptions[1]
        radio_answer_3.text = answerOptions[2]
        radio_answer_4.text = answerOptions[3]

        radioGroup_answerGroup.setOnCheckedChangeListener {_, checkedID  ->
            button_submitAnswer.isEnabled = true

            selectedAnswer = findViewById<RadioButton>(checkedID).text.toString()
        }

        button_submitAnswer.setOnClickListener {
            val intent = Intent(this, AnswerActivity::class.java)

            if (selectedAnswer == correctAnswers[i]) {
                numberCorrect += 1
            }

            i += 1

            intent.putExtra("TOPIC", navBarTitle)
            intent.putExtra("QUESTIONS", questions)
            intent.putExtra("ANSWER_OPTIONS", answerOptions)
            intent.putExtra("CORRECT_ANSWERS", correctAnswers)
            intent.putExtra("NUMBER_CORRECT", numberCorrect)
            intent.putExtra("INDEX", i)
            intent.putExtra("SELECTED_ANSWER", selectedAnswer)

            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()

        val i = intent.getIntExtra("INDEX", 0)

        if (i == 0) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}