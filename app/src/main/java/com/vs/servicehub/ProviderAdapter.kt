package com.vs.servicehub

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProviderAdapter(private val providers: List<Provider>) :
    RecyclerView.Adapter<ProviderAdapter.ProviderViewHolder>() {

    class ProviderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val img: ImageView = view.findViewById(R.id.providerImg)
        val name: TextView = view.findViewById(R.id.providerName)
        val distance: TextView = view.findViewById(R.id.providerDistance)
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
    }

    override fun getItemCount() = providers.size
}