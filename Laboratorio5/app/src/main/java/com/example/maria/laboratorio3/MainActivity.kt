package com.example.maria.laboratorio3

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.*
//clase y actividad principal donde se visualizan todos los contactos de la DB
class MainActivity : AppCompatActivity() {
    internal lateinit var db:ContactProvider
    lateinit var myapp:myapplication
    internal var first:List<contact> = arrayListOf()
    lateinit var  listView:ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        myapp=applicationContext as myapplication
        listView = this.findViewById(R.id.listview_1)
        db=ContactProvider(this)
        refreshData()
        //al seleccionar un contacto se despliega su informacion ya que la manda a la actividad de mostrarcontacto
        listView.setOnItemClickListener { _, _, position, _ ->
            //val contactoSeleccionado = contactos[position]

            val ContactInfo = Intent(this, MostrarContacto::class.java)
            myapp.setContactoSelec(first[position])

            ContactInfo.putExtra("nombre",first[position].nombre)
            ContactInfo.putExtra("telefono",first[position].telefono)
            ContactInfo.putExtra("correo",first[position].correo)
           // ContactInfo.putExtra("imagen",first[position].image)
            startActivity( ContactInfo)
        }
        //boton de borrado al dar un click largo
        listView.onItemLongClickListener = object: AdapterView.OnItemLongClickListener{
            override fun onItemLongClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long): Boolean {
                db.borrarContacto(first[position])
                //Se actualizan los datos de los contactos
                first = db.todoscontactos
                var adapter = ArrayAdapter(applicationContext, R.layout.listview, first)
                listView.adapter = adapter
                Toast.makeText(applicationContext, "Se ha eliminado el contacto", Toast.LENGTH_SHORT).show()
                return true
            }
        }


    }
//refresea los datos del listview cuando suceden cambios y los muestra
    private fun refreshData() {
        first=db.todoscontactos

        //Log.e("data",first.toString())
        val adapter=ArrayAdapter(this,
            R.layout.listview,first)
        listView.adapter = adapter
    }
//se va a la opcion de agregar
    fun agregarContacto (view: View){
        val intent = Intent(this, agregarContacto::class.java)
        startActivity(intent)
        //finish()
    }


}
