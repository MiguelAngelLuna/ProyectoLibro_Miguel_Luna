package com.example.proyectolibro.entity

data class Libros (
    val Id_Libros : Int,
    val Titulo : String,
    val Autor : String,
    val Descrip : String,
    val Precio : Double,
    val Id_Imagen : Int,
    val id_Categ : Int
)