package com.example.fitnessapp.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.fitnessapp.R

class Registro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)


        val boton :Button=findViewById(R.id.registrar)

        boton.setOnClickListener {
            val intent =Intent(this,Perfil::class.java)
            startActivity(intent)
        }

    }
}