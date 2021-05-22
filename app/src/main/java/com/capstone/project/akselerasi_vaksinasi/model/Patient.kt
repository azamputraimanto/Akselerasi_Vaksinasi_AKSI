package com.capstone.project.akselerasi_vaksinasi.model

data class Patient(
    val regId: String,
    val vax_manu: String,
    val age_yrs : Int,
    val sex: String,
    val symptom0: String
)
