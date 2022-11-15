package de.hsos.driverhelp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import de.hsos.driverhelp.data.Mileage

import kotlinx.android.synthetic.main.activity_edit_data.*

// Klasse zum Editieren von Dateneinträgen.
class EditMileageActivity : AppCompatActivity() {
    var mileage: Mileage? = null

    companion object { const val EXTRA_MILEAGE = "de.hsos.driverhelp.MILEAGE" }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_data)

        mileage = intent.getParcelableExtra(EditMileageActivity.EXTRA_MILEAGE)
        mileage?.let {
            ed_distance.setText(it.distance.toString())
            ed_fuel.setText(it.fuel.toString())
            ed_info.setText(it.info)
        } ?: kotlin.run {  }

        button_save_data.setOnClickListener {
            val replyIntent = Intent()
            if (ed_distance.text.isNotEmpty() && ed_fuel.text.isNotEmpty() && ed_info.text.isNotEmpty()) {
                mileage?.let {
                    val mileage = Mileage(mileageId = it.mileageId,
                        distance = ed_distance.text.toString().toInt(),
                        fuel = ed_fuel.text.toString().toDouble(),
                        info = ed_info.text.toString()
                    )
                    replyIntent.putExtra (EditMileageActivity.EXTRA_MILEAGE, mileage)
                } ?: kotlin.run {
                    val mileage = Mileage(
                        distance = ed_distance.text.toString().toInt(),
                        fuel = ed_fuel.text.toString().toDouble(),
                        info = ed_info.text.toString()
                    )
                    replyIntent.putExtra (EditMileageActivity.EXTRA_MILEAGE, mileage)
                }
                setResult(Activity.RESULT_OK, replyIntent)
            } else {
                Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show()
                setResult(Activity.RESULT_CANCELED, replyIntent)
            }
            finish()
        }
    }
}