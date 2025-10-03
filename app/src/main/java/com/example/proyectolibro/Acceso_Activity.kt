package com.example.proyectolibro

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

class Acceso_Activity : AppCompatActivity() {

    //--------------------1 Crear clase--------------------
    var Tv_Registro: TextView?=null

    private lateinit var tietcorreo : TextInputEditText
    private lateinit var tietContraseña : TextInputEditText
    private lateinit var btnInicio: android.widget.Button


    //-----------------------------------------------------

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login) //conectar con el DISEÑO XML

        //----------------- 2 CONECTAR VARIABLES CON ID DEL DISEÑO--------------

        Tv_Registro = findViewById(R.id.Tv_Registro)
        tietcorreo = findViewById(R.id.textCorreo)
        tietContraseña = findViewById(R.id.textContraseña)
        btnInicio = findViewById(R.id.btnInicio)

        //----------------------4 LLAMAR EVENTOS------------

        //--- IR A REGISTRO ---
        Tv_Registro?.setOnClickListener {
            cambioActivity(RegistroActivity::class.java)
        }

        //--- VALIDAR CAMPOS ---
        btnInicio.setOnClickListener {
            ValidarCampos()
        }

        //------------------------------------------------

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
  //----------------------------------3 METODOS-----------------

    //Enviar a registro
    fun cambioActivity(activityDestino : Class<out Activity>) {
        val intent = Intent(this, activityDestino)
        startActivity(intent)
    } //FIN


    /*-------------VALIDACIONES CAMPOS--------------*/

    fun ValidarCampos() {
        val correo = tietcorreo.text.toString().trim()
        val clave = tietContraseña.text.toString().trim()
        var error: Boolean = false

        //-------Correo---------
        if (correo.isEmpty()) {
            tietcorreo.error = "Ingrese un correo"
            error = true
        } else {
            tietcorreo.error = null
        } //Fin

        //---------Contraseña---------
        if (clave.isEmpty()) {
            tietContraseña.error = "Ingrese Contraseña"
            error = true
        } else {
            tietContraseña.error = null
        } //Fin

        if (error) {
            return
        } else {
            Toast.makeText(
                this,
                "Validación Correcta. Procesando Datos",
                Toast.LENGTH_LONG).show()
        }
    }
}