package com.vs.servicehub

import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val availableCategories = listOf(
        CategoryItem("Cleaning", android.R.drawable.ic_menu_preferences),
        CategoryItem("Appliances", android.R.drawable.ic_menu_manage),
        CategoryItem("Plumbers", android.R.drawable.ic_menu_edit),
        CategoryItem("AC Service", android.R.drawable.ic_menu_call),
        CategoryItem("Electric", android.R.drawable.ic_menu_preferences) // Added Electric Category
    )

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
        setupSearchLogic()
    }

    private fun setupSearchLogic() {
        val etSearch = findViewById<EditText>(R.id.etSearch)
        val btnSearch = findViewById<ImageView>(R.id.btnSearch)

        // Handle Search Button Click
        btnSearch.setOnClickListener {
            performSearch(etSearch.text.toString())
        }

        // Handle Keyboard Search Action
        etSearch.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_DONE) {
                performSearch(etSearch.text.toString())
                true
            } else {
                false
            }
        }
    }

    private fun performSearch(query: String) {
        if (query.isBlank()) return

        val foundCategory = availableCategories.find { 
            it.name.equals(query, ignoreCase = true) || query.contains(it.name, ignoreCase = true) 
        }

        if (foundCategory != null) {
            val intent = Intent(this, ServiceDetailActivity::class.java)
            intent.putExtra("SERVICE_NAME", foundCategory.name.uppercase())
            startActivity(intent)
        } else {
            Toast.makeText(this, "Service not available: No option found", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupCategories() {
        val rv: RecyclerView = findViewById(R.id.categoryRecyclerView)
        rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rv.adapter = CategoryAdapter(availableCategories) { category ->
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
            // Focus on edit text when container is clicked
            findViewById<EditText>(R.id.etSearch).requestFocus()
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
            val intent = Intent(this, NotificationsActivity::class.java)
            startActivity(intent)
        }

        findViewById<ImageView>(R.id.navProfile).setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
    }
}