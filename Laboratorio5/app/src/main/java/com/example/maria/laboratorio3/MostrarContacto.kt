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
import android.net.Uri
import android.widget.Toast
import java.util.jar.Manifest


class MostrarContacto : AppCompatActivity() {

    lateinit var  txtcorreo: TextView
    lateinit var txtnumero: TextView
    lateinit var txtnombre: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar_contacto)
        //setter de los texviews que despliegan la informacion del contacto
        val myapp: myapplication = applicationContext as myapplication
        val nombre= myapp.array[myapp.getUsuarioSelec()].getnombre()
        val correo=myapp.array[myapp.getUsuarioSelec()].getcorreo()
        val numero=myapp.array[myapp.getUsuarioSelec()].gettelefono()
        txtcorreo = findViewById(R.id.correoSelected)
        txtnombre=findViewById(R.id.NombreSelected)
        txtnumero=findViewById(R.id.telefonoSelected)
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
            //intent.putExtra(Intent.ACTION_SENDTO, correo)
            intent.putExtra(Intent.EXTRA_SUBJECT, "Prueba Android")
            intent.putExtra(Intent.EXTRA_TEXT, "Mi nombre es Maria Ines y mi numero es 30246448")

            startActivity(Intent.createChooser(intent, "Send Email"))
            //Toast.makeText(applicationContext, "Éxito", Toast.LENGTH_LONG).show()
            intent.setAction("com.MariaLab3.CUSTOM_INTENT");
            sendBroadcast(intent)

        }

        /*TextView nombre = (TextView) findViewById(R.id.)
        helloTextView.setText(R.string.user_greeting)*/
    }
//boton para regresar a menu
    fun regresarmenu (view: View){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
