package com.example.fitnessapp.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toolbar

import com.example.fitnessapp.databinding.ActivityMainBinding
import com.example.fitnessapp.databinding.ActivityPerfilBinding
import android.R

import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.annotation.NonNull


class Perfil : AppCompatActivity() {
    private lateinit var binding: ActivityPerfilBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPerfilBinding.inflate(layoutInflater)
        setContentView(binding.root)
//instanciamos el menu de navegacion
        val bottomNav = binding.toolbar
        //le damos soporte con la funcion creada
        bottomNav.setOnNavigationItemSelectedListener(menuseleccion)

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