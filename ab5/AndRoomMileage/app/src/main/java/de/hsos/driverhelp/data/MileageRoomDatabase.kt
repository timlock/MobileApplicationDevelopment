package de.hsos.driverhelp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 *  Annotierte Klasse ist eine Room Database mit einer Tabelle (Entity) von Mileage Objekten
 */
@Database(entities = [Mileage::class], version = 1)
abstract class MileageRoomDatabase : RoomDatabase() {

    abstract fun mileageDao(): MileageDao

    companion object {
        // Singleton verhindert, dass es mehrere Datenbank-Instanzen gibt.
        @Volatile
        private var INSTANCE: MileageRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope): MileageRoomDatabase {
            // Wenn INSTANCE nicht null ist, dann wird Instanz zurück gegeben.
            // Falls INSTANCE null ist, wird eine neue DB erzeugt.
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MileageRoomDatabase::class.java,
                    "mileage_database"
                )   // Ändert sich das Layout der DB, so muss diese migriert werden.
                    // Falls kein Migrations-Objekt gibt, wird die DB einfach zurück gesetzt.
                    // Die Migration wird an dieser Stelle nicht behandelt.
                    .fallbackToDestructiveMigration()
                    .addCallback(MileageDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
        private class MileageDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            /**
             * Override the onCreate method to populate the database.
             */
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                // If you want to keep the data through app restarts,
                // comment out the following line.
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.mileageDao())
                    }
                }
            }
        }

        /**
         * Eintrag in DB anlegen, damit nach Installation schon einmal etwas
         * vorhanden ist.
         */
        suspend fun populateDatabase(mileageDao: MileageDao) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            mileageDao.deleteAll()
            // Ein Objekt einfügen.
            var mileage = Mileage(1, 270, 79.3, "14.09.2022 | 225257 km | 79.3 €")
            mileageDao.insertMileage(mileage)
        }
    }
}
