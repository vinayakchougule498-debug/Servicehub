package com.vs.servicehub

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class NotificationsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notifications)

        findViewById<ImageView>(R.id.btnBack).setOnClickListener {
            finish()
        }

        findViewById<ImageView>(R.id.navHome).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
            finish()
        }

        findViewById<ImageView>(R.id.navHistory).setOnClickListener {
            val intent = Intent(this, BookingsActivity::class.java)
            startActivity(intent)
            finish()
        }

        findViewById<ImageView>(R.id.navProfile).setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
            finish()
        }

        setupNotificationsList()
    }

    private fun setupNotificationsList() {
        val notifications = listOf(
            NotificationItem(1, "Booking Confirmed", "Your AC service has been scheduled for tomorrow.", "2m ago", android.R.drawable.ic_popup_reminder),
            NotificationItem(2, "Special Offer!", "Get 20% off on your next Kitchen Cleaning service.", "1h ago", android.R.drawable.ic_menu_send),
            NotificationItem(3, "Worker Assigned", "John Wick is on his way to your location.", "3h ago", android.R.drawable.ic_menu_myplaces),
            NotificationItem(4, "Payment Successful", "Your payment of 45 SAR was successful.", "1d ago", android.R.drawable.ic_menu_manage),
            NotificationItem(5, "Account Security", "Your password was changed successfully.", "2d ago", android.R.drawable.ic_lock_idle_lock),
            NotificationItem(6, "Service Completed", "Your Home Cleaning service is now complete. Please rate us!", "3d ago", android.R.drawable.ic_menu_today),
            NotificationItem(7, "Welcome to Service Hub", "Thank you for joining our community!", "5d ago", android.R.drawable.ic_menu_info_details)
        )

        val rv: RecyclerView = findViewById(R.id.rvNotifications)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = NotificationsAdapter(notifications)
    }
}