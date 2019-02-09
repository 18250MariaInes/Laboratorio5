package com.example.maria.laboratorio3

import java.util.HashMap

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.Context
import android.content.UriMatcher

import android.database.Cursor
import android.database.SQLException

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteQueryBuilder
import android.graphics.Bitmap
import android.graphics.BitmapFactory

import android.net.Uri
import android.text.TextUtils
import java.io.ByteArrayOutputStream
//clase que administra la base de datos de contactos
class ContactProvider (context: Context): SQLiteOpenHelper(context, BASE_DATOS_NOMBRE, null,BASEDATOS_VER){
    companion object {
        private val BASEDATOS_VER=1
        private val BASE_DATOS_NOMBRE="Contactos"

        //organizacion de la table
        private val NOMBRE_TABLA="contacto"
        private val ID = "Id"
        private val NOMBRE="nombre"
        private val TELEFONO="telefono"
        private val CORREO="correo"
        private val IMAGEN ="imagen"
    }
//crea base datos
    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE_QUERY=("CREATE TABLE $NOMBRE_TABLA($ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, $NOMBRE TEXT,$TELEFONO TEXT,$CORREO TEXT, $IMAGEN BITMAP)")
        db!!.execSQL(CREATE_TABLE_QUERY);
    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $NOMBRE_TABLA")
        onCreate(db)
    }
    //FUNCIONES PARA CRUD
    //retorna todos los contactos en ls DB para mostrar en main activuty
    val todoscontactos:List<contact>
        get(){
            val first=ArrayList<contact>()
            val selectedQuery="SELECT * FROM $NOMBRE_TABLA"
            val db=this.writableDatabase
            val cursor=db.rawQuery(selectedQuery, null)
            if (cursor.moveToFirst()){
                do{
                    val blob = cursor.getBlob(cursor.getColumnIndex(IMAGEN))
                    val bitmap =  BitmapFactory.decodeByteArray(blob, 0,blob.size)
                    val contact=contact(cursor.getString(cursor.getColumnIndex(NOMBRE)),cursor.getString(cursor.getColumnIndex(CORREO)),cursor.getString(cursor.getColumnIndex(TELEFONO)),
                         bitmap )
                    contact.id=cursor.getInt(cursor.getColumnIndex(ID))
                    first.add(contact)
                }while (cursor.moveToNext())
            }
            db.close()
            return first
        }
//metodo que agrega contacto en base d datos con todos sus campos
    fun addContacto(contact: contact){
        var bitmap = contact.image
        val stream = ByteArrayOutputStream()
        bitmap!!.compress(Bitmap.CompressFormat.PNG, 0,stream)
        val blob = stream.toByteArray()
        val db=this.writableDatabase
        val values=ContentValues()
        //values.put(ID, contact.id)
        values.put(NOMBRE, contact.nombre)
        values.put(TELEFONO, contact.telefono)
        values.put(CORREO, contact.correo)
        values.put(IMAGEN, blob)
        db.insert(NOMBRE_TABLA, null, values)
        db.close()
    }
//metodo que permite editar la informacion de los contactos
    fun editarContacto(contact: contact):Int{
        var bitmap = contact.image
        val stream = ByteArrayOutputStream()
        bitmap!!.compress(Bitmap.CompressFormat.PNG, 0,stream)
        val blob = stream.toByteArray()
        val db=this.writableDatabase
        val values=ContentValues()
        values.put(ID, contact.id)
        values.put(NOMBRE, contact.nombre)
        values.put(TELEFONO, contact.telefono)
        values.put(CORREO, contact.correo)
        values.put(IMAGEN, blob)
        return db.update(NOMBRE_TABLA, values,"$ID=?", arrayOf(contact.id.toString()))
    }
//metodo que borra contacto de base de datos
    fun borrarContacto(contact: contact){
        val db=this.writableDatabase
        db.delete(NOMBRE_TABLA, "$ID=?", arrayOf(contact.id.toString()))
        db.close()
    }
}