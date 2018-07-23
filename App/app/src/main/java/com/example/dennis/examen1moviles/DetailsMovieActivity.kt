package com.example.dennis.examen1moviles

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_details_book.*

class DetailsMovieActivity : AppCompatActivity() {

    var pelicula: Pelicula? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_book)

        pelicula = intent.getParcelableExtra("pelicula")

        txtShowIsbn.text = pelicula?.isbn
        txtShowNombrePelicula.text = pelicula?.nombre
        txtShowNumPagPelicula.text = pelicula?.numeroPaginas.toString()
        txtShowEdicPelicula.text = pelicula?.edicion.toString()
        txtShowFechaPPelicula.text = pelicula?.fechaPublicacion
        txtShowEditorialPelicula.text = pelicula?.nombreEditorial
    }
}
