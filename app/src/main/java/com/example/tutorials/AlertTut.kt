package com.example.tutorials

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tutorials.databinding.ActivityAlertTutBinding
import com.example.tutorials.databinding.ActivityMainBinding

class AlertTut : AppCompatActivity() {
    private lateinit var binding: ActivityAlertTutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAlertTutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val addContactDialog = AlertDialog.Builder(this)
            .setTitle("Add contact")
            .setMessage("Do you want to add Mr.Poop?")
            .setIcon(R.drawable.ic_add_contact)
            .setPositiveButton("Yes"){ _,_ ->
                Toast.makeText(this, "You added Mr poop", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("No"){_,_ ->
                Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show()
            }.create()

        binding.btnDialog1.setOnClickListener{
            addContactDialog.show()
        }

        val option = arrayOf("First", "Second", "Third")
        val singleChooseDialog = AlertDialog.Builder(this,)
            .setTitle("Choose One")
            .setSingleChoiceItems(option,0){dialogInterface , i ->
                Toast.makeText(this, "You clicked on ${option[i]}", Toast.LENGTH_SHORT).show()
            }
            .setIcon(R.drawable.ic_favorite)
            .setPositiveButton("Yes"){ _,_ ->
                Toast.makeText(this, "You added Mr poop", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("No"){_,_ ->
                Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show()
            }.create()

        binding.btnDialog2.setOnClickListener{
            singleChooseDialog.show()
        }

        val multiChooseDialog = AlertDialog.Builder(this,)
            .setTitle("Choose One")
            .setMultiChoiceItems(option, booleanArrayOf(false, false, false)){_,i, isChecked ->
                if(isChecked) Toast.makeText(this, "You checked ${option[i]}", Toast.LENGTH_SHORT).show()
            }
            .setIcon(R.drawable.ic_favorite)
            .setPositiveButton("Yes"){ _,_ ->
                Toast.makeText(this, "Yes multichoice", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("No"){_,_ ->
                Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show()
            }.create()

        binding.btnDialog3.setOnClickListener{
            multiChooseDialog.show()
        }
    }
}