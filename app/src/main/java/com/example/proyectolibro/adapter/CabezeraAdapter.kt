package com.example.proyectolibro.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectolibro.R
import com.example.proyectolibro.entity.Libros

class CabezeraAdapter(private val listaCabezera: List<Libros>) :
    RecyclerView.Adapter<CabezeraAdapter.CabezeraViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CabezeraViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cabezera, parent, false)
        return CabezeraViewHolder(view)
    }

    override fun onBindViewHolder(holder: CabezeraViewHolder, position: Int) {
        val libro = listaCabezera[position]

        holder.imagen.setImageResource(libro.Id_Imagen)
        holder.titulo.text = libro.Titulo
    }

    override fun getItemCount(): Int = listaCabezera.size

    inner class CabezeraViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imagen: ImageView = itemView.findViewById(R.id.ImagenCat)
        val titulo: TextView = itemView.findViewById(R.id.textTituloCategoria)
    }
}