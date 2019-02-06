package com.example.maria.laboratorio3

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
//clase que hereda de broadcast receiver
class MyReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.action)) {
            Toast.makeText(context, "Se ha mandado el correo exitosamente.", Toast.LENGTH_LONG).show()
        }
    }
}