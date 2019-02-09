package com.example.maria.laboratorio3

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.maria.laboratorio3.R.id.textView
import android.content.pm.PackageManager
import android.Manifest.permission
import android.Manifest.permission.CALL_PHONE
import android.support.v4.app.ActivityCompat
import android.R.attr.button
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.support.v4.content.ContextCompat
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import com.example.maria.laboratorio3.R.id.image
import kotlinx.android.synthetic.main.activity_agregar_contacto.*
import kotlinx.android.synthetic.main.activity_mostrar_contacto.*
import java.util.jar.Manifest

//metodo que se encarga de mostrar el contacto seleccionado por el usuario
class MostrarContacto : AppCompatActivity() {

    lateinit var  txtcorreo: TextView
    lateinit var txtnumero: TextView
    lateinit var txtnombre: TextView
    lateinit var imagen:ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        val myapp:myapplication=applicationContext as myapplication
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar_contacto)
        val nombre=intent.extras.getString("nombre")
        val correo=intent.extras.getString("correo")
        val numero=intent.extras.getString("telefono")

        txtcorreo = findViewById(R.id.correoSelected)
        txtnombre=findViewById(R.id.NombreSelected)
        txtnumero=findViewById(R.id.telefonoSelected)
//muestra imagen
        imageViewMostrar.setImageBitmap(myapp.getcontactSelec().image)
        txtnombre.setText(nombre)
        txtcorreo.setText(correo)
        txtnumero.setText(numero)

//al hacer click en el numero de telefono se puede redirige a llamarlo
        txtnumero.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:"+numero)
            startActivity(intent)
        }
        //al hacer click en el correo se redirige a mandar un correo por medio de un broadcast receiver
        txtcorreo.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.type = "text/html"
            intent.data=Uri.parse("mailto:"+correo)
            intent.putExtra(Intent.EXTRA_EMAIL,correo)
            intent.putExtra(Intent.EXTRA_SUBJECT, "Prueba Android")
            intent.putExtra(Intent.EXTRA_TEXT, "Mi nombre es Maria Ines y mi numero es 30246448")

            startActivity(Intent.createChooser(intent, "Send Email"))
            intent.setAction("com.MariaLab3.CUSTOM_INTENT");
            sendBroadcast(intent)

        }

    }
//boton para regresar a menu
    fun regresarmenu (view: View){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
//boton para editar un contacto
    fun editarContacto(view: View){
        val intent=Intent(this, EditarContacto::class.java)
        startActivity(intent)

        startActivity(intent)
    }
}
