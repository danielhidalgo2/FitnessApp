package com.example.fitnessapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.text.SpannableString
import android.text.style.UnderlineSpan







class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val gg:TextView= findViewById(R.id.pregunta)

        val mitextoU = SpannableString("¿No tienes cuenta en FitnessLife aùn? Crear cuenta")
        mitextoU.setSpan(UnderlineSpan(), 0, mitextoU.length, 0)
        gg.setText(mitextoU)



        gg.setOnClickListener {
            val clase=Intent(this,Registro::class.java)
            startActivity(clase)
        }

    }
}