package edu.washington.zhang007.quizdroid

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import kotlinx.android.synthetic.main.fragment_question.*

class QuestionFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_question, container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val data = arguments as Bundle

        val questions = data.getStringArray("QUESTIONS")
        val answerOptions = data.getStringArray("ANSWER_OPTIONS")
        val correctAnswers = data.getStringArray("CORRECT_ANSWERS")
        var numberCorrect = data.getInt("NUMBER_CORRECT")
        var i = data.getInt("INDEX")

        var selectedAnswer = ""

        textView_question.text = questions[i]

        radio_answer_1.text = answerOptions[0]
        radio_answer_2.text = answerOptions[1]
        radio_answer_3.text = answerOptions[2]
        radio_answer_4.text = answerOptions[3]

        radioGroup_answerGroup.setOnCheckedChangeListener {_, checkedID  ->
            button_submitAnswer.isEnabled = true

            selectedAnswer = activity!!.findViewById<RadioButton>(checkedID).text.toString()
        }

        button_submitAnswer.setOnClickListener {
            if (selectedAnswer == correctAnswers[i]) {
                numberCorrect += 1
            }

            i += 1

            val data = Bundle()
            data.putStringArray("QUESTIONS", questions)
            data.putStringArray("ANSWER_OPTIONS", answerOptions)
            data.putStringArray("CORRECT_ANSWERS", correctAnswers)
            data.putInt("NUMBER_CORRECT", numberCorrect)
            data.putInt("INDEX", i)
            data.putString("SELECTED_ANSWER", selectedAnswer)

            val answerFragment = AnswerFragment()
            answerFragment.arguments = data

            loadFragment(answerFragment)
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