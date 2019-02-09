package com.example.maria.laboratorio3

import android.app.Application
//clase cuyo unico fin es la comunicacion entre actividades de informacion del contacto
class myapplication:Application() {
    //variable de contacto
    lateinit var contacto:contact

    //settea el contacto seleccionado
    fun setContactoSelec(contacto: contact){
        this.contacto=contacto
    }
//devuelve el contacto guardado en variable
    fun getcontactSelec():contact{
        return contacto
    }




}