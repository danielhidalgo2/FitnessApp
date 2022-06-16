package com.example.fitnessapp.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitnessapp.data.models.Objetivosutricion
import com.example.fitnessapp.databinding.ActivityEjerciciosBinding
import com.example.fitnessapp.ui.view.holders.CircuitosHolder
import com.google.android.material.bottomnavigation.BottomNavigationView

class Ejercicios : AppCompatActivity() {
    lateinit var adapter: CircuitosHolder
    private lateinit var  binding: ActivityEjerciciosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEjerciciosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //instanciamos el menu de navegacion
       val bottomNav = binding.toolbar
        //le damos soporte con la funcion creada
       bottomNav.setOnNavigationItemSelectedListener(menuseleccion)


        val ejercicios= mutableListOf<Objetivosutricion>(
            Objetivosutricion("https://i.blogs.es/6c76f5/istock-1172740018/1366_2000.jpeg","Circuito FullBody en casa"),
        Objetivosutricion("https://content21.sabervivirtv.com/medio/2022/02/14/ejercicio-cardio-perder-peso_fb3b20b9_1024x576.jpg","Andar Libre"),
            Objetivosutricion(
                "https://static.abc.es/media/MM/2021/11/17/crunch-lateral-kLzC--1248x900@abc.jpg","Circuito de Torso"),
            Objetivosutricion("https://pybonacci.es/wp-content/uploads/2021/07/ejercicios-piernas-en-casa.jpg","Circuito de Piernas")
        )

        adapter= CircuitosHolder(this,ejercicios)
        val lista = binding.recyclerview6
        lista.adapter = adapter
        lista.layoutManager = LinearLayoutManager(this)
    }


    private val menuseleccion =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->

            when (item.itemId) {
                com.example.fitnessapp.R.id.opcion1 -> {
                    val intent= Intent(this,Perfil::class.java)
                    startActivity(intent)
                    finish()



                }
                com.example.fitnessapp.R.id.opcion2 -> {

                    val intent= Intent(this,Home::class.java)
                    startActivity(intent)
                    finish()

                }
                com.example.fitnessapp.R.id.opcion3 ->{
                    val intent= Intent(this,Ajustes::class.java)
                    startActivity(intent)
                    finish()


                }
            }


            true
        }

}