package com.example.proyectolibro.entity

data class Usuario(
    val idUsuario: Int,
    val nombre: String,
    val contrasena: String,
    val genero: String,
    val correo: String
)