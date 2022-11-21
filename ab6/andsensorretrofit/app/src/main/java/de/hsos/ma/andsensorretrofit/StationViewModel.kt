package de.hsos.ma.andsensorretrofit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class StationViewModel : ViewModel() {
    val myResponse: MutableLiveData<StationData> = MutableLiveData()
    val myResponseList: MutableLiveData<List<StationData>> = MutableLiveData()
    fun getStationsForZip(zip: Int) {
        viewModelScope.launch {
            myResponseList.value =
                StationService.retrofit.getStationsForZip(zip.toString())
        }
    }
    fun getStationForZip(zip: String) {
        viewModelScope.launch {
            myResponse.value = StationService.retrofit.getStationForZip(zip)
        }
    }
    fun addStation(stationData: StationData) {
        viewModelScope.launch {
            myResponse.value = StationService.retrofit.addStation(stationData)
        }
    }
}

