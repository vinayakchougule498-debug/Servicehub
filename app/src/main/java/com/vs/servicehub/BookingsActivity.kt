package com.vs.servicehub

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class BookingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bookings)

        findViewById<ImageView>(R.id.btnBack).setOnClickListener {
            finish()
        }

        findViewById<ImageView>(R.id.navHome).setOnClickListener {
            finish()
        }

        setupBookingsList()
    }

    private fun setupBookingsList() {
        val bookings = listOf(
            Booking(1, "Gavid rao", "AC Service And Repair", "Jun 8, 2020", "45 SAR", "In Process", android.R.drawable.ic_menu_myplaces),
            Booking(2, "Ali Haidar", "Microwave repair", "Jun 8, 2020", "45 SAR", "Completed", android.R.drawable.ic_menu_myplaces),
            Booking(3, "Ali Haidar", "AC service and repair", "Jun 8, 2020", "45 SAR", "Cancelled", android.R.drawable.ic_menu_myplaces)
        )

        val rv: RecyclerView = findViewById(R.id.rvBookings)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = BookingsAdapter(bookings)
    }
}