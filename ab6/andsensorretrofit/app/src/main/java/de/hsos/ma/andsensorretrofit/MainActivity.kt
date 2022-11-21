package de.hsos.ma.andsensorretrofit

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() , LocationListener {
    private val TAG: String = "MainActivity"
    private lateinit var locationManager: LocationManager // Manager
    private lateinit var tvGpsLocation: TextView // Anzeige der Position
    private var location: Location? = null // Letzte Position
    private val locationPermissionCode = 2 // Request Code für Permission


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewModel = ViewModelProvider(this).get(StationViewModel::class.java)
        val buttonLoad: Button = findViewById(R.id.loadStationData)
        buttonLoad.setOnClickListener {
            if (zipEditLoad.text.toString().toIntOrNull() == null) {
                Toast.makeText(this, "Bitte Zip-Feld korrigieren.", Toast.LENGTH_SHORT).show()
            } else {
                val zip = zipEditLoad.text.toString().toInt()
                // einen Eintrag auslesen
                viewModel.getStationForZip(zip.toString() + "/1") // getStation()
                viewModel.myResponse.observe(this, Observer {
                    if (it != null) {
                        // TextViews setzen
                        stationLabelLoadVal.setText(it?.name ?: "empty")
                        val position_str: String = "lat = " + (it?.latitude ?: "?") +
                                ", lon = " + (it?.longitude ?: "?")
                        positionLabelLoadVal.setText(position_str)
                        costLabelLoadVal.setText(it?.cost.toString() ?: "0.0")
                        Log.d(TAG, it?.toString() ?: "empty")
                    }
                })
            }
        }
        val button: Button = findViewById(R.id.postStationData)
        button.setOnClickListener {
            // Eingabe-Daten prüfen
            // Kostenfeld korrigieren, wenn notwendig.
            val cost_str = costEdit.text.toString().replace(",", ".")
            costEdit.setText(cost_str)
            if ((zipEdit.text.toString().toIntOrNull() == null) ||
                (costEdit.text.toString().toDoubleOrNull() == null)
            ) {
                Toast.makeText(
                    this,
                    "Bitte Eingabe korrigieren / komplettieren.",
                    Toast.LENGTH_SHORT
                ).show()
            } else
                if (location == null) {
                    Toast.makeText(
                        this,
                        "Position nicht verfügbar. Tracking eingeschaltet?",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    // Dann Daten einfügen. Achtung: location könnte null geworden sein!
                    val newStation = StationData(
                        stationEdit.text.toString(), zipEdit.text.toString().toInt(),
                        location?.latitude.toString() ?: "0.0",
                        location?.longitude.toString() ?: "0.0",
                        costEdit.text.toString().toDouble()
                    )
                    Log.d(TAG, newStation.toString())
                    viewModel.addStation(newStation)
                    // Response wird nicht ausgewertet: hier sollte die neue Station zurück kommen.
                    // Kontrolle via Log möglich.
                    viewModel.myResponse.observe(this, Observer {
                        if (it != null) {
                            Log.d(TAG, it?.toString() ?: "empty")
                        }
                    })
                }
        }
        // Behandlung des Trackings
        val trackingOnOffSwitch = findViewById<View>(R.id.trackingSwitch) as Switch
        trackingOnOffSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            Log.v(TAG, "Track? " + isChecked)
            if (isChecked)
                enableTracking()
            else {
                disableTracking()
            }
        }


    }

    private fun enableTracking() {
        if ((ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED)
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                locationPermissionCode
            )
        }
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationManager.requestLocationUpdates(
            LocationManager.GPS_PROVIDER,
            5000, 5f, this
        )
    }
    private fun disableTracking() {
        tvGpsLocation.text = "lat = ?, lon = ?"
        this.location = null
        locationManager.removeUpdates(this);
    }

    override fun onLocationChanged(location: Location) {
        tvGpsLocation = findViewById(R.id.positionLabel)
        this.location = location
        tvGpsLocation.text =
            "lat = " + this.location?.latitude + " , lon = " + this.location?.longitude
    }



    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>, grantResults: IntArray
    ): Unit {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == locationPermissionCode) {
            if (grantResults.isNotEmpty() && grantResults[0] ==
                PackageManager.PERMISSION_GRANTED
            ) {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }
}