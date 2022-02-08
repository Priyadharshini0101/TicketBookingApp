package com.mycode.ticketbookingapp.profilefragment.settingsfragment

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceFragmentCompat
import com.mycode.ticketbookingapp.R

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#F3FFDE07")))
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.settings, SettingsFragment())
                .commit()
        }
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        /* findViewById.<TextView>.tvfeedback.setOnClickListener(View.OnClickListener {
             val intent= Intent(this,feedback::class.java)
             intent.flags= Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
             startActivity(intent)
         })*/
    }

    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)


        }
    }
}