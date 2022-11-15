package de.hsos.driverhelp.data

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

/**
 * Siehe Erklärungen im Architecture Guide:
 * https://developer.android.com/topic/libraries/architecture/guide.html
 */
class MileageRepository(private val mileageDao: MileageDao) {

    // Room führt alle Queries in einem separaten Thread aus.
    // Beobachteter Flow informiert alle registrierten Observer, wenn sich die
    // Daten geändert haben.
    val allMileages: Flow<List<Mileage>> = mileageDao.gelAllMileages()

    // suspend bedeutet, dass Methode durch eine Coroutine und nicht auf dem
    // Main Thread ausgeführt wird.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(mileage: Mileage) {
        mileageDao.insertMileage(mileage)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun update(mileage: Mileage) {
        mileageDao.updateMileage(mileage)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(mileage: Mileage) {
        mileageDao.deleteMileage(mileage)
    }
}
