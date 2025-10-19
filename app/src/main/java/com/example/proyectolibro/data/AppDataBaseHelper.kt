package com.example.proyectolibro.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class AppDataBaseHelper (context: Context) :
    SQLiteOpenHelper(context, "mundoLibro_app.db", null , 1){

    override fun onCreate(db: SQLiteDatabase?) {

      // TB_USUARIO
      db?.execSQL("""
      CREATE TABLE Usuario(
          idUsuario INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
          nombre TEXT NOT NULL,
          clave TEXT NOT NULL,
          genero TEXT NOT NULL,
          correo TEXT NOT NULL
      ) """.trimIndent())

       // TB_LIBROS
        db?.execSQL("""
      CREATE TABLE Libros(
          idLibros INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
          titulo TEXT NOT NULL,
          autor TEXT NOT NULL,
          descrip TEXT NOT NULL,
          precio REAL NOT NULL,
          idImagen INTEGER NOT NULL,
          idCategoria INTEGER NOT NULL,
          FOREIGN KEY (idCategoria) REFERENCES Categoria(idCategoria)
      ) """.trimIndent())


        //TB_CATGORIAS
        db?.execSQL("""
         CREATE TABLE Categorias(
          idCategoria INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
          nombre TEXT NOT NULL,
          descrip TEXT NOT NULL
        ) """.trimIndent())
    }//FIN


    override fun onUpgrade( db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        // Elimina todas las tablas si se actualiza la versión
        db?.execSQL("DROP TABLE IF EXISTS Usuario")
        db?.execSQL("DROP TABLE IF EXISTS Libros")
        db?.execSQL("DROP TABLE IF EXISTS Categorias")

        // Vuelve a crearlas
        onCreate(db)
    }
}