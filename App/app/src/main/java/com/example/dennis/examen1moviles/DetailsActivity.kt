package com.example.dennis.examen1moviles

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    var autor: Actor? = null
    lateinit var adaptador: PeliculaAdapter
    lateinit var peliculas: ArrayList<Pelicula>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        autor = intent.getParcelableExtra("autor")

        txtShowNombreActor.text = autor?.nombre
        txtShowApellidoActor.text = autor?.apellido
        txtShowFechaNActor.text = autor?.fechaNacimiento
        txtShowNumLibActor.text = autor?.numeroPeliculas.toString()
        txtShowEcuActor.text = if(autor?.ecuatoriano == 1) getString(R.string.yes) else getString(R.string.no)

        peliculas = DataBasePelicula.getPeliculasList(autor?.id!!)

        val layoutManager = LinearLayoutManager(this)
        adaptador = PeliculaAdapter(peliculas)
        recycler_view_book.layoutManager = layoutManager
        recycler_view_book.itemAnimator = DefaultItemAnimator()
        recycler_view_book.adapter = adaptador
        adaptador.notifyDataSetChanged()

        registerForContextMenu(recycler_view_book)

        btnNuevoPelicula.setOnClickListener{
            v: View? ->  crearPelicula()
        }

        btnMapa.setOnClickListener { view: View ->
            irAActividadGoogleMaps()
        }
    }

    fun crearPelicula() {
        val intent = Intent(this, CreateMovieActivity::class.java)
        intent.putExtra("tipo", "Create")
        intent.putExtra("idActor", autor?.id!!)
        startActivity(intent)
    }

    fun irAActividadGoogleMaps() {
        val intent = Intent(this, MapsActivity::class.java)
        intent.putExtra("idActor", autor?.id!!)
        startActivity(intent)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        var position = adaptador.getPosition()
        var pelicula = peliculas[position]

        when (item.itemId) {
            R.id.item_menu_compartir -> {
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "text/html"
                intent.putExtra(Intent.EXTRA_SUBJECT, "${getString(R.string.pelicula)} - ${getString(R.string.app_name)}")
                intent.putExtra(Intent.EXTRA_TEXT, "${getString(R.string.isbn)} ${pelicula.isbn}\n${getString(R.string.name)} ${pelicula.nombre}\n${getString(R.string.edicion)} ${pelicula.edicion}\n${getString(R.string.editorial)} ${pelicula.nombreEditorial}")
                startActivity(intent)
                return true
            }
            R.id.item_menu_editar -> {
                val intent = Intent(this, CreateMovieActivity::class.java)
                intent.putExtra("tipo", "Edit")
                intent.putExtra("pelicula", pelicula)
                startActivity(intent)
                return true
            }
            R.id.item_menu_borrar -> {
                val builder = AlertDialog.Builder(this)
                builder.setMessage(R.string.confirmation)
                        .setPositiveButton(R.string.yes, { dialog, which ->
                            DataBasePelicula.deletePelicula(pelicula.id)
                            finish()
                            startActivity(intent)
                        }
                        )
                        .setNegativeButton(R.string.no, null)
                val dialogo = builder.create()
                dialogo.show()
                return true
            }
            else -> {
                Log.i("menu", "Todos los demas")
                return super.onOptionsItemSelected(item)
            }
        }
    }
}
