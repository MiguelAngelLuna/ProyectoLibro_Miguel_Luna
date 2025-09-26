package com.example.proyectolibro

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Acceso_Activity : AppCompatActivity() {

    //--------------------1 Crear clase--------------------
    var Tv_Registro: TextView?=null
    //-----------------------------------------------------

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login) //conectar con el DISEÑO XML

        //-----------------3 LLAMAR METODO--------------
        Tv_Registro = findViewById(R.id.Tv_Registro)

        Tv_Registro?.setOnClickListener {
            cambioActivity(RegistroActivity::class.java)
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
    } //FIN
}