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

        // There is a problem at lower API levels (somewhere below 24) that this cast will not work.
        val questions = data.getSerializable("QUESTIONS") as Array<Question>
        val numberCorrect = data.getInt("NUMBER_CORRECT")
        var i = data.getInt("INDEX")
        val selectedAnswer = data.getString("SELECTED_ANSWER")
        
        val lastQuestionCorrectIndex = questions[i].correctIndex
        val correctAnswer = questions.get(i).answerOptions[lastQuestionCorrectIndex]

        textView_playerAnswer.text = "Your answer was: " + selectedAnswer
        textView_correctAnswer.text = "The correct answer is: " + correctAnswer
        textView_numberCorrect.text = "$numberCorrect out of ${questions.size} correct"

        i += 1 // incremented here to avoid having to -1 to get previous correctAnswer/lastQCorrectIndex

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
                data.putSerializable("QUESTIONS", questions)
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