package com.capstone.project.akselerasi_vaksinasi.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.capstone.project.akselerasi_vaksinasi.R
import com.capstone.project.akselerasi_vaksinasi.model.Patient
import com.capstone.project.akselerasi_vaksinasi.ui.detail.DetailPatientActivity
import com.capstone.project.akselerasi_vaksinasi.ui.home.adapter.HomeAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.firebase.ui.database.FirebaseRecyclerOptions.Builder
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var auth: FirebaseAuth

    private lateinit var homeAdapter: HomeAdapter

    private lateinit var database: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.tvWelcome)
        val recyclerViewMain: RecyclerView = root.findViewById(R.id.rvMain)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        //--- hanya untuk pindah activity ke login, hapus jika sudah ada button Logout di Setting screen atau profil screen
        textView.setOnClickListener {
            auth = Firebase.auth
            auth.signOut()
            Log.d("user", "user sign out!")
        }
        // --- sampai sini

        database = FirebaseDatabase.getInstance().getReference("patients")
        val options: FirebaseRecyclerOptions<Patient> = Builder<Patient>()
            .setQuery(database, Patient::class.java)
            .build()
        homeAdapter = HomeAdapter(options, ::onItemClick)
        recyclerViewMain.apply {
            layoutManager = LinearLayoutManager(root.context)
            adapter = homeAdapter
        }

        return root
    }

    private fun onItemClick(patient: Patient) {
        DetailPatientActivity.start(requireContext(), patient)
    }

    override fun onStart() {
        super.onStart()
        homeAdapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        homeAdapter.stopListening()
    }
}