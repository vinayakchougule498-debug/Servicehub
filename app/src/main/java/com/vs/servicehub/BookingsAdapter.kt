package com.vs.servicehub

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class BookingsAdapter(private val bookings: List<Booking>) :
    RecyclerView.Adapter<BookingsAdapter.BookingViewHolder>() {

    class BookingViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val img: ImageView = view.findViewById(R.id.workerImg)
        val name: TextView = view.findViewById(R.id.workerName)
        val service: TextView = view.findViewById(R.id.bookingService)
        val date: TextView = view.findViewById(R.id.bookingDate)
        val price: TextView = view.findViewById(R.id.bookingPrice)
        val status: TextView = view.findViewById(R.id.bookingStatus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookingViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_booking, parent, false)
        return BookingViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookingViewHolder, position: Int) {
        val booking = bookings[position]
        holder.name.text = booking.workerName
        holder.service.text = booking.serviceName
        holder.date.text = booking.date
        holder.price.text = booking.price
        holder.status.text = booking.status
        holder.img.setImageResource(booking.imageRes)

        // Color coding for status
        when (booking.status) {
            "Completed" -> holder.status.setTextColor(ContextCompat.getColor(holder.itemView.context, android.R.color.holo_green_dark))
            "Cancelled" -> holder.status.setTextColor(ContextCompat.getColor(holder.itemView.context, android.R.color.holo_red_dark))
            else -> holder.status.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.accent_yellow))
        }
    }

    override fun getItemCount() = bookings.size
}