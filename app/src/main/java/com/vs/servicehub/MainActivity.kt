package com.vs.servicehub

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        
        val mainView = findViewById<android.view.View>(R.id.main)
        ViewCompat.setOnApplyWindowInsetsListener(mainView) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupCategories()
        setupFeaturedServices()
        setupInteractions()
        setupBottomNavigation()
    }

    private fun setupCategories() {
        val categories = listOf(
            CategoryItem("Cleaning", android.R.drawable.ic_menu_preferences),
            CategoryItem("Appliances", android.R.drawable.ic_menu_manage),
            CategoryItem("Plumbers", android.R.drawable.ic_menu_edit),
            CategoryItem("AC Service", android.R.drawable.ic_menu_call)
        )

        val rv: RecyclerView = findViewById(R.id.categoryRecyclerView)
        rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rv.adapter = CategoryAdapter(categories) { category ->
            val intent = Intent(this, ServiceDetailActivity::class.java)
            intent.putExtra("SERVICE_NAME", category.name.uppercase())
            startActivity(intent)
        }
    }

    private fun setupFeaturedServices() {
        val featured = listOf(
            FeaturedService(1, "Home Cleaning", "$275", android.R.drawable.ic_menu_gallery, "Professional home deep cleaning service"),
            FeaturedService(2, "Kitchen Deep Clean", "$150", android.R.drawable.ic_menu_report_image, "Deep cleaning for your kitchen appliances and surfaces"),
            FeaturedService(3, "Bathroom Cleaning", "$100", android.R.drawable.ic_menu_camera, "Sanitized and sparkling clean bathrooms")
        )

        val rv: RecyclerView = findViewById(R.id.featuredRecyclerView)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = FeaturedServiceAdapter(featured) { service ->
            val intent = Intent(this, ServiceDetailActivity::class.java)
            intent.putExtra("SERVICE_NAME", service.name.uppercase())
            startActivity(intent)
        }
    }

    private fun setupInteractions() {
        findViewById<android.view.View>(R.id.searchContainer).setOnClickListener {
            Toast.makeText(this, "Opening Search...", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupBottomNavigation() {
        findViewById<ImageView>(R.id.navHome).setOnClickListener {
            Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()
        }

        findViewById<ImageView>(R.id.navHistory).setOnClickListener {
            val intent = Intent(this, BookingsActivity::class.java)
            startActivity(intent)
        }

        findViewById<ImageView>(R.id.navNotifications).setOnClickListener {
            Toast.makeText(this, "No new notifications", Toast.LENGTH_SHORT).show()
        }

        findViewById<ImageView>(R.id.navProfile).setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
    }
}