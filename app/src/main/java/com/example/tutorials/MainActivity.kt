package com.example.tutorials

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Button
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tutorials.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    val CHANNEL_ID = "Channel id"
    val CHANNEL_NAME ="Channel name"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        createNotificationChannel()

        binding.btnApply.setOnClickListener{
            val firstName = binding.etFirstName.text.toString()
            val lastName = binding.etLastName.text.toString()
            val birthDate = binding.etBirthDate.text.toString()
            val country = binding.etCountry.text.toString()

            Log.d("MainActivity", "$firstName $lastName was born on $birthDate in $country ")
            Log.i("MainActivity", "$firstName $lastName was born on $birthDate in $country ")

        }
//        Burger Order
        binding.btnOrder.setOnClickListener{
            val checkMeatId = binding.rgMeat.checkedRadioButtonId
            val meat = findViewById<RadioButton>(checkMeatId)
            val cheese = binding.cbCheese.isChecked
            val onions = binding.cbOnions.isChecked
            val salad = binding.cbSalad.isChecked

            binding.tvResult.text="One ${meat.text} burger with" +
                    (if (cheese) "\n Cheese" else "")+
                    (if (onions) "\n Onion" else "")+
                    (if (salad) "\n Salad" else "")


        }

        binding.btnRecycleView.setOnClickListener{
            Intent(this,RecycleViewActivity::class.java).also {
                startActivity(it)
            }
        }

        val button = findViewById<Button>(R.id.btnGoInside);
        button.setOnClickListener{
            Intent(this,PermissionActivity::class.java).also {
                startActivity(it)

            }
        }

        binding.btnAlert.setOnClickListener{
            Intent(this, AlertTut::class.java).also{
                startActivity(it)
            }
        }

        binding.btnSpinner.setOnClickListener{
            Intent(this, SpinnerActivity::class.java).also{
                startActivity(it)
            }
        }

        binding.btnMenuView.setOnClickListener{
            Intent(this,MenuViewActivity::class.java).also {
                startActivity(it)
            }
        }

        binding.btnDataShared.setOnClickListener {
            Intent(this,SharedDataActivity::class.java).also {
                startActivity(it)
            }
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflate: MenuInflater = menuInflater
        menuInflate.inflate(R.menu.app_menu_bar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
         when (item.itemId){
             R.id.miSettings -> Toast.makeText(this,"You clicked on Settings!", Toast.LENGTH_SHORT).show()
         }
        return super.onOptionsItemSelected(item)
    }

    private fun createNotificationChannel() {
        if(SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT).apply {
                lightColor = Color.GREEN
                enableLights(true)
            }
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }


    }


}