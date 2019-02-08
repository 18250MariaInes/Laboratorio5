package com.example.maria.laboratorio3

import android.content.ContentValues
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
//import com.example.maria.laboratorio3.R.id.imageView
import kotlinx.android.synthetic.main.activity_agregar_contacto.*

//clase encargada de agregar contactos al array
class agregarContacto : AppCompatActivity() {

    lateinit var  txtcorreo: EditText
    lateinit var txtnumero: EditText
    lateinit var txtnombre: EditText
    //var hasImage = false
    //lateinit var txtid: EditText
    internal lateinit var db:ContactProvider
    internal var first:List<contact> = ArrayList<contact>()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_contacto)
        db= ContactProvider(this)
        /*val myapp: myapplication = applicationContext as myapplication
        txtcorreo = findViewById(R.id.correo2)
        txtnombre=findViewById(R.id.editNombre)
        txtnumero=findViewById(R.id.telefono)
        myapp.add(contact(txtnombre.text.toString(), txtnumero.text.toString(), txtcorreo.text.toString()))
*/

    }

    //se crea un nuevo vontacto al leer los inputs del usuario
    fun agregarContacto (view: View){
        //val myapp: myapplication = applicationContext as myapplication
        //val values=ContentValues()
        txtcorreo = this.findViewById(R.id.correo2)
        txtnombre=findViewById(R.id.editNombre)
        txtnumero=findViewById(R.id.cellphone)

        val contacto=contact(
            txtnombre.text.toString(),
            txtcorreo.text.toString(),
            txtnumero.text.toString()
        )
        db.addContacto(contacto)

        /*myapp.add(contact(txtnombre.text.toString(), txtnumero.text.toString(), txtcorreo.text.toString()))*/
        Toast.makeText(applicationContext, "Se ha creado nuevo contacto", Toast.LENGTH_LONG).show()
    }//boton para regresar a menu

    fun regresarmenu (view: View){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
