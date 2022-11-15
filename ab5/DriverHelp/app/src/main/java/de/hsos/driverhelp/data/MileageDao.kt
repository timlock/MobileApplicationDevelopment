package de.hsos.driverhelp.data

import androidx.room.*

import kotlinx.coroutines.flow.Flow

/**
 * Dies ist eine zentrale Datei für Room.
 * Hier geschieht das Mapping von CRUD-Operationen auf SQL-Queries.
 *
 * Bei komplexen Datentypen (z.B. Date) müssen ggf. Typ-Konverter
 * bereit gestellt werden. In dem Bsp. hier werden keine solche Datentypen eingesetzt.
 *
 * Dokumentation:
 * https://developer.android.com/topic/libraries/architecture/room.html#type-converters
 */
@Dao
interface MileageDao {
    // Im Flow-Objekt wird der letzte Stand der Daten repräsentiert.
    // Wenn sich die Daten ändern, werden die Observer darüber informiert.
    // Dazu wird das Flow-Objekt später in LiveData konvertiert.
    @Query("SELECT * FROM mileage_table")
    fun gelAllMileages(): Flow<List<Mileage>>

    // 'suspend' bedeutet: Funktion läuft in separatem Thread / Coroutine und
    // kann unterbrochen werden. Nimmt man die Attribut weg, kommt es zu einer Exception (siehe
    // Logcat).
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMileage (mileage: Mileage)

    @Update
    suspend fun updateMileage(mileage: Mileage)

    @Delete
    suspend fun deleteMileage(mileage: Mileage)

    @Query("DELETE FROM mileage_table")
    /* suspend */ fun deleteAll()
}
