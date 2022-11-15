package de.hsos.driverhelp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import android.widget.Toast.LENGTH_SHORT
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import de.hsos.driverhelp.data.Mileage


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Navigation App Bar einbauen
        findViewById<View>(R.id.fragmentContainerView).post {
            val navCtr = findNavController(R.id.fragmentContainerView)
            NavigationUI.setupActionBarWithNavController(this, navCtr)
        }

    }
    override fun onSupportNavigateUp(): Boolean {
        val navCtr = findNavController(R.id.fragmentContainerView)
        return navCtr.navigateUp()
    }
}