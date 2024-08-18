package com.example.tutorials

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import android.os.Build.VERSION
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tutorials.databinding.ActivityMenuViewBinding
import com.example.tutorials.databinding.ActivitySharedDataBinding

class SharedDataActivity : AppCompatActivity() {
    lateinit var binding: ActivitySharedDataBinding
    val CHANNEL_ID = "Channel id"
    val CHANNEL_NAME = "Channel name"
    val NOTIFICATION_ID = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySharedDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPref = getSharedPreferences("formPref", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()

        val intent = Intent(this,SharedDataActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(this,0,intent, PendingIntent.FLAG_IMMUTABLE)

        val builder = NotificationCompat.Builder(this,CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle("New Notification")
            .setContentText("Some new information have come up!")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        val notificationManager = NotificationManagerCompat.from(this)

        binding.btnSave.setOnClickListener {
            val name = binding.etName.text.toString()
            val age = binding.etAge.text.toString().toInt()
            val isAdult = binding.cbAdult.isChecked

            editor.apply {
                putString("name", name)
                putInt("age", age)
                putBoolean("isAdult", isAdult)
                apply()
            }

        }

        binding.btnLoad.setOnClickListener {
            val name = sharedPref.getString("name", null)
            val age = sharedPref.getInt("age", 0)
            val isAdult = sharedPref.getBoolean("isAdult", false)

            binding.etName.setText(name)
            binding.etAge.setText(age.toString())
            binding.cbAdult.isChecked = isAdult
        }

        binding.btnNotification.setOnClickListener {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),0)

                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return@setOnClickListener
            }
            Log.d("Notification", "Clecked on button and if condition passes")

            notificationManager.notify(NOTIFICATION_ID,builder.build())
        }

    }
}