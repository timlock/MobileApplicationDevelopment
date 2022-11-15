package de.hsos.driverhelp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.webkit.WebView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class StationFindingActivity : AppCompatActivity() {
    // Standard-Orte, damit in der App schon etwas zur Verfügung steht.
    val defaultLocations = arrayListOf<String>(
        "Osnabrück",
        "Hannover",
        "Oldenburg",
        "Melle",
        "Wallenhorst",
        "Rheine",
        "Georgsmarienhütte"
    )

    // Gespeicherte Liste mit Orten
    private var locations = arrayListOf<String>()

    // Aktueller Eintrag
    private var location: String = ""

    // Der Adapter zeigt die Liste mit Orten an.
    var adapter: ArrayAdapter<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_station_finding)
        val myWebView: WebView = findViewById(R.id.webView)
        adapter = ArrayAdapter<String>(
            this,
            android.R.layout.select_dialog_item,
            loadDataFromSharedPreferences()
        )
        val locationText = findViewById<View>(R.id.locationText) as AutoCompleteTextView
        locationText.threshold = 2
        locationText.setAdapter(adapter)
        locationText.doOnTextChanged { text, start, before, count ->
            location = text.toString()
        }
        // Eingabefeld initialisieren mit erstem Eintrag.
        locationText.setText("")
        val btnSubmit = findViewById<View>(R.id.btnSubmit) as Button
        btnSubmit.setOnClickListener {
            val uri =
                Uri.parse(
                    "https://www.google.de/maps/search/Tankstellen+" +
                            location
                )
            /* zu implementieren */
            handleHistory()
            myWebView.loadUrl(uri.toString())
            //val intent: Intent = Intent(Intent.ACTION_VIEW, uri)
            //startActivity(intent)
        }
    }

    private fun handleHistory() {
        if (!locations.contains(location)) {
            locations.add(location)
            saveDataToSharedPreferences()
        }
    }

    fun loadDataFromSharedPreferences(): ArrayList<String> {
        val sharedPreferences = getSharedPreferences(
            "shared preferences",
            MODE_PRIVATE
        )
        // Die Einträge werden im JSON-Format in Dateien gespeichert.
        val gson = Gson()
        val json = sharedPreferences.getString("locations", null)
        // Konvertierung in eine Liste mit Strings.
        val type: Type = object : TypeToken<ArrayList<String?>?>() {}.type
        val saved_locations = gson.fromJson<Any>(json, type)
        // Gibt es schon gespeicherte Einträge?
        if (saved_locations == null) {
            locations = defaultLocations.clone() as ArrayList<String>
        } else {
            locations = saved_locations as ArrayList<String>
        }
        return locations
    }

    fun saveDataToSharedPreferences() {
        val sharedPreferences = getSharedPreferences(
            "shared preferences",
            MODE_PRIVATE
        )
        // Editor erzeugen.
        val editor = sharedPreferences.edit()
        // Erzeugen einer Liste.
        val gson = Gson()
        val json = gson.toJson(locations)
        // Daten speichern in den Shared Prefs.
        editor.putString("locations", json)
        editor.apply()
        // Der Toast mit Info erscheint immer unten im UI.
        Toast.makeText(
            applicationContext,
            "Sucheinstellungen wurden gespeichert.",
            Toast.LENGTH_SHORT
        ).apply { setGravity(Gravity.CENTER, 0, 0) }.show()
    }
}