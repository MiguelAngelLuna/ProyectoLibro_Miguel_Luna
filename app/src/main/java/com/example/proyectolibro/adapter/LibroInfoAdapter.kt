package com.example.proyectolibro.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectolibro.R
import com.example.proyectolibro.entity.Libros

class LibroInfoAdapter(private val listaLibros: List<Libros>) :
    RecyclerView.Adapter<LibroInfoAdapter.LibroViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibroViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_libro_info, parent, false)
        return LibroViewHolder(view)
    }

    override fun onBindViewHolder(holder: LibroViewHolder, position: Int) {
        val libro = listaLibros[position]

        holder.tvTitulo.text = libro.Titulo
        holder.tvAutor.text = "por ${libro.Autor}"
        holder.tvPrecio.text = "S/ ${String.format("%.2f", libro.Precio)}"
        holder.tvDescripcion.text = libro.Descrip
        holder.imgLibro.setImageResource(libro.Id_Imagen)

        // Mostrar nombre de categoría según el ID
        val nombreCategoria = when (libro.id_Categ) {
            1 -> "Romance"
            2 -> "Terror"
            3 -> "Ciencia Ficción"
            4 -> "Aventura"
            5 -> "Suspenso"
            else -> "Otros"
        }
        holder.tvCategoria.text = nombreCategoria
    }

    override fun getItemCount(): Int = listaLibros.size

    inner class LibroViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgLibro: ImageView = view.findViewById(R.id.ivImagenInfo)
        val tvTitulo: TextView = view.findViewById(R.id.tvTituloInfo)
        val tvAutor: TextView = view.findViewById(R.id.tvAutorInfo)
        val tvPrecio: TextView = view.findViewById(R.id.tvPrecioInfo)
        val tvDescripcion: TextView = view.findViewById(R.id.tvDescripcionInfo)
        val tvCategoria: TextView = view.findViewById(R.id.tvCategoria)
    }
}
