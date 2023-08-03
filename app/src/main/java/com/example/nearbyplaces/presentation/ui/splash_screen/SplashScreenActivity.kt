package com.example.nearbyplaces.presentation.ui.splash_screen

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.nearbyplaces.databinding.ActivitySplashScreenBinding
import com.example.nearbyplaces.presentation.ui.MainActivity

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {
    private var _binding: ActivitySplashScreenBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.imageViewLogo.animate().setDuration(2000).alpha(1f).withEndAction {
            navToPlacesFragment()
        }
    }

    private fun navToPlacesFragment() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        finish()
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}