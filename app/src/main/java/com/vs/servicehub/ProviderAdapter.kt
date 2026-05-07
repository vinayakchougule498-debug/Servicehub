package com.vs.servicehub

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProviderAdapter(private val providers: List<Provider>) :
    RecyclerView.Adapter<ProviderAdapter.ProviderViewHolder>() {

    private var selectedPosition = -1

    class ProviderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val img: ImageView = view.findViewById(R.id.providerImg)
        val name: TextView = view.findViewById(R.id.providerName)
        val distance: TextView = view.findViewById(R.id.providerDistance)
        val checkBox: CheckBox = view.findViewById(R.id.providerCheckbox)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProviderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_provider, parent, false)
        return ProviderViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProviderViewHolder, position: Int) {
        val provider = providers[position]
        holder.name.text = provider.name
        holder.distance.text = provider.distance
        holder.img.setImageResource(provider.imageRes)

        // Only one selection at a time
        holder.checkBox.isChecked = position == selectedPosition

        holder.itemView.setOnClickListener {
            val oldPosition = selectedPosition
            selectedPosition = holder.adapterPosition
            notifyItemChanged(oldPosition)
            notifyItemChanged(selectedPosition)
        }

        holder.checkBox.setOnClickListener {
            val oldPosition = selectedPosition
            selectedPosition = holder.adapterPosition
            notifyItemChanged(oldPosition)
            notifyItemChanged(selectedPosition)
        }
    }

    override fun getItemCount() = providers.size

    fun getSelectedProvider(): Provider? {
        return if (selectedPosition != -1) providers[selectedPosition] else null
    }
}