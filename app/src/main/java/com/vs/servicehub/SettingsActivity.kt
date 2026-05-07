package com.vs.servicehub

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.switchmaterial.SwitchMaterial

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        findViewById<ImageView>(R.id.btnBack).setOnClickListener {
            finish()
        }

        val switchNotifications = findViewById<SwitchMaterial>(R.id.switchNotifications)
        switchNotifications.setOnCheckedChangeListener { _, isChecked ->
            val status = if (isChecked) "enabled" else "disabled"
            Toast.makeText(this, "Notifications $status", Toast.LENGTH_SHORT).show()
        }

        val switchDarkMode = findViewById<SwitchMaterial>(R.id.switchDarkMode)
        switchDarkMode.setOnCheckedChangeListener { _, isChecked ->
            val mode = if (isChecked) "on" else "off"
            Toast.makeText(this, "Dark Mode $mode (Simulation)", Toast.LENGTH_SHORT).show()
        }

        // Fixed these references to match activity_settings.xml
        findViewById<TextView>(R.id.tvLanguage).setOnClickListener {
            Toast.makeText(this, "Language selection coming soon", Toast.LENGTH_SHORT).show()
        }

        findViewById<TextView>(R.id.tvPrivacyPolicy).setOnClickListener {
            Toast.makeText(this, "Opening Privacy Policy...", Toast.LENGTH_SHORT).show()
        }
    }
}