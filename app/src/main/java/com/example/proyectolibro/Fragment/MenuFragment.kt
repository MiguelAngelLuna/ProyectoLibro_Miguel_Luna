package com.example.proyectolibro.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectolibro.R
import com.example.proyectolibro.adapter.PrincipalAdapter
import com.example.proyectolibro.entity.Libros

class MenuFragment : Fragment() {

    private lateinit var rvPrincipal: RecyclerView
    private lateinit var principalAdapter: PrincipalAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvPrincipal = view.findViewById(R.id.rvPrincipal)

        val listaLibros = listOf(
            Libros(1, "Los Hermanos", "Thomas Hurtado", "Libros vale oro", 220.10, R.mipmap.ic_libro1, 2),
            Libros(2, "El Funerario", "Juan Campos", "Libros vale oro", 320.10, R.mipmap.ic_libro2, 2),
            Libros(3, "Los Demonios", "Matias Layano", "Libros vale oro", 410.10, R.mipmap.ic_libro3, 5),
            Libros(4, "El Bueno y el Malo", "Santiago Romas", "Libros vale oro", 120.10, R.mipmap.ic_libro4, 5),
            Libros(5, "Te Esperaré", "Daniel Campos", "Libros vale oro", 120.10, R.mipmap.ic_libro5, 1),
            Libros(6, "La Restauradora", "Akira Toriyama", "Libros vale oro", 210.10, R.mipmap.ic_libro6, 5),
            Libros(7, "Mejor Muerto", "Akira Toriyama", "Libros vale oro", 210.10, R.mipmap.ic_libro7, 3),
            Libros(8, "El Templo", "Akira Toriyama", "Libros vale oro", 210.10, R.mipmap.ic_libro8, 4)
        )

        val listaCabecera = listOf(
            Libros(9, "X-MEN", "", "", 10.0, R.mipmap.ic_comic1, 3),
            Libros(10, "El Bosque", "", "", 20.0, R.mipmap.ic_comic2, 8),
            Libros(11, "Diario Sabueso", "", "", 30.0, R.mipmap.ic_comic3, 9),
            Libros(12, "Miserables", "", "", 30.0, R.mipmap.ic_comic4, 10),
            Libros(13, "KRIEG", "", "", 30.0, R.mipmap.ic_comic5, 11)
        )

        // 👇 AQUÍ está la magia
        principalAdapter = PrincipalAdapter(listaCabecera, listaLibros) { libroSeleccionado ->
            val bundle = Bundle().apply {
                putString("titulo", libroSeleccionado.Titulo)
                putString("autor", libroSeleccionado.Autor)
                putString("descripcion", libroSeleccionado.Descrip)
                putDouble("precio", libroSeleccionado.Precio)
                putInt("imagen", libroSeleccionado.Id_Imagen)
            }

            val fragment = InfoLibroFragment()
            fragment.arguments = bundle

            parentFragmentManager.beginTransaction()
                .replace(R.id.dlayMenu, fragment) // 👈 asegúrate que este sea tu id del contenedor
                .addToBackStack(null)
                .commit()
        }

        rvPrincipal.layoutManager = LinearLayoutManager(requireContext())
        rvPrincipal.adapter = principalAdapter
    }
}