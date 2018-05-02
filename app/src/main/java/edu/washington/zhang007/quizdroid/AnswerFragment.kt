package edu.washington.zhang007.quizdroid

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_answer.*

class AnswerFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_answer, container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val data = arguments as Bundle

        val questions = data.getStringArray("QUESTIONS")
        val answerOptions = data.getStringArray("ANSWER_OPTIONS")
        val correctAnswers = data.getStringArray("CORRECT_ANSWERS")
        val numberCorrect = data.getInt("NUMBER_CORRECT")
        val i = data.getInt("INDEX")
        val selectedAnswer = data.getString("SELECTED_ANSWER")

        textView_playerAnswer.text = "Your answer was: " + selectedAnswer
        textView_correctAnswer.text = "The correct answer is: " + correctAnswers[i - 1]
        textView_numberCorrect.text = "$numberCorrect out of ${questions.size} correct"

        if (i >= questions.size) { // index will always be equal to or less
            button_nextOrFinish.text = "Finish"

            button_nextOrFinish.setOnClickListener {
                val intent = Intent(activity, MainActivity::class.java)

                startActivity(intent)
            }
        } else {
            button_nextOrFinish.text = "Next"
            button_nextOrFinish.setOnClickListener {
                val data = Bundle()
                data.putStringArray("QUESTIONS", questions)
                data.putStringArray("ANSWER_OPTIONS", answerOptions)
                data.putStringArray("CORRECT_ANSWERS", correctAnswers)
                data.putInt("NUMBER_CORRECT", numberCorrect)
                data.putInt("INDEX", i)

                val questionFragment = QuestionFragment()
                questionFragment.arguments = data

                loadFragment(questionFragment)
            }
        }
    }

    fun loadFragment(f: Fragment) {
        val transaction = fragmentManager?.beginTransaction()
        transaction?.setCustomAnimations(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left)
        transaction?.replace(R.id.fragment_holder, f)
        transaction?.addToBackStack(null)
        transaction?.commit()
    }
}