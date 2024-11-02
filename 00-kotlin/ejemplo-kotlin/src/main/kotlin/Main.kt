package org.example

import java.util.Date

fun main() {
    println("Hello World!")
    val inmutable : String = "Cadena de texto";
//    inmutable = "Otra cadena" // no se puede asignar dado el tipo de variables
    println(inmutable);
    var mutable : String = "Cadena de texto mutable";
    mutable = "Otra cadena de texto"
    println(mutable);

    // Duck Typing
    val ejemploVariable = "Anthony Guerra"
    ejemploVariable.trim()
    println(ejemploVariable)

    val edadEjemplo: Int = 12
    println(edadEjemplo)

    val fechaNacimiento: Date = Date();
    println(fechaNacimiento)

    // WHEN-CASE
    val estadoCivil = "Si"

    when(estadoCivil){
        "C" -> println("Esta casado")
        "S" -> println("Esta soltero")
        else -> println("No sabemos")
    }
    // IF COMO UNA SOLA EXPRESION
    val esSoltero = true
    val coqueteo = if(esSoltero) "Si" else "No" // if-else corto
    println(coqueteo)
    // FUNCIONES
    imprimirNombre("Anthony")
    // Funciones - extras
    calcularSueldo(10.00) // solo un parametro requerido
    calcularSueldo(10.00,15.00,20.00) // parametro requerido y sobreescribir parametros opcionales
    //NAMED PARAMTERS
    // calcular sueldo (sueldo, tasa, bono especial)
    calcularSueldo(10.00, bonoEspecial = 20.00)

    calcularSueldo(bonoEspecial = 20.00, sueldo = 10.00, tasa = 14.00)
    // Gracias a NamedParameters se puede poner los parametros de cualquier orden, ademas se tendria que poner todos los parametros de manera obligatoria de caso contrario seria mucho mas complicado de mantener

}

// FUNCIONES

fun imprimirNombre(nombre:String): Unit{
    // no existe void -> aqui es unit
    fun otraFuncionAdentro(){
        println("Otra funcion dentro de una funcion")
    }
    println("Nombre $nombre")
    println("Nombre: ${nombre}")
    println("Nombre: ${nombre + nombre}")
    println("Nombre: ${nombre.toString()}")
}
// MAS FUNCIONES


fun calcularSueldo(sueldo: Double, tasa: Double=12.00, bonoEspecial: Double? = null):Double{
    // Varible? -> "?" Es un Null
    if(bonoEspecial == null){
        return sueldo * (100/tasa)
    }else{
        return sueldo * (100/tasa) * bonoEspecial
    }
}

class Suma(unoParametro: Int, dosParametro: Int,):Numeros(unoParametro,dosParametro){

}