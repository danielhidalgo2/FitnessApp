package com.example.fitnessapp.ui.viewmodels

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.fitnessapp.databinding.ActivityMainBinding
import com.example.fitnessapp.ui.view.Home
import com.example.fitnessapp.ui.view.MainActivity
import com.example.fitnessapp.ui.view.ProviderType
import com.example.fitnessapp.ui.view.email
import com.google.firebase.auth.FirebaseAuth


class MainActivityViewModel: ViewModel() {


    fun onCreate(context: Context, usuario :String,contra:String) {

        FirebaseAuth.getInstance().signInWithEmailAndPassword(usuario,contra).addOnCompleteListener {
            if(it.isSuccessful){
               // showHome(it.result?.user?.email ?:"", ProviderType.BASIC)
                email =usuario

            }else{
                Toast.makeText(context,"error", Toast.LENGTH_LONG).show()
            }
        }

    }


}