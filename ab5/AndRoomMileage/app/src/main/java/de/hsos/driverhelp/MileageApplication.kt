package de.hsos.driverhelp

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class MileageApplication : Application() {
    // No need to cancel this scope as it'll be torn down with the process
    val applicationScope = CoroutineScope(SupervisorJob())

    // 'by lazy' meint: Datenbank und Repository werden erst erzeugt,
    // wenn sie benötigt werden. Nicht gleich beim Start der App.
    val database by lazy { MileageRoomDatabase.getDatabase(this , applicationScope) }
    val repository by lazy { MileageRepository(database.mileageDao()) }
}