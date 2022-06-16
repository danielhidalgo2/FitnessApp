package com.example.fitnessapp.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitnessapp.data.models.Objetivosutricion
import com.example.fitnessapp.databinding.ActivityNutricionBinding
import com.example.fitnessapp.ui.view.holders.NutricionHolder

import com.google.android.material.bottomnavigation.BottomNavigationView

class Nutricion : AppCompatActivity() {
    lateinit var adapter: NutricionHolder

    private lateinit var binding: ActivityNutricionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityNutricionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //instanciamos el menu de navegacion
        val bottomNav = binding.toolbar
        //le damos soporte con la funcion creada
        bottomNav.setOnNavigationItemSelectedListener(menuseleccion)


        var objetivos_nutricion= mutableListOf<Objetivosutricion>(
            Objetivosutricion("https://www.comedera.com/wp-content/uploads/2018/01/recetas-para-adelgazar.jpg","Perdida de peso")
        ,   Objetivosutricion("https://guiafitness.com/wp-content/uploads/cantidad-batidos-proteinas.jpg","Ganancia Muscular"),
            Objetivosutricion("https://archzine.es/wp-content/uploads/2020/04/alimentos-para-comer-cuando-entrenamos-ideas-de-comidas-saludables-y-faciles-de-preparar-en-casa-salmon-aguacate-espinacas-1.jpg","Altas en Proteinas")
        ,   Objetivosutricion("https://www.cocinacaserayfacil.net/wp-content/uploads/2020/03/Mantener-un-estilo-de-vida-saludable-en-casa_4.jpg","Favoritos")
        )
        adapter= NutricionHolder(this,objetivos_nutricion)
        val lista = binding.recyclerview2
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