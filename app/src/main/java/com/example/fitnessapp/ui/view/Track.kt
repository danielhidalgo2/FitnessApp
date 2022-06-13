package com.example.fitnessapp.ui.view

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitnessapp.R
import com.example.fitnessapp.data.EjercicioRegistradoTrack
import com.example.fitnessapp.databinding.ActivityTrackBinding


import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObjects


class Track : AppCompatActivity() {
    val datos= mutableListOf<EjercicioRegistradoTrack>()
    private lateinit var database: DatabaseReference
    private lateinit var binding: ActivityTrackBinding
    private val db = FirebaseFirestore.getInstance()
    lateinit var adapter: TrackHolder
    lateinit var ejercicos:MutableList<EjercicioRegistradoTrack>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityTrackBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //instanciamos el menu de navegacion
        val bottomNav = binding.toolbar
        //le damos soporte con la funcion creada
        bottomNav.setOnNavigationItemSelectedListener(menuseleccion)




        db.collection("Users").document(email.toString()).collection("EjerciciosRegistro").get().addOnSuccessListener {
                documents ->
            for (document in documents) {
                Log.i(TAG_LOGS,document.get("Fecha")as String)
                Log.i(TAG_LOGS,document.get("Titulo")as String)
                Log.i(TAG_LOGS,document.get("Pasos")as String)
                datos.addAll(listOf(EjercicioRegistradoTrack(document.get("Fecha")as String,document.get("Titulo")as String, document.get("Pasos")as String)))


                adapter= TrackHolder(this,datos)
                val lista = binding.recyclerview24
                lista.adapter = adapter
                lista.layoutManager = LinearLayoutManager(this)
            }

        }.addOnFailureListener {
            val alerta = AlertDialog.Builder(this)

            alerta.setMessage("No hay ejercicios Registrados")
                .setTitle("Información")
                .setCancelable(false)//esto es para que clique fuera del popup de alerta
                .setNegativeButton(
                    "Cerrar",
                    DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })
                .create()
                .show()        }



    }

    //con esta funcion controlamos la interacion con el menu y las distintas pantallas con sus funciones
    private val menuseleccion =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->

            when (item.itemId) {
                com.example.fitnessapp.R.id.opcion1 -> {
                    val intent= Intent(this,Perfil::class.java)
                    startActivity(intent)

                }
                com.example.fitnessapp.R.id.opcion2 -> {
                    val intent= Intent(this,Home::class.java)
                    startActivity(intent)
                    item.isVisible=true
                }
                com.example.fitnessapp.R.id.opcion3 ->{
                    val intent= Intent(this,Ajustes::class.java)
                    startActivity(intent)
                    item.isVisible=true

                }
            }

            true
        }


}