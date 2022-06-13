package com.example.fitnessapp.ui.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitnessapp.R
import com.example.fitnessapp.data.Perdidapeso
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




        var favoritosreal= mutableListOf<Perdidapeso>()

            // In this function we will retrieve data
            val sharedPreferences = getSharedPreferences("datosrecetas", Context.MODE_PRIVATE)
        val sharedPreferences1 = getSharedPreferences("datosrecetas1", Context.MODE_PRIVATE)

        val id=sharedPreferences.getInt("id",0)
        val id1=sharedPreferences1.getInt("id",1)






        if (id==0){
            val titulo = sharedPreferences.getString("titulo", "")
            val foto=sharedPreferences.getString("foto","")
            val informacion=sharedPreferences.getString("informacion","")
            val calorias=sharedPreferences.getString("calorias","")
            val tiempo=sharedPreferences.getString("tiempo","")
            favoritosreal.add(Perdidapeso("$titulo","$foto","$informacion","$calorias","$tiempo"))


            // Log.d is used for debugging purposes
        }

        if (id1==1){
            val titulo = sharedPreferences.getString("titulo", "")
            val foto=sharedPreferences.getString("foto","")
            val informacion=sharedPreferences.getString("informacion","")
            val calorias=sharedPreferences.getString("calorias","")
            val tiempo=sharedPreferences.getString("tiempo","")
            favoritosreal.add(Perdidapeso("$titulo","$foto","$informacion","$calorias","$tiempo"))


        }
        adapter= PerdidaPesoHolder(this, favoritosreal)
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