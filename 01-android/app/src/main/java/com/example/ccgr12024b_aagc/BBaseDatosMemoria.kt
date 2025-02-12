package com.example.ccgr12024b_aagc

class BBaseDatosMemoria {
    companion object{
        val arregloBEntrenador = arrayListOf<BEntrenador>()
        init {
            arregloBEntrenador.add(BEntrenador(1,"Ana","ana@app.com"))
            arregloBEntrenador.add(BEntrenador(2,"Pepe","pepe@app.com"))
            arregloBEntrenador.add(BEntrenador(3,"Pablo","pablo@app.com"))
        }
    }
}