package com.example.dennis.examen1moviles

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Switch
import kotlinx.android.synthetic.main.activity_create.*

class CreateActivity : AppCompatActivity() {

    var actor: Actor? = null
    var tipo = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        val type = intent.getStringExtra("tipo")

        if (type.equals("Edit")) {
            textViewActor.text = getString(R.string.edit_actor)
            actor = intent.getParcelableExtra("actor")
            fillFields()
            tipo = true
        }

        btnCrearActor.setOnClickListener{
            v: View? -> crearActor()
        }
    }

    fun fillFields() {
        txtNombreActor.setText(actor?.nombre)
        txtApellidoActor.setText(actor?.apellido)
        txtFechaActor.setText(actor?.fechaNacimiento)
        txtNumeroPeliculasActor.setText(actor?.numeroPeliculas.toString())
        if (actor?.ecuatoriano == 1) {
            switchEcActor.toggle()
        }
    }

    fun crearActor() {
        var nombre = txtNombreActor.text.toString()
        var apellido = txtApellidoActor.text.toString()
        var fecha = txtFechaActor.text.toString()
        var numeroPeliculas = txtNumeroPeliculasActor.text.toString().toInt()
        var ecutoriano = if (switchEcActor.isChecked) 1 else 0

        if (!tipo) {
            var actor = Actor(0, nombre, apellido, fecha, numeroPeliculas, ecutoriano, 0, 0)
            DataBaseActor.insertarActor(actor)
        } else {
            var actor = Actor(actor?.id!!, nombre, apellido, fecha, numeroPeliculas, ecutoriano, 0, 0)
            DataBaseActor.updateActor(actor)
        }

        irAListView()
    }

    fun irAListView() {
        val intent = Intent(this, ListActivity::class.java)
        startActivity(intent)
    }
}
