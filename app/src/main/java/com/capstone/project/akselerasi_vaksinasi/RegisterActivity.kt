package com.capstone.project.akselerasi_vaksinasi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.widget.Toast
import com.capstone.project.akselerasi_vaksinasi.databinding.ActivityRegisterBinding
import com.capstone.project.akselerasi_vaksinasi.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityRegisterBinding
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth
    private var visiblePass = false
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        register()


        binding.eyeButton.setOnClickListener {
            if(visiblePass == false){
                visiblePass = true
                binding.edtPass.transformationMethod = null
            } else {
                visiblePass = false
                binding.edtPass.transformationMethod = PasswordTransformationMethod()
            }
        }

        binding.txtMasuk.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun register(){
        binding.buttonRegister.setOnClickListener {
            val name = binding.edtName.text.toString()
            val password = binding.edtPass.text.toString()
            val email = binding.edtEmail.text.toString()


            if(binding.edtName.text.toString().isEmpty()){
                binding.edtName.setError("Field ini harus diisi!!")
            } else if(binding.edtEmail.text.toString().isEmpty()){
                binding.edtEmail.setError("Field ini harus diisi!!")
            } else if(binding.edtPass.text.toString().isEmpty()){
                binding.edtPass.setError("Field ini harus diisi!!")
            } else if(binding.edtPass.text.toString().length < 8){
                binding.edtPass.setError("Password minimal harus terdiri dari 8 karakter!!")
            } else {
                auth = Firebase.auth
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {

                            database = Firebase.database("https://b21-cap0247.asia-southeast1.firebasedatabase.app/").reference
                            Log.d("db ref", database.toString())
                            val user = User(auth.uid.toString(), name, email)
                            database.child("users").child(auth.uid.toString()).setValue(user)
                                .addOnSuccessListener {
                                    Log.d("response register", "successfull")

                                    val intent = Intent(this, MainActivity::class.java)
                                    startActivity(intent)
                                    finish()
                                }
                                .addOnFailureListener {
                                    Log.d("response register", "error ${it.message}")
                                }
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("sign in", "createUserWithEmail:failure", task.exception)
                            Toast.makeText(baseContext, "Authentication failed, try again!!",
                                Toast.LENGTH_SHORT).show()

                        }
                    }
            }
        }

    }
}