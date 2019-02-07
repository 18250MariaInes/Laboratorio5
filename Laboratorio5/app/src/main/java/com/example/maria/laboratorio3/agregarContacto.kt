package com.example.maria.laboratorio3

import android.content.ContentValues
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

//clase encargada de agregar contactos al array
class agregarContacto : AppCompatActivity() {

    lateinit var  txtcorreo: EditText
    lateinit var txtnumero: EditText
    lateinit var txtnombre: EditText
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_contacto)
        /*val myapp: myapplication = applicationContext as myapplication
        txtcorreo = findViewById(R.id.correo2)
        txtnombre=findViewById(R.id.editNombre)
        txtnumero=findViewById(R.id.telefono)
        myapp.add(contact(txtnombre.text.toString(), txtnumero.text.toString(), txtcorreo.text.toString()))
*/

    }//se crea un nuevo vontacto al leer los inputs del usuario
    fun agregarContacto (view: View){
        //val myapp: myapplication = applicationContext as myapplication
        val values=ContentValues()
        txtcorreo = this.findViewById(R.id.correo2)
        txtnombre=findViewById(R.id.editNombre)
        txtnumero=findViewById(R.id.cellphone)
        values.put(ContactProvider.NAME,txtnombre.toString())
        values.put(ContactProvider.EMAIL,txtcorreo.toString())
        values.put(ContactProvider.NUMBER,txtnumero.toString())
        val uri = contentResolver.insert(ContactProvider.CONTENT_URI, values)
        Toast.makeText(baseContext, uri!!.toString(), Toast.LENGTH_LONG).show()
        /*myapp.add(contact(txtnombre.text.toString(), txtnumero.text.toString(), txtcorreo.text.toString()))*/
        Toast.makeText(applicationContext, "Se ha creado nuevo contacto", Toast.LENGTH_LONG).show()
    }//boton para regresar a menu
    fun regresarmenu (view: View){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
