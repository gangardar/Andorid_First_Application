package com.example.tutorials

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tutorials.databinding.ActivityMenuViewBinding

class MenuViewActivity : AppCompatActivity() {

    lateinit var binding : ActivityMenuViewBinding
    lateinit var toggle : ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMenuViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        toggle = ActionBarDrawerToggle(this, binding.drawerLayout,R.string.open, R.string.close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.nvDrawer.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.item1 -> Toast.makeText(this,"Item 1 Clicked!",Toast.LENGTH_SHORT).show()
                R.id.item2 -> Toast.makeText(this,"Item 2 Clicked!",Toast.LENGTH_SHORT).show()
                R.id.item3 -> Toast.makeText(this,"Item 3 Clicked!",Toast.LENGTH_SHORT).show()
                R.id.item4 -> Toast.makeText(this,"Item 4 Clicked!",Toast.LENGTH_SHORT).show()
            }
            true
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}