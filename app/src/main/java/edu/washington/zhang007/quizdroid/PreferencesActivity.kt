package edu.washington.zhang007.quizdroid

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_preferences.*

class PreferencesActivity: AppCompatActivity() {

    // TO-DO Add URL retrieval functionality

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preferences)

        val updateFreq = spinner_updateFreq

        val updateAdapter: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(this,
                R.array.update_frequency, android.R.layout.simple_spinner_item)
        updateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        updateFreq?.adapter = updateAdapter
    }
}