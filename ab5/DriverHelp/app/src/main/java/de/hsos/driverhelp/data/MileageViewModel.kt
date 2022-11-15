package de.hsos.driverhelp

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import de.hsos.driverhelp.data.Mileage
import de.hsos.driverhelp.data.MileageRepository
import kotlinx.coroutines.launch

class MileageViewModel(private val repository: MileageRepository) : ViewModel() {
    private val totalMileage:Long =  0L;


    // Wir nutzen LiveData mit Caching, die Daten sind in allMileages gespeichert. Vorteile:
    // - Observer können hinzugefügt werden (z.B. UI), die auf Änderungen des Datenmodells reagieren.
    // - Das Repository und das UI sind über das ViewModel komplett getrennt.
    val allMileages: LiveData<List<Mileage>> = repository.allMileages.asLiveData()

    //
    // Startet eine neue Coroutine zum nicht-blockierenden Einfügen von Daten.
    //
    fun insert(mileage: Mileage) = viewModelScope.launch {
        repository.insert(mileage)
        System.out.println(
            "LOGGER: data inserted into repository; " +
                    allMileages.value?.size + " entries."
        )
    }

    fun update(mileage: Mileage) = viewModelScope.launch {
        repository.update(mileage)
        System.out.println(
            "LOGGER: data updated in repository; " +
                    allMileages.value?.size + " entries."
        )
    }

    fun delete(mileage: Mileage) = viewModelScope.launch {
        repository.delete(mileage)
        System.out.println(
            "LOGGER: data deleted in repository; " +
                    allMileages.value?.size + " entries."
        )
    }
}

/**
 *  Die Factory erzeugt ein ViewModel, welches das Daten-Repository verwendet. Die
 *  Factory ist notwendig, weil ViewModels evtl. neu erzeugt werden müssen.
 *  Z.B. wenn die Activity neu gestartet wird. Das passiert bspw. bei einem Perspektivwechsel
 *  (Portrait -> Landscape).
 */
class MileageViewModelFactory (private val repository: MileageRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MileageViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MileageViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
