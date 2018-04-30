package edu.washington.zhang007.quizdroid

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_answer.*

class AnswerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answer)

        val navBarTitle = intent.getStringExtra("TOPIC")
        val questions = intent.getStringArrayExtra("QUESTIONS")
        val answerOptions = intent.getStringArrayExtra("ANSWER_OPTIONS")
        val correctAnswers = intent.getStringArrayExtra("CORRECT_ANSWERS")
        val numberCorrect = intent.getIntExtra("NUMBER_CORRECT", 0)
        val i = intent.getIntExtra("INDEX", -1)
        val selectedAnswer = intent.getStringExtra("SELECTED_ANSWER")

        supportActionBar?.title = navBarTitle

        textView_playerAnswer.text = "Your answer was: $selectedAnswer"
        textView_correctAnswer.text = "The correct answer was: ${correctAnswers[i-1]}" // correct answer for previous question
        textView_numberCorrect.text = "$numberCorrect out of ${questions.size} correct"

        if (i >= questions.size) { // index will always be equal to or less
            button_nextOrFinish.text = "Finish"

            button_nextOrFinish.setOnClickListener {
                val intent = Intent(this, MainActivity::class.java)

                startActivity(intent)
            }
        } else {
            button_nextOrFinish.text = "Next"

            button_nextOrFinish.setOnClickListener {
                val intent = Intent(this, QuestionActivity::class.java)

                intent.putExtra("TOPIC", navBarTitle)
                intent.putExtra("QUESTIONS", questions)
                intent.putExtra("ANSWER_OPTIONS", answerOptions)
                intent.putExtra("CORRECT_ANSWERS", correctAnswers)
                intent.putExtra("NUMBER_CORRECT", numberCorrect)
                intent.putExtra("INDEX", i)

                startActivity(intent)
            }
        }
    }
}