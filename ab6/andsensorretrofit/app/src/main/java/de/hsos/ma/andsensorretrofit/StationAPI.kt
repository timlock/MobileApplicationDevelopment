package de.hsos.ma.andsensorretrofit

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Url
interface StationAPI {
    @GET
    suspend fun getStationsForZip(@Url url: String): List<StationData>
    @GET
    suspend fun getStationForZip(@Url url: String): StationData
    @POST("stationData")
    suspend fun addStation(@Body stationData: StationData): StationData
}