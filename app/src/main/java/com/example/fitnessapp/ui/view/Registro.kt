package com.example.fitnessapp.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.fitnessapp.R
import com.example.fitnessapp.databinding.ActivityPerfilBinding
import com.example.fitnessapp.databinding.ActivityRegistroBinding

class Registro : AppCompatActivity() {

    private lateinit var binding: ActivityRegistroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val boton :Button=findViewById(R.id.registrar)

        boton.setOnClickListener {
            val intent =Intent(this,Perfil::class.java)
            startActivity(intent)
        }





    }
}