package com.example.proyectolibro.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectolibro.R
import com.example.proyectolibro.entity.Libros

class PrincipalAdapter(
    private val listaCabecera: List<Libros>,
    private val listaLibros: List<Libros>,
    private val onLibroClick: (Libros) -> Unit

) : RecyclerView.Adapter<PrincipalAdapter.PrincipalViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrincipalViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cabezera_categeria, parent, false)
        return PrincipalViewHolder(view)
    }

    override fun onBindViewHolder(holder: PrincipalViewHolder, position: Int) {
        val cabeceraAdapter = CabezeraAdapter(listaCabecera)
        holder.rvCabecera.layoutManager =
            LinearLayoutManager(holder.itemView.context, LinearLayoutManager.HORIZONTAL, false)
        holder.rvCabecera.adapter = cabeceraAdapter

        // 👇 Pasas el callback al MenuAdapter
        val librosAdapter = MenuAdapter(listaLibros, onLibroClick)
        holder.rvHistorial.layoutManager = GridLayoutManager(holder.itemView.context, 2)
        holder.rvHistorial.adapter = librosAdapter
    }

    override fun getItemCount() = 1

    inner class PrincipalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val rvCabecera: RecyclerView = itemView.findViewById(R.id.rvCabecera)
        val rvHistorial: RecyclerView = itemView.findViewById(R.id.rvHistorial)
    }
}
