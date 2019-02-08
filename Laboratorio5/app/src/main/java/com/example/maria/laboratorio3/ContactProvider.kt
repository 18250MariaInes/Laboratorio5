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

import android.net.Uri
import android.text.TextUtils
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
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE_QUERY=("CREATE TABLE $NOMBRE_TABLA($ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, $NOMBRE TEXT,$TELEFONO TEXT,$CORREO TEXT)")
        db!!.execSQL(CREATE_TABLE_QUERY);

    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $NOMBRE_TABLA")
        onCreate(db!!)
    }
    //FUNCIONES PARA CRUD
    val todoscontactos:List<contact>
        get(){
            val first=ArrayList<contact>()
            val selectedQuery="SELECT * FROM $NOMBRE_TABLA"
            val db=this.writableDatabase
            val cursor=db.rawQuery(selectedQuery, null)
            if (cursor.moveToFirst()){
                do{
                    val contact=contact()
                    contact.id=cursor.getInt(cursor.getColumnIndex(ID))
                    contact.nombre=cursor.getString(cursor.getColumnIndex(NOMBRE))
                    contact.telefono=cursor.getString(cursor.getColumnIndex(TELEFONO))
                    contact.correo=cursor.getString(cursor.getColumnIndex(CORREO))

                    first.add(contact)
                }while (cursor.moveToNext())
            }
            db.close()
            return first
        }

    fun addContacto(contact: contact){
        val db=this.writableDatabase
        val values=ContentValues()
        //values.put(ID, contact.id)
        values.put(NOMBRE, contact.nombre)
        values.put(TELEFONO, contact.telefono)
        values.put(CORREO, contact.correo)

        db.insert(NOMBRE_TABLA, null, values)
        db.close()
    }

    fun editarContacto(contact: contact):Int{
        val db=this.writableDatabase
        val values=ContentValues()
        values.put(ID, contact.id)
        values.put(NOMBRE, contact.nombre)
        values.put(TELEFONO, contact.telefono)
        values.put(CORREO, contact.correo)

        return db.update(NOMBRE_TABLA, values,"$ID=?", arrayOf(contact.id.toString()))
    }

    fun borrarContacto(contact: contact){
        val db=this.writableDatabase
        db.delete(NOMBRE_TABLA, "$ID=?", arrayOf(contact.id.toString()))
        db.close()
    }
}