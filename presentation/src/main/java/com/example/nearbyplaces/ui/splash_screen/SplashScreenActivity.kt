package com.example.nearbyplaces.ui.splash_screen

import android.annotation.SuppressLint
import android.app.ActivityOptions
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.nearbyplaces.R
import com.example.nearbyplaces.helper.AuthManager
import com.example.nearbyplaces.helper.Constants
import com.example.nearbyplaces.ui.MainActivity

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {
    //    private var _binding: ActivitySplashScreenBinding? = null
//    private val binding get() = _binding!!
    private lateinit var webView: WebView
    private val animationDuration = 2000L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        _binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_splash_screen)
        supportActionBar?.hide()
        val imageViewLogo = findViewById<View>(R.id.imageViewLogo)
        val buttonLoginToFoursqaure = findViewById<View>(R.id.buttonLoginToFoursqaure)
        imageViewLogo.animate().setDuration(animationDuration).alpha(1f).withEndAction {
            if (!AuthManager.isAuthenticated(this)) {
                imageViewLogo.visibility = View.GONE
                buttonLoginToFoursqaure.visibility = View.VISIBLE
                buttonLoginToFoursqaure.setOnClickListener { showAuthenticationForm() }
            } else {
                navToMainActivity()
            }
        }
    }

    private fun navToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        val animationBundle = ActivityOptions.makeCustomAnimation(
            this,
            android.R.anim.fade_in,
            android.R.anim.fade_in
        ).toBundle()
        startActivity(intent, animationBundle)
        finish()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun showAuthenticationForm() {
        webView = WebView(this)
        setContentView(webView)

        val webSettings = webView.settings
        webSettings.javaScriptEnabled = true

        // Set cache mode to no cache, so the WebView will not use any cached data
        webSettings.cacheMode = WebSettings.LOAD_NO_CACHE

        // Load the Foursquare OAuth 2.0 authentication URL in the WebView
        webView.loadUrl(
            "https://foursquare.com/oauth2/authenticate" +
                    "?client_id=${Constants.CLIENT_ID}" +
                    "&response_type=token" +
                    "&redirect_uri=${Constants.REDIRECT_URL}"
        )

        // Set a WebViewClient to handle the callback URL and retrieve the token
        webView.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                if (url?.startsWith(Constants.REDIRECT_URL) == true) {
                    val token = parseAuthTokenFromUrl(url)
                    if (token.isNotEmpty()) {
                        AuthManager.saveAuthenticationToken(this@SplashScreenActivity, token)
                        webView.visibility = View.GONE
                        navToMainActivity()
                    } else {
                        webView.visibility = View.GONE
                        Toast.makeText(this@SplashScreenActivity, "Error!", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }

        }

    }

    private fun parseAuthTokenFromUrl(url: String): String {
        // Parse the token from the URL (extract it from the fragment part)
        val tokenPart = url.substringAfter("#access_token=")
        return tokenPart.substringBefore("&")
    }
}