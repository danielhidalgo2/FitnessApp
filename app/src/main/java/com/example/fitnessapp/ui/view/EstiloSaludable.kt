package com.example.fitnessapp.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitnessapp.R
import com.example.fitnessapp.databinding.ActivityAjustesBinding
import com.example.fitnessapp.databinding.ActivityEstiloSaludableBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class EstiloSaludable : AppCompatActivity() {
    lateinit var adapter: PerdidaPesoHolder
    private lateinit var binding: ActivityEstiloSaludableBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_estilo_saludable)
        binding = ActivityEstiloSaludableBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //instanciamos el menu de navegacion
         val bottomNav = binding.toolbar
        //le damos soporte con la funcion creada
         bottomNav.setOnNavigationItemSelectedListener(menuseleccion)


        adapter= PerdidaPesoHolder(this, favoritos)
        val lista = binding.recyclerview66
        lista.adapter = adapter
        lista.layoutManager = LinearLayoutManager(this)

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