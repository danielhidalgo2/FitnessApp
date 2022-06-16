package com.example.fitnessapp.ui.view.holders

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fitnessapp.R
import com.example.fitnessapp.data.models.DatosEjercicios
import com.example.fitnessapp.databinding.ItemEjercicicosBinding
import com.example.fitnessapp.ui.view.Ejercicios
import com.example.fitnessapp.ui.view.Nutricion
import com.example.fitnessapp.ui.view.Track

public lateinit var context2:Context


class EjerciciosHolder(val context: Context , val ejercicioslist: MutableList<DatosEjercicios> ) :
    RecyclerView.Adapter<EjerciciosHolder.ViewHolder>() {


    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        val binding = ItemEjercicicosBinding.bind(itemView)
        var imagen1=binding.imagen
        init {
            itemView.setOnClickListener {
                //cogemos la posicion del adapet y dependiendo de la posicion hacemos una u otra cosa
                val position= adapterPosition
                if (position==0) {
                    context2.startActivity(Intent(context2, Ejercicios::class.java))
                }
                else if (position==1) {
                    context2.startActivity(Intent(context2, Nutricion::class.java))
                }
                else  {
                    context2.startActivity(Intent(context2, Track::class.java))
                }

            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.item_ejercicicos, parent, false)
        return ViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ejercicios=ejercicioslist[position]
        Glide.with(context).load(ejercicios.imagen).into(holder.imagen1)
context2=context


    }

    override fun getItemCount(): Int {
        return ejercicioslist.size
    }


}