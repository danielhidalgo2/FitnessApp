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
import android.widget.EditText
import android.widget.Toast

import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.annotation.NonNull
import androidx.core.text.set
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.firestore.FirebaseFirestore


class Perfil : AppCompatActivity() {
    private lateinit var binding: ActivityPerfilBinding
    private val db = FirebaseFirestore.getInstance()
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPerfilBinding.inflate(layoutInflater)
        setContentView(binding.root)
//instanciamos el menu de navegacion
        val bottomNav = binding.toolbar
        //le damos soporte con la funcion creada
        bottomNav.setOnNavigationItemSelectedListener(menuseleccion)


       db.collection("Users").document(email.toString()).get().addOnSuccessListener {

           binding.nombreperfil.text=it.get("nombre")as String
           binding.apellidosperfil.setText(it.get("apellido")as String)
           binding.correoperfil.setText(email)
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