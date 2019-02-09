package com.example.maria.laboratorio3

import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
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

//clase encargada de agregar contactos a la base de datos
class agregarContacto : AppCompatActivity() {

    lateinit var  txtcorreo: EditText
    lateinit var txtnumero: EditText
    lateinit var txtnombre: EditText
    internal lateinit var db:ContactProvider
    internal var first:List<contact> = ArrayList<contact>()
    lateinit var  img :Bitmap
    var imagenpresente = false
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_contacto)
        db= ContactProvider(this)
//metodo para seleccionar la imagen
        imageView.setOnClickListener {
            val PICK_PHOTO_CODE = 1046
            val intent = Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            )
            if (intent.resolveActivity(packageManager) != null) {
                // Bring up gallery to select a photo
                startActivityForResult(intent, PICK_PHOTO_CODE)
            }
        }
    }

    //se crea un nuevo contacto al leer los inputs del usuario y se guarda en base de datos
    fun agregarContacto (view: View){
        txtcorreo = this.findViewById(R.id.correo2)
        txtnombre=findViewById(R.id.editNombre)
        txtnumero=findViewById(R.id.cellphone)
        if (imagenpresente) {
            val x:BitmapDrawable = imageView.drawable as BitmapDrawable
            val bitmap:Bitmap = x.bitmap //(imageView.drawable as BitmapDrawable)
            val contacto = contact(
                txtnombre.text.toString(),
                txtcorreo.text.toString(),
                txtnumero.text.toString(),
                img
            )
            db.addContacto(contacto)
            Toast.makeText(applicationContext, "Se ha creado nuevo contacto", Toast.LENGTH_LONG).show()
        }
    }
    //boton para regresar a menu
    fun regresarmenu (view: View){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
    //metodo que se acciona al escoger una imagen y la agrega al imageview
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val photoUri = data?.data
        if (photoUri!=null) {
            val selectedImage = MediaStore.Images.Media.getBitmap(this.contentResolver, photoUri)
            img = selectedImage
            imageView.setImageBitmap(img)
            imagenpresente = true
        }
    }
}
