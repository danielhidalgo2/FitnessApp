package com.example.fitnessapp.ui.view

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.widget.Toast
import androidx.activity.viewModels
import com.example.fitnessapp.R
import com.example.fitnessapp.databinding.ActivityMainBinding
import com.example.fitnessapp.databinding.ActivityRegistroBinding
import com.example.fitnessapp.ui.viewmodels.MainActivityViewModel
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // se encargara de conectar la activity con nuestro Viewmodel
    private  val mainViewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        setTheme(R.style.Theme_FitnessApp)
        binding.iniciar.setBackgroundColor(Color.GRAY)
        val gg:TextView= findViewById(R.id.pregunta)

        val mitextoU = SpannableString("¿No tienes cuenta en FitnessLife aùn? Crear cuenta")
        mitextoU.setSpan(UnderlineSpan(), 0, mitextoU.length, 0)
        gg.setText(mitextoU)


        gg.setOnClickListener {
            val clase=Intent(this, Registro::class.java)
            startActivity(clase)
        }

        binding.iniciar.setOnClickListener {
            if(binding.usuario.text.toString().isNotEmpty() &&  binding.password.text.toString().isNotEmpty()){
                mainViewModel.onCreate(this,binding.usuario.text.toString(),binding.password.text.toString())
                val intent=Intent(this,Home::class.java)
                startActivity(intent)
                finish()
            }else{
                Toast.makeText(this,"Error, Los campos introducidos son incorrectos o invalidos.", Toast.LENGTH_LONG).show()

            }
        }

    }
    private fun showHome(email:String,provider:ProviderType){
        val intent=Intent(this,Home::class.java).apply {
            putExtra("email",email)
            putExtra("proveedor",provider.name)
        }
    }
}