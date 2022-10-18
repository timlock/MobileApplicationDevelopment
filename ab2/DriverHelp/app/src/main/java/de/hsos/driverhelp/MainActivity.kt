package de.hsos.driverhelp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import android.widget.Toast.LENGTH_SHORT

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button: Button = findViewById<Button>(R.id.button) as Button;
        val startInput: EditText = findViewById<EditText>(R.id.startInput) as EditText
        val stopInput: EditText = findViewById<EditText>(R.id.stopInput) as EditText
        val tankInput: EditText = findViewById<EditText>(R.id.tankenInput) as EditText
        val kostenInput: EditText = findViewById<EditText>(R.id.preisInput) as EditText
        val resultBox: TextView = findViewById<TextView>(R.id.resultBox) as TextView
        button.setOnClickListener {
            var distance = -1.0
            var fuel = -1.0
            var bill = -1.0
            try {
                val start = startInput.text.toString().toDouble()
                val stop = stopInput.text.toString().toDouble()
                distance = stop - start
                fuel = tankInput.text.toString().toDouble()
                bill = tankInput.text.toString().toDouble()
                if (distance > 0.0 && fuel > 0.0 && bill > 0.0) {
                    val consumption = fuel / distance * 100
                    val consumption_formatted = "%.2f".format(consumption)
                    val cost_per_100_formatted = "%.2f".format(bill / fuel * consumption)
                    var msg: String = "Verbrauch: $consumption_formatted l pro 100 km.\n"
                    val switch = findViewById (R.id.relativPreisSwitch) as Switch
                    if (switch.isChecked()) msg += "Kosten: $cost_per_100_formatted € pro 100 km."
                    resultBox.setText(msg)
                }

            } catch (e: Exception) { // Option 1 im Fehlerfall: Nutzung des Ausgabefeldes für Warnung.
                resultBox.setText("Geben Sie bitte Start und Stop an!") // Option 2 im Fehlerfall: Nutzung eines Toasts.
                Toast.makeText(
                    this@MainActivity,
                    "Geben Sie bitte Start und Stop an!",
                    LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
        }
    }
}
