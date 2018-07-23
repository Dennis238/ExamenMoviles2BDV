package com.example.dennis.examen1moviles

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_create_book.*

class CreateMovieActivity : AppCompatActivity() {

    var idActor: Int = 0
    var idPelicula: Int = 0
    var pelicula: Pelicula? = null
    var tipo = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_book)

        val type = intent.getStringExtra("tipo")

        if (type.equals("Edit")) {
            textViewPelicula.text = getString(R.string.edit_book)
            pelicula = intent.getParcelableExtra("pelicula")
            idPelicula = pelicula!!.id
            fillFields()
            tipo = true
        }

        idActor = intent.getIntExtra("idActor", 0)

        btnCrearPelicula.setOnClickListener{
            v: View? ->  crearPelicula()
        }
    }

    fun fillFields() {
        txtISBNPelicula.setText(pelicula?.isbn)
        txtNombrePelicula.setText(pelicula?.nombre)
        txtNPagPelicula.setText(pelicula?.numeroPaginas.toString())
        txtEdicionPelicula.setText(pelicula?.edicion.toString())
        txtFechaPPelicula.setText(pelicula?.fechaPublicacion)
        txtEditorialPelicula.setText(pelicula?.nombreEditorial)
    }

    fun crearPelicula() {
        var isbn = txtISBNPelicula.text.toString()
        var nombre = txtNombrePelicula.text.toString()
        var numeroPaginas = txtNPagPelicula.text.toString().toInt()
        var edicion = txtEdicionPelicula.text.toString().toInt()
        var fechaP = txtFechaPPelicula.text.toString()
        var nombreEd = txtEditorialPelicula.text.toString()
        var latitud = txtLatPelicula.text.toString().toDouble()
        var longitud = txtLongPelicula.text.toString().toDouble()
        var pelicula = Pelicula(this.idPelicula, isbn, nombre, numeroPaginas, edicion, fechaP, nombreEd, latitud, longitud, idActor, 0, 0)

        if (!tipo) {
            DataBasePelicula.insertarPelicula(pelicula)
        } else {
            DataBasePelicula.updatePelicula(pelicula)
        }

        finish()
    }
}
