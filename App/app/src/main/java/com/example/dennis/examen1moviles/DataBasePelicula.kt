package com.example.dennis.examen1moviles

import android.os.StrictMode
import android.util.Log
import com.beust.klaxon.*
import com.github.kittinunf.fuel.httpDelete
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPatch
import com.github.kittinunf.fuel.httpPost
import java.io.StringReader

class DataBasePelicula {

    companion object {
        fun insertarPelicula(pelicula: Pelicula) {
            "http://192.168.100.159:1337/Pelicula".httpPost(listOf("isbn" to pelicula.isbn, "nombre" to pelicula.nombre, "numeroPaginas" to pelicula.numeroPaginas, "edicion" to pelicula.edicion, "fechaPublicacion" to pelicula.fechaPublicacion, "nombreEditorial" to pelicula.nombreEditorial, "latitud" to pelicula.latitud, "longitud" to pelicula.longitud, "actorId" to pelicula.actorID))
                    .responseString { request, _, result ->
                        Log.d("http-ejemplo", request.toString())
                    }
        }

        fun updatePelicula(pelicula: Pelicula) {
            "http://192.168.100.159:1337/Pelicula/${pelicula.id}".httpPatch(listOf("isbn" to pelicula.isbn, "nombre" to pelicula.nombre, "numeroPaginas" to pelicula.numeroPaginas, "edicion" to pelicula.edicion, "fechaPublicacion" to pelicula.fechaPublicacion, "nombreEditorial" to pelicula.nombreEditorial, "latitud" to pelicula.latitud, "longitud" to pelicula.longitud, "actorId" to pelicula.actorID))
                    .responseString { request, _, result ->
                        Log.d("http-ejemplo", request.toString())
                    }
        }

        fun deletePelicula(id: Int) {
            "http://192.168.100.159:1337/Pelicula/$id".httpDelete()
                    .responseString { request, response, result ->
                        Log.d("http-ejemplo", request.toString())
                    }
        }

        fun getPeliculasList(idActor: Int): ArrayList<Pelicula> {
            val peliculas: ArrayList<Pelicula> = ArrayList()
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
            val (request, response, result) = "http://192.168.100.159:1337/Pelicula?actorId=$idActor".httpGet().responseString()
            val jsonStringPelicula = result.get()

            val parser = Parser()
            val stringBuilder = StringBuilder(jsonStringPelicula)
            val array = parser.parse(stringBuilder) as JsonArray<JsonObject>

            array.forEach {
                val id = it["id"] as Int
                val isbn = it["isbn"] as String
                val nombre = it["nombre"] as String
                val numeroPaginas = it["numeroPaginas"] as Int
                val edicion = it["edicion"] as Int
                val fechaPublicacion = it["fechaPublicacion"] as String
                val nombreEditorial = it["nombreEditorial"] as String
                val latitud = it["latitud"] as Double
                val longitud = it["longitud"] as Double
                val pelicula = Pelicula(id, isbn, nombre, numeroPaginas, edicion, fechaPublicacion, nombreEditorial, latitud, longitud, idActor, 0, 0)
                peliculas.add(pelicula)
            }
            return peliculas
        }
    }

}
