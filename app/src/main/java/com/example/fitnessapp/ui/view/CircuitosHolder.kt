package com.example.fitnessapp.ui.view

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Glide.init
import com.example.fitnessapp.R
import com.example.fitnessapp.data.DatosEjercicios
import com.example.fitnessapp.data.Objetivosutricion
import com.example.fitnessapp.databinding.ItemEjercicicosBinding
import com.example.fitnessapp.databinding.ItemObjetivosRecetaBinding

class CircuitosHolder(val context: Context, val ejercicioslist: MutableList<Objetivosutricion> ) :
    RecyclerView.Adapter<CircuitosHolder.ViewHolder>() {

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        val binding = ItemObjetivosRecetaBinding.bind(itemView)
        var titulo=binding.tituloobjetivo
        var imagen=binding.imagenobjetivo
        init {
            itemView.setOnClickListener {
                //cogemos la posicion del adapet y dependiendo de la posicion hacemos una u otra cosa
                val position= adapterPosition
                val alerta = AlertDialog.Builder(itemView.context)
                if (position==0) {
                    context2.startActivity(Intent(context2, Fullbody::class.java))
                }
                else if (position==1) {
                    context2.startActivity(Intent(context2, StepCounter::class.java))
                } else if (position==2) {

                    alerta.setMessage("Proximamente.....")
                        .setTitle("Información")
                        .setCancelable(false)//esto es para que clique fuera del popup de alerta
                        .setNegativeButton(
                            "Cerrar",
                            DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })
                        .create()
                        .show()
                }
                else  {
                    alerta.setMessage("Proximamente.....")
                        .setTitle("Información")
                        .setCancelable(false)//esto es para que clique fuera del popup de alerta
                        .setNegativeButton(
                            "Cerrar",
                            DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })
                        .create()
                        .show()
                }

            }

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.item_objetivos_receta, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.titulo.text=ejercicioslist[position].titulo.toString()
        Glide.with(context).load(ejercicioslist[position].foto).into(holder.imagen)
    }

    override fun getItemCount(): Int {
        return ejercicioslist.size
    }

}