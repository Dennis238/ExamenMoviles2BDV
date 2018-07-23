package com.example.dennis.examen1moviles

import android.os.Parcel
import android.os.Parcelable

class Actor(var id: Int,
            var nombre: String,
            var apellido: String,
            var fechaNacimiento: String,
            var numeroPeliculas: Int,
            var ecuatoriano: Int,
            var createdAt: Long,
            var updatedAt: Long): Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readLong(),
            parcel.readLong()) {
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(destino: Parcel?, p1: Int) {
        destino?.writeInt(id)
        destino?.writeString(nombre)
        destino?.writeString(apellido)
        destino?.writeString(fechaNacimiento)
        destino?.writeInt(numeroPeliculas)
        destino?.writeInt(ecuatoriano)
        destino?.writeLong(createdAt)
        destino?.writeLong(updatedAt)
    }

    companion object CREATOR : Parcelable.Creator<Actor> {
        override fun createFromParcel(parcel: Parcel): Actor {
            return Actor(parcel)
        }

        override fun newArray(size: Int): Array<Actor?> {
            return arrayOfNulls(size)
        }
    }

}