package com.example.maria.laboratorio3

import android.graphics.Bitmap

//clase de tipo contac para los objetos instanciado en la app con todos los campos necesarios de informacion

class contact {
    //atributos que deben poseer
     var id:Int=0
     var nombre:String = ""
     var telefono: String?=null
     var correo:String = ""
    var image: Bitmap? = null

    constructor()
    constructor(nombre: String, correo: String, telefono: String, image:Bitmap) {
        this.nombre=nombre
        this.correo=correo
        this.telefono=telefono
        this.image=image

    }
    //para imprimir su nombre
    override fun toString():String{
        return this.nombre
    }



}