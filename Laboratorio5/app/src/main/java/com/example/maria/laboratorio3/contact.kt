package com.example.maria.laboratorio3

import android.graphics.Bitmap

//clase de tipo contac para los objetos instanciado en la app

class contact {
    //atributos que deben poseer
     var id:Int=0
     var nombre:String = ""
     var telefono: String?=null
     var correo:String = ""
     //lateinit var image: Bitmap

    constructor(){}
    constructor(nombre: String, correo: String, telefono: String) {
        this.nombre=nombre
        this.correo=correo
        this.telefono=telefono
        /*this.image=image*/

    }
    override fun toString():String{
        return this.nombre
    }



}