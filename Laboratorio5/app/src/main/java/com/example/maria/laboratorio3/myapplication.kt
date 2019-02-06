package com.example.maria.laboratorio3

import android.app.Application

class myapplication:Application() {
    //instancia de lista tipo contacto
    val array: ArrayList<contact> = arrayListOf(contact("Chris P. Beacon","1234", "bea18200@uvg.edu.gt"), contact("Juan", "59077176", "juancvs@gmail.com"))
    private var usuarioSelec:Int=0
    //array.add()
    //val contact selected

    fun setUsarioselec(num: Int){
        this.usuarioSelec=num
    }

    fun getUsuarioSelec():Int{
        return usuarioSelec
    }

    fun add(element: contact) {
        array.add(element)
    }


}