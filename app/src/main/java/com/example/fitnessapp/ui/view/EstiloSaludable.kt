package com.example.fitnessapp.ui.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitnessapp.R
import com.example.fitnessapp.data.EjercicioRegistradoTrack
import com.example.fitnessapp.data.Perdidapeso
import com.example.fitnessapp.databinding.ActivityAjustesBinding
import com.example.fitnessapp.databinding.ActivityEstiloSaludableBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.firestore.FirebaseFirestore

class EstiloSaludable : AppCompatActivity() {
    lateinit var adapter: PerdidaPesoHolder
    private lateinit var binding: ActivityEstiloSaludableBinding

    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_estilo_saludable)
        binding = ActivityEstiloSaludableBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //instanciamos el menu de navegacion
         val bottomNav = binding.toolbar
        //le damos soporte con la funcion creada
         bottomNav.setOnNavigationItemSelectedListener(menuseleccion)




        var favoritosreal= mutableListOf<Perdidapeso>()

        db.collection("Users").document(email.toString()).collection("RecetasFavoritos").get().addOnSuccessListener {
                documents ->
            for (document in documents) {
                favoritosreal.addAll(listOf(Perdidapeso(document.get("titulo")as String,document.get("foto")as String, document.get("informacion")as String,document.get("calorias") as String, document.get("tiempo") as String)))

            }

            adapter= PerdidaPesoHolder(this, favoritosreal.distinct() as MutableList<Perdidapeso>)
            val lista = binding.recyclerview66
            lista.adapter = adapter
            lista.layoutManager = LinearLayoutManager(this)
        }


    }
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