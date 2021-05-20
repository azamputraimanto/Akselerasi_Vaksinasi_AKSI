package com.capstone.project.akselerasi_vaksinasi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.widget.Toast
import com.capstone.project.akselerasi_vaksinasi.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityLoginBinding
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth
    private var visiblePass = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.eyeButton.setOnClickListener {
            if(visiblePass == false){
                visiblePass = true
                binding.textPassword.transformationMethod = null
            } else {
                visiblePass = false
                binding.textPassword.transformationMethod = PasswordTransformationMethod()
            }
        }

        binding.txtDaftar.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }

        login()
    }

    private fun login(){
        binding.btnLogin.setOnClickListener{
            val email = binding.textEmail.text.toString()
            val password = binding.textPassword.text.toString()

            if(binding.textEmail.text.toString().isEmpty()){
                binding.textEmail.setError("Field ini harus diisi!!")
            } else if(binding.textPassword.text.toString().isEmpty()){
                binding.textPassword.setError("Field ini harus diisi!!")
            } else {
                auth = Firebase.auth
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("sign in", "signInWithEmail:failure", task.exception)
                            Toast.makeText(baseContext, "Authentication failed. Try again!",
                                Toast.LENGTH_SHORT).show()

                        }
                    }
            }


        }
    }
}