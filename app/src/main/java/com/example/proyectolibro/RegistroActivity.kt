package com.example.proyectolibro

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

class RegistroActivity : AppCompatActivity() {

    //--------------------1 Crear clase--------------------
    var ivRegresar: ImageView?=null

    private lateinit var tietuser : TextInputEditText
    private lateinit var tietcorreo : TextInputEditText
    private lateinit var tietcontraseña : TextInputEditText
    private lateinit var tietgenero : RadioGroup

    private lateinit var btnInicio: android.widget.Button
    //-----------------------------------------------------

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registro)

        //----------------- 2 CONECTAR VARIABLES CON ID DEL DISEÑO--------------

        tietuser = findViewById(R.id.textUsuario)
        tietcorreo = findViewById(R.id.textCorreo2)
        tietcontraseña = findViewById(R.id.textContraseña2)
        tietgenero = findViewById(R.id.Sexo)
        btnInicio = findViewById(R.id.btnInicio)

        //----------------------4 LLAMAR EVENTOS------------

        ivRegresar = findViewById(R.id.ivRegresar) //---VINCULAMOS

        //ACCION AL HACER CLICK
        ivRegresar?.setOnClickListener {
            cambioActivity(Acceso_Activity::class.java)
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
        finish()
    } //FIN

    /*-------------VALIDACIONES CAMPOS--------------*/
    fun ValidarCampos() {
        val user = tietuser.text.toString().trim()
        val correo = tietcorreo.text.toString().trim()
        val clave = tietcontraseña.text.toString().trim()
        val generoId = tietgenero.checkedRadioButtonId
        var error = false

        // Usuario
        if (user.isEmpty()) {
            tietuser.error = "Ingrese un usuario"
            error = true
        } else {
            tietuser.error = null
        }

        // Correo
        if (correo.isEmpty()) {
            tietcorreo.error = "Ingrese un correo"
            error = true
        } else {
            tietcorreo.error = null
        }

        // Contraseña
        if (clave.isEmpty()) {
            tietcontraseña.error = "Ingrese una contraseña"
            error = true
        } else {
            tietcontraseña.error = null
        }

        // Género
        if (generoId == -1) {
            Toast.makeText(this, "Seleccione un género", Toast.LENGTH_SHORT).show()
            error = true
        }

        if (!error) {
            Toast.makeText(
                this,
                "Validación correcta ✅ Guardando datos...",
                Toast.LENGTH_LONG).show()
        }
    }
}