package com.vs.servicehub

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton

class ServiceDetailActivity : AppCompatActivity() {

    private lateinit var providerAdapter: ProviderAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_detail)

        val serviceName = intent.getStringExtra("SERVICE_NAME") ?: "AC SERVICE AND REPAIR"
        
        findViewById<TextView>(R.id.tvServiceName).text = serviceName
        
        findViewById<ImageView>(R.id.btnBack).setOnClickListener {
            finish()
        }

        setupProvidersList(serviceName)

        findViewById<MaterialButton>(R.id.btnBook).setOnClickListener {
            val selectedProvider = providerAdapter.getSelectedProvider()
            if (selectedProvider != null) {
                // Open User Booking Page
                val intent = Intent(this, UserBookingActivity::class.java)
                intent.putExtra("PROVIDER_NAME", selectedProvider.name)
                intent.putExtra("SERVICE_NAME", serviceName)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Please select a provider first", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupProvidersList(serviceName: String) {
        val providers = getProvidersForService(serviceName)

        val rv: RecyclerView = findViewById(R.id.rvProviders)
        rv.layoutManager = LinearLayoutManager(this)
        providerAdapter = ProviderAdapter(providers)
        rv.adapter = providerAdapter
    }

    private fun getProvidersForService(serviceName: String): List<Provider> {
        val upperName = serviceName.uppercase()
        return when {
            upperName.contains("HOME CLEANING") -> listOf(
                Provider("Sara Khan", 4.9f, 42, "1.2 km", android.R.drawable.ic_menu_myplaces),
                Provider("Elena Gilbert", 4.7f, 31, "2.5 km", android.R.drawable.ic_menu_myplaces),
                Provider("John Wick", 4.5f, 120, "4.0 km", android.R.drawable.ic_menu_myplaces)
            )
            upperName.contains("KITCHEN DEEP CLEAN") -> listOf(
                Provider("Gordon Ramsay", 5.0f, 500, "0.5 km", android.R.drawable.ic_menu_myplaces),
                Provider("Monica Geller", 4.9f, 200, "1.8 km", android.R.drawable.ic_menu_myplaces),
                Provider("Thomas Keller", 4.8f, 150, "3.0 km", android.R.drawable.ic_menu_myplaces)
            )
            upperName.contains("BATHROOM CLEANING") -> listOf(
                Provider("Danny Tanner", 4.9f, 85, "1.0 km", android.R.drawable.ic_menu_myplaces),
                Provider("Marie Kondo", 5.0f, 300, "2.2 km", android.R.drawable.ic_menu_myplaces),
                Provider("Adrian Monk", 4.9f, 110, "3.5 km", android.R.drawable.ic_menu_myplaces)
            )
            upperName.contains("CLEANING") || upperName.contains("CLEAN") -> listOf(
                Provider("General Cleaner 1", 4.2f, 15, "1.5 km", android.R.drawable.ic_menu_myplaces),
                Provider("General Cleaner 2", 4.0f, 10, "2.8 km", android.R.drawable.ic_menu_myplaces)
            )
            upperName.contains("AC") -> listOf(
                Provider("Ali Haidar", 4.5f, 18, "2 km", android.R.drawable.ic_menu_myplaces),
                Provider("Ismail Ali", 4.8f, 25, "3.5 km", android.R.drawable.ic_menu_myplaces),
                Provider("Ahmed Ali", 4.2f, 12, "5 km", android.R.drawable.ic_menu_myplaces)
            )
            upperName.contains("PLUMBER") -> listOf(
                Provider("Mario Rossi", 5.0f, 88, "0.8 km", android.R.drawable.ic_menu_myplaces),
                Provider("Luigi Smith", 4.6f, 54, "2.1 km", android.R.drawable.ic_menu_myplaces),
                Provider("Tom Hardy", 4.3f, 19, "6 km", android.R.drawable.ic_menu_myplaces)
            )
            upperName.contains("APPLIANCE") -> listOf(
                Provider("David Miller", 4.4f, 33, "1.5 km", android.R.drawable.ic_menu_myplaces),
                Provider("Robert Pattinson", 4.9f, 67, "3.2 km", android.R.drawable.ic_menu_myplaces),
                Provider("Chris Evans", 4.1f, 15, "7 km", android.R.drawable.ic_menu_myplaces)
            )
            upperName.contains("ELECTRIC") -> listOf(
                Provider("Nikola Tesla", 5.0f, 150, "1.2 km", android.R.drawable.ic_menu_myplaces),
                Provider("Thomas Edison", 4.8f, 95, "2.8 km", android.R.drawable.ic_menu_myplaces)
            )
            else -> listOf(
                Provider("General Provider 1", 4.0f, 10, "1 km", android.R.drawable.ic_menu_myplaces),
                Provider("General Provider 2", 4.2f, 15, "3 km", android.R.drawable.ic_menu_myplaces)
            )
        }
    }
}