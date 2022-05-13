package com.example.fitnessapp.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.widget.Toast
import com.example.fitnessapp.R
import com.example.fitnessapp.databinding.ActivityMainBinding
import com.example.fitnessapp.databinding.ActivityRegistroBinding
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


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
                FirebaseAuth.getInstance().signInWithEmailAndPassword(binding.usuario.text.toString(),binding.password.text.toString()).addOnCompleteListener {
                    if(it.isSuccessful){
                        showHome(it.result?.user?.email ?:"",ProviderType.BASIC)
                        email=binding.usuario.text.toString()

                    }else{
                        Toast.makeText(this,"error", Toast.LENGTH_LONG).show()
                    }
                }
                //esta funcion es para que nos notifique si se ha realizado correctamentre el registro

            }
        }

    }
    private fun showHome(email:String,provider:ProviderType){
        val intent=Intent(this,Home::class.java).apply {
            putExtra("email",email)
            putExtra("proveedor",provider.name)
        }
        startActivity(intent)
    }
}