package de.hsos.driverhelp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.observe

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    // CRUD Operationen finden in einer separaten Activity statt.
    // Die Replies dieser Activity (die prinzipiell auch von
    // anderen Komponenten aufgerufen werden kann) werden über den Request-Code
    // dieser MainActivity zugeordnet.
    private val editMileageActivityRequestCode = 1

    // Über das ViewModel kann die Synchronisation der Anzeigen
    // an verschiedenen Stellen des UI erfolgen.
    private val mileageViewModel: MileageViewModel by viewModels {
        MileageViewModelFactory((application as MileageApplication).repository)
    }

    // Adapter für die Liste mit Verbrauchsdaten
    var adapter : MileageListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Zur Anzeige der Liste wird ein RecyclerView verwendet.
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview_mileages)
        adapter = MileageListAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // Floating Button zum Einfügen von Daten.
        floatingActionButton.setOnClickListener {
            val intent = Intent(this@MainActivity, EditMileageActivity::class.java)
            startActivityForResult(intent, editMileageActivityRequestCode)
        }

        // Wir beobachten Änderungen am ViewModel. Da die Liste als LiveData
        // gekennzeichnet ist, können diese Änderungen hier verfolgt werden.
        // Wenn sich die Daten ändern und die Activity im Vordergrund ist,
        // wird die Liste im Adapter angepasst.
        mileageViewModel.allMileages.observe(owner = this) { mileages ->
            // Update the cached copy of the words in the adapter.
            mileages.let { adapter?.setMileages(it) }
        }

        // Die Einträge der Liste werden mit Click-Listenern für
        // 'update' und 'delete' versehen.
        adapter?.setOnItemClick(object : ListClickListener<Mileage> {
            override fun onClick(mileage: Mileage, position: Int) {
                val intent = Intent(this@MainActivity, EditMileageActivity::class.java)
                // requestToIntent (intent, mileage)
                intent.putExtra(EditMileageActivity.EXTRA_MILEAGE, mileage)
                startActivityForResult(intent, editMileageActivityRequestCode)
                // Hier wird das Ergebnis der Subaktivität abgewartet.
                // Die Behandlung erfolgt in onActivityResult().
            }
            override fun onDelete(mileage: Mileage) {
                mileageViewModel.delete(mileage)
                System.out.println ("LOGGER: calling delete")
            }
        })
    }

    // Wird aufgerufen, wenn Subaktivität zum Editieren beendet wird.
    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)
        if (requestCode == editMileageActivityRequestCode && resultCode == Activity.RESULT_OK) {
            val mileage: Mileage?  = intentData?.getParcelableExtra(EditMileageActivity.EXTRA_MILEAGE)
            // Der Editor erzeugt entweder einen neuen Eintrag oder hat ein existierendes Objekt editiert.
            // Das kann über den mileageId Eintrag unterschieden werden.
            mileage?.let {
                if (it.mileageId == null) {
                    // Neuen Eintrag erzeugen
                    mileageViewModel.insert(it)
                    System.out.println("LOGGER: calling insert")
                } else {
                    // Existierenden Eintrag aktualisieren
                    val tmp_mileage = Mileage(
                        mileageId = it.mileageId,
                        distance = it.distance, fuel = it.fuel, info = it.info
                    )
                    mileageViewModel.update(tmp_mileage)
                    System.out.println("LOGGER: calling update")
                }
            } ?: kotlin.run {
                Toast.makeText(applicationContext, "Keine Rückgabe von Subaktivität!",
                    Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(applicationContext, R.string.empty_not_saved,
                Toast.LENGTH_LONG).show()
        }
    }
}