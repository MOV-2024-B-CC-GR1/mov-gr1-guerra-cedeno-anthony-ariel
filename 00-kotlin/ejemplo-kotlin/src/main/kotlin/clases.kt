package org.example

fun main(){}

// JAVA
abstract class NumerosJava{
    protected val numeroUno: Int
    protected val numeroDos: Int

    constructor(
        uno: Int,
        dos: Int
    ){
        this.numeroUno = uno
        this.numeroDos = dos
        println("Inicializando")
    }
}
//KOTLIN
abstract class Numeros(
    protected val numeroUno: Int,
    protected val numeroDos: Int,
    parametroNoUsadoNoPropiedadDeLaClase: Int? = null){
    init {
        this.numeroUno
        this.numeroDos
        println("Inicializado")
    }
}