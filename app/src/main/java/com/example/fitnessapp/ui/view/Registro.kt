package com.example.fitnessapp.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.fitnessapp.R
import com.example.fitnessapp.databinding.ActivityPerfilBinding
import com.example.fitnessapp.databinding.ActivityRegistroBinding
import com.google.firebase.auth.FirebaseAuth


enum class ProviderType{
    BASIC
}
class Registro : AppCompatActivity() {

    private lateinit var binding: ActivityRegistroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)




        val boton :Button=findViewById(R.id.registrar)

       /* boton.setOnClickListener {
            val intent =Intent(this,Perfil::class.java)
            startActivity(intent)

        }*/

        binding.registrar.setOnClickListener {
            if(binding.contraseARegistro.text.toString().isNotEmpty() &&  binding.correoRegistro.text.toString().isNotEmpty()){
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(binding.correoRegistro.text.toString(),binding.contraseARegistro.text.toString()).addOnCompleteListener {
                    if(it.isSuccessful){
                       showHome(it.result?.user?.email ?:"",ProviderType.BASIC)

                    }else{
                        Toast.makeText(this,"error",Toast.LENGTH_LONG).show()
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