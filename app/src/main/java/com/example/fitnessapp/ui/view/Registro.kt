package com.example.fitnessapp.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import com.example.fitnessapp.R
import com.example.fitnessapp.databinding.ActivityPerfilBinding
import com.example.fitnessapp.databinding.ActivityRegistroBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase


enum class ProviderType{
    BASIC
}
var email: String? = null

  lateinit var correoajustes:String
 open class Registro : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()

    private lateinit var binding: ActivityRegistroBinding


     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

         var nombreRegistro: EditText= findViewById(R.id.nombreRegistro)
         var apellidosRegistro: EditText= findViewById(R.id.apellidosRegistro)
         var correoRegistro: EditText= findViewById(R.id.correoRegistro)
         var contraseniaRegistro: EditText= findViewById(R.id.contraseñaRegistro)
         var contraseniaConfirmada: EditText= findViewById(R.id.contraseñaconfirmadaRegistro)
         val boton :Button=findViewById(R.id.registrar)



       /* boton.setOnClickListener {
            val intent =Intent(this,Perfil::class.java)
            startActivity(intent)

        }*/

        binding.registrar.setOnClickListener {
            if(binding.contraseARegistro.text.toString().isNotEmpty() &&  binding.correoRegistro.text.toString().isNotEmpty()){
                db.collection("Users").document(correoRegistro.text.toString()).set(
                    hashMapOf("nombre" to nombreRegistro.text.toString(), "apellido" to apellidosRegistro.text.toString(), "contrasenia" to contraseniaRegistro.text.toString(), "confirmacion" to contraseniaConfirmada.text.toString())
                )
                email=binding.correoRegistro.text.toString()
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