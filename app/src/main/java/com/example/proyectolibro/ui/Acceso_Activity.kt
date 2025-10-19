package com.example.proyectolibro.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.proyectolibro.Fragment.MenuFragment
import com.example.proyectolibro.R
import com.example.proyectolibro.data.AppDataBaseHelper
import com.google.android.material.textfield.TextInputEditText

class Acceso_Activity : AppCompatActivity() {

    // -------------------- VARIABLES --------------------
    private lateinit var tvRegistro: TextView
    private lateinit var tietCorreo: TextInputEditText
    private lateinit var tietContraseña: TextInputEditText
    private lateinit var btnInicio: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        // Conectar variables con el diseño XML
        tvRegistro = findViewById(R.id.Tv_Registro)
        tietCorreo = findViewById(R.id.textCorreo)
        tietContraseña = findViewById(R.id.textContraseña)
        btnInicio = findViewById(R.id.btnInicio)

        // Eventos
        tvRegistro.setOnClickListener {
            cambioActivity(RegistroActivity::class.java)
        }

        btnInicio.setOnClickListener {
            validarCampos()
        }

        // Ajustar diseño con los márgenes del sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    // -------------------- MÉTODOS --------------------

    // Cambiar a otra Activity
    private fun cambioActivity(activityDestino: Class<out Activity>) {
        startActivity(Intent(this, activityDestino))
    }

    // Validar campos y verificar login
    private fun validarCampos() {
        val correo = tietCorreo.text.toString().trim()
        val clave = tietContraseña.text.toString().trim()

        // ===== VALIDACIONES DE ENTRADA =====
        if (correo.isEmpty()) {
            Toast.makeText(this, "Ingresa tu correo electrónico", Toast.LENGTH_SHORT).show()
            return
        }

        // Validar formato de correo Gmail
        val patronCorreo = Regex("^[a-zA-Z0-9._%+-]{6,}@gmail\\.com$")

        if (!correo.matches(patronCorreo)) {
            Toast.makeText(this, "Correo inválido. Usa un formato correcto", Toast.LENGTH_SHORT).show()
            return
        }

        if (clave.isEmpty()) {
            Toast.makeText(this, "Ingresa tu contraseña", Toast.LENGTH_SHORT).show()
            return
        }

        // Validación mínima de seguridad
        if (clave.length < 5) {
            Toast.makeText(this, "La contraseña debe tener al menos 5 caracteres", Toast.LENGTH_SHORT).show()
            return
        }

        // Mostrar mensaje de carga
        Toast.makeText(this, "Verificando credenciales...", Toast.LENGTH_SHORT).show()

        //-----CONSULTA A LA BASE DE DATOS ------
        val dbHelper = AppDataBaseHelper(this)
        val db = dbHelper.readableDatabase

        try {
            val cursor = db.rawQuery(
                "SELECT nombre, clave FROM Usuario WHERE correo = ?",
                arrayOf(correo)
            )

            if (cursor.moveToFirst()) {
                val nombre = cursor.getString(0)
                val claveGuardada = cursor.getString(1)

                if (clave == claveGuardada) {
                    // Login correcto
                    Toast.makeText(this, "Bienvenido $nombre 👋", Toast.LENGTH_LONG).show()

                    // Redirigir al menú principal
                    val intent = Intent(this, Inicio_Activity::class.java)

                    intent.putExtra("nombreUsuario", nombre)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Contraseña incorrecta ❌", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Usuario no encontrado", Toast.LENGTH_SHORT).show()
            }

            cursor.close()
        } catch (e: Exception) {
            Toast.makeText(this, "Error al acceder a la base de datos: ${e.message}", Toast.LENGTH_LONG).show()
        } finally {
            db.close()
        }
    }
}