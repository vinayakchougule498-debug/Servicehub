package com.vs.servicehub

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Check if already logged in
        val sharedPref = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        if (sharedPref.contains("userName")) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
            return
        }

        setContentView(R.layout.activity_login)

        val etName = findViewById<EditText>(R.id.etLoginName)
        val etEmail = findViewById<EditText>(R.id.etLoginEmail)
        val btnLogin = findViewById<MaterialButton>(R.id.btnLogin)

        btnLogin.setOnClickListener {
            val name = etName.text.toString()
            val email = etEmail.text.toString()

            if (name.isNotBlank() && email.isNotBlank()) {
                // Save user info
                with(sharedPref.edit()) {
                    putString("userName", name)
                    putString("userEmail", email)
                    apply()
                }

                Toast.makeText(this, "Welcome, $name!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Please enter name and email", Toast.LENGTH_SHORT).show()
            }
        }
    }
}