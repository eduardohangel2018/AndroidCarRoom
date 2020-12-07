package com.android.carroom

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListCarAdapter() : RecyclerView.Adapter<ListCarAdapter.ViewHolder>() {

    var listCars = listOf<Car>()
        set(value) {

            field = value
            notifyDataSetChanged()
        }

        lateinit var itemListener: RecyclerViewItemListener

        fun setRecyclerViewItemListener(listener: RecyclerViewItemListener) {

            itemListener = listener
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListCarAdapter.ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.car_list, parent, false)
        return ListCarAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListCarAdapter.ViewHolder, position: Int) {

        holder.bindItem(listCars[position], itemListener, position)
    }

    override fun getItemCount(): Int {

        return listCars.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(car: Car, itemListener: RecyclerViewItemListener, position: Int) {

            val nameRow = itemView.findViewById<TextView>(R.id.nameRow)
            nameRow.setText(car.name)
            val yearRow = itemView.findViewById<TextView>(R.id.yearRow)
            yearRow.setText(car.year)
            val costRow = itemView.findViewById<TextView>(R.id.costRow)
            costRow.setText(car.price)
            val descRow = itemView.findViewById<TextView>(R.id.descRow)
            descRow.setText(car.desc)

            itemView.setOnClickListener {
                itemListener.recyclerViewItemClicked(it, car.id)
            }
        }

    }
}
