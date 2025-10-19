package com.example.proyectolibro.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectolibro.R
import com.example.proyectolibro.entity.Libros

class MenuAdapter(
    private val listaHistorial: List<Libros>,
    private val onLibroClick: (Libros) -> Unit
) : RecyclerView.Adapter<MenuAdapter.HistorialViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistorialViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_categorias, parent, false)
        return HistorialViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistorialViewHolder, position: Int) {
        val libro = listaHistorial[position]

        holder.IvImagen.setImageResource(libro.Id_Imagen)
        holder.Titulo.text = libro.Titulo

        val nombreCategoria = when (libro.id_Categ) {
            1 -> "Romance"
            2 -> "Terror"
            3 -> "Ciencia Ficción"
            4 -> "Aventura"
            5 -> "Suspenso"
            else -> "Otros"
        }
        holder.Categoria.text = nombreCategoria

        // clic del libro
        holder.itemView.setOnClickListener {
            onLibroClick(libro)
        }
    }

    override fun getItemCount() = listaHistorial.size

    inner class HistorialViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val IvImagen: ImageView = itemView.findViewById(R.id.ivImagen)
        val Titulo: TextView = itemView.findViewById(R.id.txNomLibro)
        val Categoria: TextView = itemView.findViewById(R.id.tvCategoria)
    }
}
