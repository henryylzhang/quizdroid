package edu.washington.zhang007.quizdroid

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity

class ControllerActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_controller)

        val overviewFragment = OverviewFragment()

        overviewFragment.arguments = loadDataFromIntent()

        loadFragment(overviewFragment)
    }

    fun loadFragment(f: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left)
        transaction.replace(R.id.fragment_holder, f)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun loadDataFromIntent(): Bundle {
        val overview = intent.getStringExtra(ViewHolder.TOPIC_OVERVIEW)
        val questions = intent.getSerializableExtra(ViewHolder.TOPIC_QUESTIONS)

        val data = Bundle()
        data.putString("OVERVIEW", overview)
        data.putSerializable("QUESTIONS", questions)

        return data
    }
}