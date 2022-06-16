package com.example.fitnessapp.ui.view.holders

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fitnessapp.R
import com.example.fitnessapp.data.models.Objetivosutricion
import com.example.fitnessapp.databinding.ItemObjetivosRecetaBinding
import com.example.fitnessapp.ui.view.AltasProteinas
import com.example.fitnessapp.ui.view.EstiloSaludable
import com.example.fitnessapp.ui.view.GananciaMuscular
import com.example.fitnessapp.ui.view.PerdidaPeso

class NutricionHolder(val context: Context, val listanutricion:MutableList<Objetivosutricion>) :
    RecyclerView.Adapter<NutricionHolder.ViewHolder>() {

  class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
      val binding=ItemObjetivosRecetaBinding.bind(itemView)
      val imagen=binding.imagenobjetivo
      val titulo=binding.tituloobjetivo

      init {
          itemView.setOnClickListener {
              //cogemos la posicion del adapet y dependiendo de la posicion hacemos una u otra cosa
              val position= adapterPosition
              if (position==0) {
                  context2.startActivity(Intent(context2, PerdidaPeso::class.java))
              }
              else if (position==1) {
                  context2.startActivity(Intent(context2, GananciaMuscular::class.java))
              }
              else if (position==2) {
                  context2.startActivity(Intent(context2, AltasProteinas::class.java))
              }else{
                  context2.startActivity(Intent(context2, EstiloSaludable::class.java))



              }
      }

      }
  }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.item_objetivos_receta, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ejercicios=listanutricion[position]
        Glide.with(context).load(ejercicios.foto).into(holder.imagen)
        holder.titulo.text = listanutricion[position].titulo
        context2=context
    }

    override fun getItemCount(): Int {
       return listanutricion.size
    }
}