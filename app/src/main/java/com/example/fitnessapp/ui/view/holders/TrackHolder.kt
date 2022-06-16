package com.example.fitnessapp.ui.view.holders

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessapp.R
import com.example.fitnessapp.data.models.EjercicioRegistradoTrack
import com.example.fitnessapp.databinding.ItemTrackEjercicioBinding
var icono: Int =R.drawable.jj
var icono3: Int =R.drawable.flame

class TrackHolder (val context: Context, val listaejerciciostrack: MutableList<EjercicioRegistradoTrack>) :
    RecyclerView.Adapter<TrackHolder.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var binding= ItemTrackEjercicioBinding.bind(itemView)
        var titulo=binding.titulotrack
        var fecha=binding.fechatrack
        var pasos=binding.pasos


        init {


        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackHolder.ViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.item_track_ejercicio, parent, false)
        return TrackHolder.ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TrackHolder.ViewHolder, position: Int) {


       holder.titulo.text=listaejerciciostrack[position].titulo.toString()
       holder.fecha.text= listaejerciciostrack[position].fecha.toString()
        holder.pasos.text=listaejerciciostrack[position].pasos.toString()

        if (holder.titulo.text.toString().equals("FullBody")){
            holder.binding.imagenn.setBackgroundResource(icono3)

        }else{
            holder.binding.imagenn.setBackgroundResource(icono)
        }


    }

    override fun getItemCount(): Int {
     return  listaejerciciostrack.size
    }

}