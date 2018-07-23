package com.example.dennis.examen1moviles

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    lateinit var peliculas: ArrayList<Pelicula>
    var idActor: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        idActor = intent.getIntExtra("idActor", 0)
        peliculas = DataBasePelicula.getPeliculasList(idActor)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val zoom = 17f
        // Add a marker in Sydney and move the camera
        peliculas.forEach{ pelicula: Pelicula ->
            val temp = LatLng(pelicula.latitud, pelicula.longitud)
            mMap.addMarker(MarkerOptions().position(temp).title(pelicula.nombre))
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(temp, zoom))
        }

    }
}
