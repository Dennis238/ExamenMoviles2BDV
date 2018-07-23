package com.example.dennis.examen1moviles

import android.util.Log
import java.io.StringReader
import android.os.StrictMode
import com.beust.klaxon.*
import com.github.kittinunf.fuel.*


class DataBaseActor {

    companion object {

        fun insertarActor(actor: Actor) {
            "http://192.168.100.159:1337/Actor".httpPost(listOf("nombre" to actor.nombre, "apellido" to actor.apellido, "fechaNacimiento" to actor.fechaNacimiento, "numeroPeliculas" to actor.numeroPeliculas, "ecuatoriano" to actor.ecuatoriano))
                    .responseString { request, _, result ->
                        Log.d("http-ejemplo", request.toString())
                    }
        }

        fun updateActor(actor: Actor) {
            "http://192.168.100.159:1337/Actor/${actor.id}".httpPatch(listOf("nombre" to actor.nombre, "apellido" to actor.apellido, "fechaNacimiento" to actor.fechaNacimiento, "numeroPeliculas" to actor.numeroPeliculas, "ecuatoriano" to actor.ecuatoriano))
                    .responseString { request, _, result ->
                        Log.d("http-ejemplo", request.toString())
                    }
        }

        fun deleteActor(id: Int) {
            "http://192.168.100.159:1337/Actor/$id".httpDelete()
                    .responseString { request, response, result ->
                        Log.d("http-ejemplo", request.toString())
                    }
        }

        fun getList(): ArrayList<Actor> {
            val actores: ArrayList<Actor> = ArrayList()
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
            val (request, response, result) = "http://192.168.100.159:1337/Actor".httpGet().responseString()
            val jsonStringActor = result.get()

            val parser = Parser()
            val stringBuilder = StringBuilder(jsonStringActor)
            val array = parser.parse(stringBuilder) as JsonArray<JsonObject>

            array.forEach {
                val id = it["id"] as Int
                val nombre = it["nombre"] as String
                val apellido = it["apellido"] as String
                val fechaNacimiento = it["fechaNacimiento"] as String
                val numeroPeliculas = it["numeroPeliculas"] as Int
                val ecuatoriano = it["ecuatoriano"] as Int
                val actor = Actor(id, nombre, apellido, fechaNacimiento, numeroPeliculas, ecuatoriano, 0, 0)
                actores.add(actor)
            }
            return actores
        }

    }

}
