package com.example.fitnessapp.ui.viewmodels

import android.widget.TextView
import androidx.lifecycle.ViewModel
import com.example.fitnessapp.ui.view.email
import com.google.firebase.firestore.FirebaseFirestore

class PerfilviewModel: ViewModel() {

    private val db = FirebaseFirestore.getInstance()


    fun onCreate(){

    }


    fun leerDatos(  nombre: String,correo: String){
        db.collection("Users").document(email.toString()).get().addOnSuccessListener {
            it.get("nombre") as String
            nombre.toString ()

        }

    }
}