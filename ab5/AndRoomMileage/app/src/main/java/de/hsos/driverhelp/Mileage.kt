package de.hsos.driverhelp

/**
 * Entität als Spalten einer Datenbanktabelle.
 *
 * @ Entity - Angabe des Namens der Datenbank-Tabelle
 * @ PrimaryKey - Primärschlüssel.
 * @ ColumnInfo - Muss eigentlich nur angegeben werden, wenn Spaltenname und Variablenname abweichen.
 */

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mileage_table")
data class Mileage(
    @PrimaryKey(autoGenerate = true) var mileageId: Int? = null,
    @ColumnInfo (name = "distance") var distance: Int,
    @ColumnInfo (name = "fuel") var fuel: Double,
    @ColumnInfo (name = "info") var info: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readInt(),
        parcel.readDouble(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(mileageId)
        parcel.writeInt(distance)
        parcel.writeDouble(fuel)
        parcel.writeString(info)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Mileage> {
        override fun createFromParcel(parcel: Parcel): Mileage {
            return Mileage(parcel)
        }

        override fun newArray(size: Int): Array<Mileage?> {
            return arrayOfNulls(size)
        }
    }
}
