package com.example.facebookloginapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.facebookloginapp.databinding.ActivityMainBinding
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.LoginStatusCallback
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult


class MainActivity : AppCompatActivity() {
    val callbackManager = CallbackManager.Factory.create()

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)


        val loginManager = LoginManager.getInstance()

        loginManager.logInWithReadPermissions(this, arrayListOf("public_profile"))

        loginManager.retrieveLoginStatus(this,object : LoginStatusCallback{
            override fun onCompleted(accessToken: AccessToken) {
                Log.d("fvmfkvmfv",accessToken.toString())
            }

            override fun onError(exception: Exception) {
                Log.d("fvmfkvmfv",exception.message.toString())
            }

            override fun onFailure() {
                Log.d("fvmfkvmfv","failure".toString())
            }

        })

        loginManager.registerCallback(callbackManager,object : FacebookCallback<LoginResult>{
            override fun onSuccess(result: LoginResult) {
                Log.d("fvkfnvfkjv","Success")
            }

            override fun onCancel() {
                Log.d("fvkfnvfkjv","onCancel")
            }

            override fun onError(error: FacebookException) {
                Log.d("fvkfnvfkjv","onError")

            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

}