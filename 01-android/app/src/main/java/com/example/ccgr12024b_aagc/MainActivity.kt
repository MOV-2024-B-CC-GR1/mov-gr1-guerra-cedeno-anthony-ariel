package com.example.ccgr12024b_aagc

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.cl_ciclo_vida)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnCicloVida = findViewById<Button>(R.id.btn_ciclo_vida)
        btnCicloVida.setOnClickListener{
            irActividad(ACicloVida::class.java) // aqui agregamos la nueva actividad a la que queremos ir
        }
        val btnIrListView = findViewById<Button>(R.id.btn_ir_list_view)
        btnIrListView.setOnClickListener{
            irActividad(BListView::class.java) // aqui agregamos la nueva actividad a la que queremos ir
        }
    }
    fun irActividad(clase:Class<*>){
        startActivity(Intent(this, clase)) //Siempre se emplean los intents
    }
}