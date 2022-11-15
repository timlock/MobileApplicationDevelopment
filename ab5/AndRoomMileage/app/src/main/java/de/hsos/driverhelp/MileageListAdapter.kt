package de.hsos.driverhelp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import de.hsos.driverhelp.data.Mileage

import kotlinx.android.synthetic.main.layout_data_list_item.view.*

/**
 *   Adapter verwaltet Liste der im UI angezeigten Mileage-Objekte.
 */
class MileageListAdapter : RecyclerView.Adapter<MileageViewHolder>() {

    // Ein RecyclerView.Adapter hält selbst keine Liste der verwalteten
    // Daten. Das muss in der abgeleiteten Klasse geschehen.
    // Alternativ könnte ein ListAdapter eingesetzt werden, der aber im
    // Hinblick auf die Darstellung der Listenelemente limitiert ist.
    private var mileageList = mutableListOf<Mileage>()

    var clickListener: ListClickListener<Mileage>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MileageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_data_list_item, parent, false)
        return MileageViewHolder(view)
    }

    override fun onBindViewHolder(holder: MileageViewHolder, position: Int) {
        val mileage = mileageList[position]
        holder.distance.text = mileage.distance.toString()
        holder.fuel.text = mileage.fuel.toString()
        holder.info.text = mileage.info
        holder.layout.setOnClickListener {
            clickListener?.onClick(mileage,position)
        }
        holder.imgDelete.setOnClickListener {
            clickListener?.onDelete(mileage)
        }
    }

    // Diese Funktion benötigt der Adapter, damit ein Layout
    // erzeugt werden kann.
    override fun getItemCount(): Int {
        return mileageList.size
    }

    // Diese Funktion aktualisiert die als Cache gedachte Liste.
    // Sie wird vom Observer des ViewModels bei Änderungen aufgerufen.
    fun setMileages(mileages: List<Mileage>) {
        this.mileageList = mileages.toMutableList()
        System.out.println ("LOGGER: adapter set with " + mileages.size + " entries.")
        // Ist hier eigentlich nicht notwendig, denn es gibt keine Observer für die
        // interne Liste.
        notifyDataSetChanged()
    }

    fun setOnItemClick(listClickListener: ListClickListener<Mileage>) {
        this.clickListener = listClickListener
    }
}

class MileageViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val distance = view.text_distance
    // Hier stimmt etwas nicht!!!
    val fuel = view.text_info
    val info = view.text_fuel
    val layout = view.layout
    val imgDelete = view.imgDelete
}

interface ListClickListener<T> {
    fun onClick(data: T, position: Int)
    fun onDelete(mileage: T)
}
