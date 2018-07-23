package com.example.dennis.examen1moviles

import android.os.Parcel
import android.os.Parcelable

class Pelicula(var id: Int,
            var isbn: String,
            var nombre: String,
            var numeroPaginas: Int,
            var edicion: Int,
            var fechaPublicacion: String,
            var nombreEditorial: String,
            var latitud: Double,
            var longitud: Double,
            var actorID: Int,
            var createdAt: Long,
            var updatedAt: Long) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readDouble(),
            parcel.readDouble(),
            parcel.readInt(),
            parcel.readLong(),
            parcel.readLong()) {
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(destino: Parcel?, p1: Int) {
        destino?.writeInt(id)
        destino?.writeString(isbn)
        destino?.writeString(nombre)
        destino?.writeInt(numeroPaginas)
        destino?.writeInt(edicion)
        destino?.writeString(fechaPublicacion)
        destino?.writeString(nombreEditorial)
        destino?.writeDouble(latitud)
        destino?.writeDouble(longitud)
        destino?.writeInt(actorID)
        destino?.writeLong(createdAt)
        destino?.writeLong(updatedAt)
    }

    companion object CREATOR : Parcelable.Creator<Pelicula> {
        override fun createFromParcel(parcel: Parcel): Pelicula {
            return Pelicula(parcel)
        }

        override fun newArray(size: Int): Array<Pelicula?> {
            return arrayOfNulls(size)
        }
    }

}