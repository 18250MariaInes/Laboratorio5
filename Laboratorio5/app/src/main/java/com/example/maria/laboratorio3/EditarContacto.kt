package com.example.maria.laboratorio3

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_agregar_contacto.*
import kotlinx.android.synthetic.main.activity_editar_contacto.*
//clase que se encarga de la edicion de la informacion de los contactos en ls DB
class EditarContacto : AppCompatActivity() {
    internal lateinit var db:ContactProvider
    lateinit var myapp:myapplication
    lateinit var contacto: contact
    lateinit var  img : Bitmap
    var imagenpresente = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_contacto)
        myapp=applicationContext as myapplication
        contacto=myapp.getcontactSelec()
        //muestra los campos ya agregados para su edicion
        nombren.setText(contacto.nombre)
        telefonon.setText(contacto.telefono)
        correon.setText(contacto.correo)
        imageN.setImageBitmap(contacto.image)
        imageN.setOnClickListener {
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
    //realiza edicion y guarda cambios en DB
    fun editarcontactofinal(view: View){
        db=ContactProvider(this)
        //Log.e("data",contacto.id.toString())
        if (imagenpresente){
            val bitmap = (imageN.getDrawable() as BitmapDrawable).bitmap
            var nuevoContacto:contact=contact(nombren.text.toString(),telefonon.text.toString(), correon.text.toString(),bitmap)
            nuevoContacto.id=contacto.id
            db.editarContacto(nuevoContacto)
            Toast.makeText(this, "Se ha editado el contacto", Toast.LENGTH_SHORT).show()
            val intent: Intent = Intent(this, MainActivity::class.java)
            //regresa al main
            startActivity(intent)
            finish()
        }

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val photoUri = data?.data
        // Do something with the photo based on Uri
        if (photoUri!=null) {
            val selectedImage = MediaStore.Images.Media.getBitmap(this.contentResolver, photoUri)
            img = selectedImage
            imageN.setImageBitmap(img)
            imagenpresente = true
        }
    }
}
