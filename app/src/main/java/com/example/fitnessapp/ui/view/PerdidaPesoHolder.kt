package com.example.fitnessapp.ui.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fitnessapp.R
import com.example.fitnessapp.data.Perdidapeso
import com.example.fitnessapp.databinding.ItemRecetaBinding

class PerdidaPesoHolder(val context: Context,val listaperdidpeso: MutableList<Perdidapeso>) :
    RecyclerView.Adapter<PerdidaPesoHolder.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        var binding= ItemRecetaBinding.bind(itemView)
        var titulo=binding.NombreReceta
        var foto=binding.fotoreceta
        var information=binding.informacion
        var calorias=binding.calorias
        var tiempo=binding.tiempo
        init {

        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PerdidaPesoHolder.ViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.item_receta, parent, false)
        return PerdidaPesoHolder.ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PerdidaPesoHolder.ViewHolder, position: Int) {
        holder.titulo.text= listaperdidpeso[position].titulo
        Glide.with(context).load(listaperdidpeso[position].foto).into(holder.foto)
        holder.information.text=listaperdidpeso[position].informacion
        holder.calorias.text=listaperdidpeso[position].calorias
        holder.tiempo.text=listaperdidpeso[position].tiempo
    }

    override fun getItemCount(): Int {
        return listaperdidpeso.size
    }

}