package de.hsos.driverhelp

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import de.hsos.driverhelp.data.Mileage
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DriverHelp.newInstance] factory method to
 * create an instance of this fragment.
 */
class DriverHelp : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private val mileageViewModel: MileageViewModel by activityViewModels {
        MileageViewModelFactory((activity?.application as MileageApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_driver_help, container, false)
        val button: Button = view.findViewById<Button>(R.id.button) as Button
        val startInput: EditText = view.findViewById<EditText>(R.id.startInput) as EditText
        val stopInput: EditText = view.findViewById<EditText>(R.id.stopInput) as EditText
        val tankInput: EditText = view.findViewById<EditText>(R.id.tankenInput) as EditText
        val kostenInput: EditText = view.findViewById<EditText>(R.id.preisInput) as EditText
        val resultBox: TextView = view.findViewById<TextView>(R.id.resultBox) as TextView
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
                    val switch = view.findViewById(R.id.relativPreisSwitch) as Switch
                    if (switch.isChecked()) msg += "Kosten: $cost_per_100_formatted € pro 100 km."
                    resultBox.setText(msg)
                }

            } catch (e: Exception) { // Option 1 im Fehlerfall: Nutzung des Ausgabefeldes für Warnung.
                resultBox.setText("Geben Sie bitte Start und Stop an!") // Option 2 im Fehlerfall: Nutzung eines Toasts.
                Toast.makeText(
                    requireContext(),
                    "Geben Sie bitte Start und Stop an!",
                    Toast.LENGTH_SHORT
                ).show()
//                return@setOnClickListener
            }
            persistMileage(distance.toInt(), fuel, bill.toInt())
        }
        view.findViewById<Button>(R.id.buttonInfo)?.setOnClickListener {
            val navContr = findNavController()
            navContr.navigate(R.id.action_driverHelp_to_driverHelpInformation)
        }
        setHasOptionsMenu(true)
        return view
    }

    private fun persistMileage(distance: Int, fuel: Double, bill: Int) {
        val currentTime: Date = Calendar.getInstance().getTime()
        var totalDistance : Int = 0;
        val info: String = "$currentTime | $totalDistance.to | $bill"
        val mileage: Mileage = Mileage(null, distance, fuel, info)
        this.mileageViewModel.insert(mileage)
    }

    override fun onCreateOptionsMenu(
        menu: Menu, inflater:
        MenuInflater
    ) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navCtr = requireView().findNavController()
        System.out.println("LOGGER: " + item + " selected")
        if(item.itemId == R.id.driverHelpInformation) {
            val navContr = findNavController()
            navContr.navigate(R.id.action_driverHelp_to_driverHelpInformation)
        }
        if (item.itemId == R.id.driverHelp_StationFinder) {
            val intent = Intent(this.getContext(),
                StationFindingActivity::class.java)
            startActivity(intent)
            return true
        }

        return NavigationUI.onNavDestinationSelected(item, navCtr) ||
                super.onOptionsItemSelected(item)
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DriverHelp.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DriverHelp().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}