package com.vs.servicehub

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton

class FeaturedServiceAdapter(
    private val services: List<FeaturedService>,
    private val onBookClick: (FeaturedService) -> Unit
) : RecyclerView.Adapter<FeaturedServiceAdapter.ServiceViewHolder>() {

    class ServiceViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.serviceImg)
        val name: TextView = view.findViewById(R.id.featuredServiceName)
        val price: TextView = view.findViewById(R.id.priceTag)
        val bookBtn: MaterialButton = view.findViewById(R.id.bookNowBtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_featured_service, parent, false)
        return ServiceViewHolder(view)
    }

    override fun onBindViewHolder(holder: ServiceViewHolder, position: Int) {
        val service = services[position]
        holder.name.text = service.name
        holder.price.text = service.price
        holder.image.setImageResource(service.imageRes)
        
        holder.bookBtn.setOnClickListener {
            onBookClick(service)
        }
    }

    override fun getItemCount() = services.size
}