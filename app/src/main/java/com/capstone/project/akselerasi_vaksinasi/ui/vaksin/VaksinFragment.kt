package com.capstone.project.akselerasi_vaksinasi.ui.vaksin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.capstone.project.akselerasi_vaksinasi.R

class VaksinFragment : Fragment() {

    private lateinit var vaksinViewModel: VaksinViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vaksinViewModel =
            ViewModelProvider(this).get(VaksinViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_vaksin, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        vaksinViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}