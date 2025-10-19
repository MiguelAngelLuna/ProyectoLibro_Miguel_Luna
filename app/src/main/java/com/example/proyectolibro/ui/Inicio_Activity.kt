package com.example.proyectolibro.ui

import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.proyectolibro.Fragment.MenuFragment
import com.example.proyectolibro.R
import com.google.android.material.navigation.NavigationView

class Inicio_Activity : AppCompatActivity() {

    private lateinit var DlayMenu : DrawerLayout
    private lateinit var IvMenu : ImageView
    private lateinit var MenuNavi  : NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.inicio_menu_principal)

        //Inicializar
        DlayMenu = findViewById(R.id.dlayMenu)
        IvMenu = findViewById(R.id.ivMenu)
        MenuNavi = findViewById(R.id.menuNavi)

        //ABRIR CON CLICK
        IvMenu.setOnClickListener {
            DlayMenu.open()
        }

        MenuNavi.setNavigationItemSelectedListener { menuItem ->
            menuItem.isChecked = true
            DlayMenu.closeDrawers()

            //-----LLAMAR A FRAGMENT----
            when (menuItem.itemId){
                R.id.itInicio -> replaceFragment(MenuFragment())
            }
            true
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.dlayMenu)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        replaceFragment(MenuFragment())
    }

    private fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.ConteFragment, fragment).commit()
    }
}