package com.example.fitnessapp.ui.view

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.fitnessapp.databinding.ActivityPerfilBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*


class Perfil : AppCompatActivity() {
    private lateinit var binding: ActivityPerfilBinding
    private val db = FirebaseFirestore.getInstance()
    private lateinit var database: DatabaseReference
    @RequiresApi(Build.VERSION_CODES.N)
    val numero=Random().ints()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPerfilBinding.inflate(layoutInflater)
        setContentView(binding.root)
//instanciamos el menu de navegacion
        val bottomNav = binding.toolbar
        //le damos soporte con la funcion creada
        bottomNav.setOnNavigationItemSelectedListener(menuseleccion)
        idejercicio = numero.toString()

        db.collection("Users").document(email.toString()).get().addOnSuccessListener {
            binding.nombreperfil.setText("Hola de nuevo "+it.get("nombre") as String+" !" )
        }






        if (true) {

            db.collection("Users").document(email.toString()).collection("DatosPersonales").whereEqualTo("correo",
                email).get().addOnSuccessListener { documents ->
                for (document in documents) {
                    Log.i(TAG_LOGS, "${document.id} => ${document.data}")

                    binding.imcperfil.setText(document.get("imc") as String )
                    binding.pesoperfil.setText(document.get("peso")as String)
                    binding.alturaperfil.setText(document.get("altura")as String)
                    binding.correoperfil.setText(email)
                    binding.apellidosperfil.setText(document.get("apellidos")as String)


                }

            }.addOnFailureListener {
                Toast.makeText(
                    this,
                    "No hay datos que cargar",
                    Toast.LENGTH_LONG
                ).show()
            }



            binding.guardar.setOnClickListener {

                db.collection("Users").document(email.toString()).collection("DatosPersonales")
                    .document(
                      email.toString()
                    ).set(
                    hashMapOf(
                        "correo" to email.toString(),
                        "apellidos" to binding.apellidosperfil.text.toString(),
                        "altura" to binding.alturaperfil.text.toString(),
                        "peso" to binding.pesoperfil.text.toString(),
                        "imc" to binding.imcperfil.text.toString()
                    )
                ).addOnSuccessListener {
                    Toast.makeText(
                        this,
                        "Los datos se han guardado correctamente",
                        Toast.LENGTH_LONG
                    ).show()
                }

            }
        }


        binding.titulocalcularaperfil.setOnClickListener {

            val numCadena = binding.pesoperfil.text.toString()
            val numEntero = numCadena.toDouble()
            val numcadenados=binding.alturaperfil.text.toString()
            val numeroentero2=numcadenados.toDouble()
            val resultado=numEntero/(numeroentero2*numeroentero2)






                binding.imcperfil.setText(resultado.toString())


        }







    }

//con esta funcion controlamos la interacion con el menu y las distintas pantallas con sus funciones
    private val menuseleccion =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->

            when (item.itemId) {
                com.example.fitnessapp.R.id.opcion1 -> {
                    val intent=Intent(this,Perfil::class.java)
                    startActivity(intent)
                }
                com.example.fitnessapp.R.id.opcion2 -> {
                    val intent=Intent(this,Home::class.java)
                    startActivity(intent)
                }
                com.example.fitnessapp.R.id.opcion3 ->{
                    val intent=Intent(this,Ajustes::class.java)
                    startActivity(intent)

                }
            }

            true
        }

}