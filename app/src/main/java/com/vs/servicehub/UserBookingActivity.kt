package com.vs.servicehub

import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class UserBookingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_booking)

        val btnBack = findViewById<ImageView>(R.id.btnBack)
        val etName = findViewById<EditText>(R.id.etName)
        val etLocation = findViewById<EditText>(R.id.etLocation)
        val etMobile = findViewById<EditText>(R.id.etMobile)
        val btnConfirm = findViewById<MaterialButton>(R.id.btnConfirmBooking)

        btnBack.setOnClickListener {
            finish()
        }

        btnConfirm.setOnClickListener {
            val name = etName.text.toString()
            val location = etLocation.text.toString()
            val mobile = etMobile.text.toString()

            if (name.isBlank() || location.isBlank() || mobile.isBlank()) {
                Toast.makeText(this, "Please fill all details", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Booking Confirmed for $name!", Toast.LENGTH_LONG).show()
                // In a real app, you'd save this to a database
                finish() 
            }
        }
    }
}