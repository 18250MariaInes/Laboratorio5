package com.example.maria.laboratorio3

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
//clase y actividad principal donde se visualizan todos los contactos
class MainActivity : AppCompatActivity() {
    val contactos = arrayListOf<contact>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val myapp: myapplication = applicationContext as myapplication
        contactos.addAll(myapp.array)
//adapter del array de contactos
        val adapter = ArrayAdapter(this,
            R.layout.listview, contactos)
        val listView: ListView = this.findViewById(R.id.listview_1)
        listView.setAdapter(adapter)
        val context = this
        //al seleccionar un contacto se despliega su informacion
        listView.setOnItemClickListener { _, _, position, _ ->
            //val contactoSeleccionado = contactos[position]
            myapp.setUsarioselec(position)
            val ContactInfo = Intent(applicationContext, MostrarContacto::class.java)
            startActivity( ContactInfo)
        }
    }//boton para agregar contactos
    fun agregarContacto (view: View){
        val intent = Intent(this, agregarContacto::class.java)
        startActivity(intent)
        //finish()
    }


}
