package com.example.quizapplication.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.quizapplication.activities.LoginActivity
import com.example.quizapplication.databinding.ActivityLoginIntroBinding
import com.example.quizapplication.activities.MainActivity
import com.google.firebase.auth.FirebaseAuth

class LoginIntro : AppCompatActivity() {
    private lateinit var binding: ActivityLoginIntroBinding // ViewBinding variable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val auth = FirebaseAuth.getInstance()
        if (auth.currentUser != null) {
            // User is already logged in
            redirect("MAIN")
            return
        }

        binding.btnGetStarted.setOnClickListener {
            redirect("LOGIN")
        }
    }

    private fun redirect(name: String) {
        val intent = when (name) {
            "LOGIN" -> Intent(this, LoginActivity::class.java)
            "MAIN" -> Intent(this, MainActivity::class.java)
            else -> throw IllegalArgumentException("Invalid path: $name")
        }
        startActivity(intent)
        finish()
    }
}
