package com.example.fitnessapp.ui.view

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import com.example.fitnessapp.R
import com.example.fitnessapp.databinding.ActivityPerfilBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.firestore.FirebaseFirestore
import java.net.URI
import java.net.URL
import java.util.*
import java.util.jar.Manifest


class Perfil : AppCompatActivity() {
    private lateinit var binding: ActivityPerfilBinding
    private val db = FirebaseFirestore.getInstance()
    private lateinit var database: DatabaseReference
    @RequiresApi(Build.VERSION_CODES.N)
    val numero=Random().ints()

     private val  SELECT_ACTIVITY = 50
    private  var imageUri: Uri? = null

    var uri:String="https://www.who.int/es/news-room/fact-sheets/detail/obesity-and-overweight"
    lateinit var link:Uri



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


        binding.imagenperfil.setOnClickListener{
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            startActivityForResult(intent,SELECT_ACTIVITY)
        }



        binding.titulocalcularaperfil.setOnClickListener {

            val numCadena = binding.pesoperfil.text.toString()
            val numEntero = numCadena.toDouble()
            val numcadenados=binding.alturaperfil.text.toString()
            val numeroentero2=numcadenados.toDouble()
            val resultado=numEntero/(numeroentero2*numeroentero2)






                binding.imcperfil.setText(resultado.toString())


        }


        binding.info.setOnClickListener {

            val alerta = AlertDialog.Builder(this)
            alerta.setMessage("El IMC (índice de masa corporal) se emplea para determinar si una persona padece sobrepeso, pero no tiene en cuenta el porcentaje de grasa corporal, por lo que no es fiable para diagnosticar la obesidad." +
                    "¿Quieres informarte mas?")
                .setTitle("Alerta")
                .setCancelable(false)//esto es para que clique fuera del popup de alerta
                .setPositiveButton(
                    "Cerrar",
                    DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })
                .setNegativeButton(
                    "Aceptar",
                    DialogInterface.OnClickListener { dia, which -> link=uri.toUri()
                        val intent=Intent(Intent.ACTION_VIEW,link)
                        startActivity(intent)

                    })
                .create()
                .show()
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


    override fun onActivityResult(requestCode : Int, resultCode: Int, data: Intent?){
        super.onActivityResult(requestCode, resultCode, data)

        when{
            requestCode == SELECT_ACTIVITY && resultCode == Activity.RESULT_OK -> {
                imageUri = data!!.data
                binding.imagenperfil.setImageURI(imageUri)
            }
        }
    }

}