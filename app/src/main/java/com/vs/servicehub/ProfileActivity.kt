package com.vs.servicehub

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val sharedPref = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val name = sharedPref.getString("userName", "Guest")
        val email = sharedPref.getString("userEmail", "guest@email.com")

        findViewById<TextView>(R.id.tvProfileName).text = name
        findViewById<TextView>(R.id.tvProfileEmail).text = email

        findViewById<ImageView>(R.id.btnBack).setOnClickListener {
            finish()
        }

        findViewById<ImageView>(R.id.navHome).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
            finish()
        }

        setupMenuActions(sharedPref)
    }

    private fun setupMenuActions(sharedPref: android.content.SharedPreferences) {
        findViewById<MaterialButton>(R.id.btnPaymentMethods).setOnClickListener {
            openPhonePePayment()
        }
        
        findViewById<MaterialButton>(R.id.btnSettings).setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }

        findViewById<MaterialButton>(R.id.btnLogout).setOnClickListener {
            with(sharedPref.edit()) {
                clear()
                apply()
            }
            Toast.makeText(this, "Logged out successfully", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }

    private fun openPhonePePayment() {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("upi://pay?pa=xyz@upi&pn=xyz&am=1.00&cu=INR")
        try {
            startActivity(Intent.createChooser(intent, "Pay with PhonePe or others"))
        } catch (e: Exception) {
            Toast.makeText(this, "No UPI app found", Toast.LENGTH_SHORT).show()
        }
    }
}