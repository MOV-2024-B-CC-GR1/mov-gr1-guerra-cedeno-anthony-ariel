package com.example.ccgr12024b_aagc

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar

class ACicloVida : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_aciclo_vida)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.cl_ciclo_vida)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        mostrarsnackbar("OnCreate")
    }

    override fun onStart() {
        super.onStart()
        mostrarsnackbar("OnStart")
    }

    override fun onResume() {
        super.onResume()
        mostrarsnackbar("onResume")
    }

    override fun onRestart() {
        super.onRestart()
        mostrarsnackbar("onRestart")
    }

    override fun onPause() {
        super.onPause()
        mostrarsnackbar("onPause")
    }

    override fun onStop() {
        super.onStop()
        mostrarsnackbar("onStop")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        // Recurperar cada una de las variables
        val textoRecuperado: String? = savedInstanceState
            .getString("varaibleTextoGuardado")
        if ( textoRecuperado != null){
            // textoGlobal = textoRecuperado -> ya no es necesario porque en esta funcion se lo obvia
            mostrarsnackbar(textoRecuperado) // ya se guarda el texto global
        }
    }



    var textoGlobal= ""
    fun mostrarsnackbar(text: String){
        textoGlobal += text
        val snack = Snackbar.make(
            findViewById(R.id.cl_ciclo_vida),
            textoGlobal,
            Snackbar.LENGTH_INDEFINITE
        )
        snack.show()
    }
}