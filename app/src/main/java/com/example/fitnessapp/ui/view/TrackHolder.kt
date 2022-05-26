package com.example.fitnessapp.ui.view

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessapp.R
import com.example.fitnessapp.data.EjercicioRegistradoTrack
import com.example.fitnessapp.data.Perdidapeso
import com.example.fitnessapp.databinding.ItemRecetaBinding
import com.example.fitnessapp.databinding.ItemTrackEjercicioBinding
class TrackHolder (val context: Context, val listaejerciciostrack: MutableList<EjercicioRegistradoTrack>) :
    RecyclerView.Adapter<TrackHolder.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var binding= ItemTrackEjercicioBinding.bind(itemView)
        var titulo=binding.titulotrack
        var fecha=binding.fechatrack


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



    }

    override fun getItemCount(): Int {
     return  listaejerciciostrack.size
    }

}