package com.example.proyectolibro.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.proyectolibro.R

class InfoLibroFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflamos el layout que mostrará la info del libro
        return inflater.inflate(R.layout.item_libro_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Recuperar los datos enviados desde MenuFragment
        val titulo = arguments?.getString("titulo")
        val autor = arguments?.getString("autor")
        val descripcion = arguments?.getString("descripcion")
        val precio = arguments?.getDouble("precio")
        val imagen = arguments?.getInt("imagen")

        // Asignar los valores a los elementos visuales del layout
        view.findViewById<TextView>(R.id.tvTituloInfo).text = titulo
        view.findViewById<TextView>(R.id.tvAutorInfo).text = "por $autor"
        view.findViewById<TextView>(R.id.tvDescripcionInfo).text = descripcion
        view.findViewById<TextView>(R.id.tvPrecioInfo).text = "S/. $precio"
        view.findViewById<ImageView>(R.id.ivImagenInfo).setImageResource(imagen ?: 0)
    }
}
