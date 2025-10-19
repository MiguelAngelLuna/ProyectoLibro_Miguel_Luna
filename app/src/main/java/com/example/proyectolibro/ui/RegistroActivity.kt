package com.example.proyectolibro.ui

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.proyectolibro.R
import com.example.proyectolibro.data.AppDataBaseHelper

class RegistroActivity : AppCompatActivity() {

    private lateinit var nombreUsuario: EditText
    private lateinit var correo: EditText
    private lateinit var clave: EditText
    private lateinit var generoGrupo: RadioGroup
    private lateinit var btnRegistrarse: Button
    private lateinit var btnRegresar: ImageView
    private lateinit var dbHelper: AppDataBaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registro)

        // Ajuste visual (no tocar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicializar vistas
        nombreUsuario = findViewById(R.id.textUsuario)
        correo = findViewById(R.id.textCorreo2)
        clave = findViewById(R.id.textContraseña2)
        generoGrupo = findViewById(R.id.Sexo)
        btnRegistrarse = findViewById(R.id.btnInicio)
        btnRegresar = findViewById(R.id.ivRegresar)

        dbHelper = AppDataBaseHelper(this)

        // Botón de regresar
        btnRegresar.setOnClickListener {
            finish()
        }

        // Botón de registrar
        btnRegistrarse.setOnClickListener {
            registrarUsuario()
        }
    }

    private fun registrarUsuario() {
        val nombre = nombreUsuario.text.toString().trim()
        val correoTxt = correo.text.toString().trim()
        val claveTxt = clave.text.toString().trim()
        val generoSeleccionado = generoGrupo.checkedRadioButtonId

        // --- VALIDACIONES DE CAMPOS ----
        if (nombre.isEmpty()) {
            Toast.makeText(this, "Ingresa tu nombre", Toast.LENGTH_SHORT).show()
            return
        }

        if (correoTxt.isEmpty()) {
            Toast.makeText(this, "Ingresa tu correo", Toast.LENGTH_SHORT).show()
            return
        }

        // Validar formato de correo
        val patronCorreo = Regex("^[a-zA-Z0-9._%+-]{6,}@gmail\\.com$")
        if (!correoTxt.matches(patronCorreo)) {
            Toast.makeText(this, "Correo inválido. Debe ser un @gmail.com válido", Toast.LENGTH_SHORT).show()
            return
        }

        if (claveTxt.isEmpty()) {
            Toast.makeText(this, "Ingresa una contraseña", Toast.LENGTH_SHORT).show()
            return
        }

        // Reglas de seguridad para contraseña
        if (claveTxt.length < 5) {
            Toast.makeText(this, "La contraseña debe tener al menos 5 caracteres", Toast.LENGTH_SHORT).show()
            return
        }
        if (!claveTxt.any { it.isUpperCase() }) {
            Toast.makeText(this, "Debe incluir al menos una letra mayúscula", Toast.LENGTH_SHORT).show()
            return
        }
        if (!claveTxt.any { it.isDigit() }) {
            Toast.makeText(this, "Debe incluir al menos un número", Toast.LENGTH_SHORT).show()
            return
        }

        if (generoSeleccionado == -1) {
            Toast.makeText(this, "Selecciona tu género", Toast.LENGTH_SHORT).show()
            return
        }

        val genero = findViewById<RadioButton>(generoSeleccionado).text.toString()

        // GUARDAR EN BASE DE DATOS
        val db = dbHelper.writableDatabase

        try {
            // Verificar si el correo ya está registrado
            val cursor = db.rawQuery("SELECT 1 FROM Usuario WHERE correo = ?", arrayOf(correoTxt))
            if (cursor.moveToFirst()) {
                Toast.makeText(this, "El correo ya está registrado ❌", Toast.LENGTH_SHORT).show()
                cursor.close()
                return
            }
            cursor.close()

            // Insertar nuevo usuario
            val valores = ContentValues().apply {
                put("nombre", nombre)
                put("correo", correoTxt)
                put("clave", claveTxt)
                put("genero", genero)
            }

            val resultado = db.insert("Usuario", null, valores)

            if (resultado != -1L) {
                Toast.makeText(this, "Usuario registrado con éxito ✅", Toast.LENGTH_SHORT).show()
                limpiarCampos()

                // Redirigir automáticamente al login
                val intent = Intent(this, Acceso_Activity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Error al registrar usuario ❌", Toast.LENGTH_SHORT).show()
            }

        } catch (e: Exception) {
            Toast.makeText(this, "Error en el registro: ${e.message}", Toast.LENGTH_LONG).show()
        } finally {
            db.close()
        }
    }

    //LIMPIAR CAMPOS
    private fun limpiarCampos() {
        nombreUsuario.text?.clear()
        correo.text?.clear()
        clave.text?.clear()
        generoGrupo.clearCheck()
    }
}
