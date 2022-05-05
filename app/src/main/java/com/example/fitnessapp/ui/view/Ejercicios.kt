package com.example.fitnessapp.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fitnessapp.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class Ejercicios : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicios)
        //instanciamos el menu de navegacion
       // val bottomNav = binding.toolbar
        //le damos soporte con la funcion creada
       // bottomNav.setOnNavigationItemSelectedListener(menuseleccion)
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
                }
                com.example.fitnessapp.R.id.opcion3 ->{
                    val intent= Intent(this,Ajustes::class.java)
                    startActivity(intent)

                }
            }

            true
        }
}