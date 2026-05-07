package com.vs.servicehub

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class BookingsActivity : AppCompatActivity() {

    private lateinit var rvBookings: RecyclerView
    private val allBookings = listOf(
        Booking(1, "Gavid rao", "AC Service And Repair", "Jun 8, 2020", "45 SAR", "In Process", android.R.drawable.ic_menu_myplaces),
        Booking(2, "Ali Haidar", "Microwave repair", "Jun 8, 2020", "45 SAR", "Completed", android.R.drawable.ic_menu_myplaces),
        Booking(3, "Ali Haidar", "AC service and repair", "Jun 8, 2020", "45 SAR", "Cancelled", android.R.drawable.ic_menu_myplaces),
        Booking(4, "John Doe", "Home Cleaning", "Jul 10, 2020", "120 SAR", "Completed", android.R.drawable.ic_menu_myplaces),
        Booking(5, "Jane Smith", "Plumbing service", "Aug 15, 2020", "80 SAR", "In Process", android.R.drawable.ic_menu_myplaces),
        Booking(6, "Mike Ross", "Electrical repair", "Sep 20, 2020", "60 SAR", "Cancelled", android.R.drawable.ic_menu_myplaces),
        Booking(7, "Sarah Connor", "Kitchen Cleaning", "Oct 05, 2020", "150 SAR", "Completed", android.R.drawable.ic_menu_myplaces),
        Booking(8, "Kyle Reese", "AC maintenance", "Nov 12, 2020", "50 SAR", "In Process", android.R.drawable.ic_menu_myplaces)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bookings)

        rvBookings = findViewById(R.id.rvBookings)
        rvBookings.layoutManager = LinearLayoutManager(this)

        findViewById<ImageView>(R.id.btnBack).setOnClickListener {
            finish()
        }

        findViewById<ImageView>(R.id.navHome).setOnClickListener {
            finish()
        }

        setupTabs()
        updateBookingsList("All")
    }

    private fun setupTabs() {
        val tabAll = findViewById<TextView>(R.id.tabAll)
        val tabCompleted = findViewById<TextView>(R.id.tabCompleted)
        val tabInProcess = findViewById<TextView>(R.id.tabInProcess)
        val tabCancelled = findViewById<TextView>(R.id.tabCancelled)

        val tabs = listOf(tabAll, tabCompleted, tabInProcess, tabCancelled)

        tabs.forEach { tab ->
            tab.setOnClickListener {
                updateTabUI(tab, tabs)
                updateBookingsList(tab.text.toString())
            }
        }
    }

    private fun updateTabUI(selectedTab: TextView, allTabs: List<TextView>) {
        allTabs.forEach { tab ->
            if (tab == selectedTab) {
                tab.setBackgroundResource(R.drawable.bg_nav_active)
                tab.setTextColor(ContextCompat.getColor(this, R.color.white))
            } else {
                tab.background = null
                tab.setTextColor(ContextCompat.getColor(this, R.color.text_hint))
            }
        }
    }

    private fun updateBookingsList(status: String) {
        val filteredList = if (status == "All") {
            allBookings
        } else {
            allBookings.filter { it.status.equals(status, ignoreCase = true) }
        }
        rvBookings.adapter = BookingsAdapter(filteredList)
    }
}
