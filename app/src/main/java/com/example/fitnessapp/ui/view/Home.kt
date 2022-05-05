package com.example.fitnessapp.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitnessapp.R
import com.example.fitnessapp.data.DatosEjercicios
import com.example.fitnessapp.databinding.ActivityHomeBinding


import com.google.android.material.bottomnavigation.BottomNavigationView

lateinit var adapter: EjerciciosHolder
val TAG_LOGS = "kikopalomares"

class Home : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

//instanciamos el menu de navegacion
        val bottomNav = binding.toolbar
        //le damos soporte con la funcion creada
        bottomNav.setOnNavigationItemSelectedListener(menuseleccion)

        val datos= mutableListOf<DatosEjercicios>(DatosEjercicios("https://static1.abc.es/media/bienestar/2020/10/14/entrenamiento-funcional-kZcD--620x349@abc.jpeg",1),
            DatosEjercicios("https://concepto.de/wp-content/uploads/2015/03/nutricion-1-e1550713442289.jpg",2),
            DatosEjercicios("https://todobulletjournal.com/wp-content/uploads/registro-de-ejercicio-6.jpg",3)
        )




        adapter = EjerciciosHolder(this, datos)

        val lista = binding.recyclerview
        lista.adapter = adapter
        lista.layoutManager = LinearLayoutManager(this)



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