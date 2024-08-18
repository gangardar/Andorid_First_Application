package com.example.tutorials

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PermissionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_permission)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val button = findViewById<Button>(R.id.btnRequestPermission)
        button.setOnClickListener{
            requestPermission()
        }


    }

    private fun hasForeGroundLocationPermission()
    = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED

    private fun hasBackGroundLocationPermission()
    = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_BACKGROUND_LOCATION) == PackageManager.PERMISSION_GRANTED

    private fun requestPermission() {
        val permissionToRequest = mutableListOf<String>();

        if(!hasForeGroundLocationPermission()) permissionToRequest.add(Manifest.permission.ACCESS_COARSE_LOCATION)
        if(!hasBackGroundLocationPermission()) permissionToRequest.add(Manifest.permission.ACCESS_BACKGROUND_LOCATION)

        if(permissionToRequest.isNotEmpty()){
            ActivityCompat.requestPermissions(this,permissionToRequest.toTypedArray(), 0)
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 0 && grantResults.isNotEmpty()){
            for(i in grantResults.indices){
                if(grantResults[i]== PackageManager.PERMISSION_GRANTED)
                    Log.d("PermissionResult:", "${permissions[i]} is granted!")

                if(grantResults[i]== PackageManager.PERMISSION_DENIED)
                    Log.d("PermissionResult:", "${permissions[i]} is denied!")
            }
        }
    }
}