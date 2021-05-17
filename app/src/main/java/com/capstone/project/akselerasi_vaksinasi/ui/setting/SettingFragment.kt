package com.capstone.project.akselerasi_vaksinasi.ui.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceFragmentCompat
import com.capstone.project.akselerasi_vaksinasi.R

class SettingFragment : PreferenceFragmentCompat() {

    private lateinit var settingViewModel: SettingViewModel

//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        settingViewModel =
//            ViewModelProvider(this).get(SettingViewModel::class.java)
//        val root = inflater.inflate(R.layout.fragment_setting, container, false)
//        val textView: TextView = root.findViewById(R.id.text_notifications)
//        settingViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
//        return root
//    }

    override fun onCreatePreferences(bundle: Bundle?, s: String?) {
        addPreferencesFromResource(R.xml.setting_menu)
    }
}