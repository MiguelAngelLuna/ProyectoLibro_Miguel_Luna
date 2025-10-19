package com.example.proyectolibro.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
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

        // Recuperar los datos enviados desde el fragmento anterior
        val titulo = arguments?.getString("titulo")
        val autor = arguments?.getString("autor")
        val descripcion = arguments?.getString("descripcion")
        val precio = arguments?.getDouble("precio")
        val imagen = arguments?.getInt("imagen")

        view.findViewById<TextView>(R.id.tvTituloInfo).text = titulo
        view.findViewById<TextView>(R.id.tvAutorInfo).text = "por $autor"
        view.findViewById<TextView>(R.id.tvDescripcionInfo).text = descripcion
        view.findViewById<TextView>(R.id.tvPrecioInfo).text = "S/. $precio"
        view.findViewById<ImageView>(R.id.ivImagenInfo).setImageResource(imagen ?: 0)

        // Botón Comprar
        val btnComprar = view.findViewById<Button>(R.id.btnComprar)
        btnComprar.setOnClickListener {

            Toast.makeText(requireContext(), "Libro comprado: $titulo", Toast.LENGTH_SHORT).show()
        }

        // Botón Regresar
        val btnRegresar = view.findViewById<Button>(R.id.btnRegresar)
        btnRegresar.setOnClickListener {
            // Regresa al fragmento anterior
            requireActivity().supportFragmentManager.popBackStack()
        }
    }
}