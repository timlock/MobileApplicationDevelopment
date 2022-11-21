package de.hsos.ma.andsensorretrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
object StationService {
    val retrofit by lazy {
        Retrofit.Builder()

            .baseUrl("http://ilexanu.site:8080/RESTStationProvider-1.0-SNAPSHOT/ProviderServlet/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(StationAPI::class.java)
    }
}