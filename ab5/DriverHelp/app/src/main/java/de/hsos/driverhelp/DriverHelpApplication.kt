package de.hsos.driverhelp

import android.app.Application
import de.hsos.driverhelp.data.MileageRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class DriverHelpApplication : Application() {
    // Anlegen eines Coroutinen-Scopes. Endet mit dem dem Prozess.
    val applicationScope = CoroutineScope(SupervisorJob())

    // 'by lazy' meint: Datenbank und Repository werden erst erzeugt,
    // wenn sie benötigt werden. Nicht gleich beim Start der App.
    val database by lazy { MileageRoomDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { MileageRepository(database.mileageDao()) }


}