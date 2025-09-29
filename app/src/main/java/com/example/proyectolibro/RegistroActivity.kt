package com.example.proyectolibro

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RegistroActivity : AppCompatActivity() {

    //--------------------1 Crear clase--------------------
    var ivRegresar: ImageView?=null
    //-----------------------------------------------------

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registro)

        //-----------------3 LLAMAR METODO--------------
        ivRegresar = findViewById(R.id.ivRegresar) //---VINCULAMOS

        //ACCION AL HACER CLICK
        ivRegresar?.setOnClickListener {
            cambioActivity(Acceso_Activity::class.java)
        }
        //------------------------------------------------

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    //----------------------------------2 METODOS-----------------

    //Enviar a registro
    fun cambioActivity(activityDestino : Class<out Activity>) {
        val intent = Intent(this, activityDestino)
        startActivity(intent)
        finish()
    } //FIN
}