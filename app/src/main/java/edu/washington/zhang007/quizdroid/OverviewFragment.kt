package edu.washington.zhang007.quizdroid

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_overview.*

class OverviewFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_overview, container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val data = arguments as Bundle

        val overview = data.getString("OVERVIEW")

        // There is a problem at lower API levels (somewhere below 24) that this cast will not work.
        val questions = data.getSerializable("QUESTIONS") as Array<Question>

        textView_overview.text = overview
        textView_numberQuestions.text = questions.size.toString() + " Question(s)"

        button_beginQuiz.setOnClickListener {
            val data = Bundle()
            data.putSerializable("QUESTIONS", questions)
            data.putInt("NUMBER_CORRECT", 0)
            data.putInt("INDEX", 0)

            val questionFragment = QuestionFragment()
            questionFragment.arguments = data

            loadFragment(questionFragment)
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