package com.example.ccgr12024b_aagc

import android.media.audiofx.AudioEffect.Descriptor

class BEntrenador (  //Aqui colocaremos el modelo que vamos a usar
    var id: Int,
    var nombre: String,
    var descripcion: String?
){
    override fun toString(): String {
        return "$nombre $descripcion"
    }
}
