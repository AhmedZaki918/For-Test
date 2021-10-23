package com.example.fortest

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import com.example.fortest.preferences.UserPref
import com.example.fortest.databinding.ActivityMainBinding
import com.example.fortest.room.Word
import com.example.fortest.room.WordViewModel
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint
import android.content.SharedPreferences
import com.example.fortest.settings.AppSettings


private const val CHANNEL_ID = "channel_id"


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener, View.OnClickListener,
    NavigationView.OnNavigationItemSelectedListener {


    private lateinit var binding: ActivityMainBinding
    private lateinit var baseViewModel: BaseViewModel
    private lateinit var wordViewModel: WordViewModel
    private lateinit var toggle: ActionBarDrawerToggle


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        registerListener()
        createNotificationChannel()
        baseViewModel.applyWork()
        setupSpinner()

        // Navigation drawer
        toggle.syncState()
        binding.navView.bringToFront()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }


    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_save -> {
                val word = binding.etMessage.text.toString()
                wordViewModel.insert(Word(word))

                if (word == "") {
                    toast(getString(R.string.enter_mess))
                } else {
                    startActivity(SecondActivity::class.java, "KEY", word)
                }
            }

            R.id.btn_get -> {
                startActivity(SecondActivity::class.java, null, null)
            }

            R.id.special_view -> {
                toast(getString(R.string.done))
            }

            R.id.btn_user_pref -> {
                startActivity(UserPref::class.java, null, null)
            }

            R.id.floating_btn -> {
                startActivity(AppSettings::class.java, null, null)
            }


        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when {
            toggle.onOptionsItemSelected(item) -> {
                return true
            }
            id == R.id.apartment -> {
                toast(getString(R.string.appartment))
            }
            id == R.id.alarm -> {
                toast(getString(R.string.alarm))
            }
            id == R.id.settings -> {
                startActivity(SettingsActivity::class.java, null, null)
            }
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
        val phoneType = parent?.getItemAtPosition(pos)
        val phoneNumber = binding.etPhoneNumber.text
        ("$phoneNumber - $phoneType").also { binding.tvPhone.text = it }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.setting -> {
                toast(getString(R.string.settings))
            }

            R.id.home -> {
                toast(getString(R.string.home))
            }

            R.id.profile -> {
                toast(getString(R.string.profile))
            }
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }


    private fun initViews() {
        toggle = ActionBarDrawerToggle(
            this, binding.drawerLayout,
            R.string.open_drawable, R.string.close_drawable
        )
        val factory = Utils(applicationContext).factory
        wordViewModel = ViewModelProvider(this, factory).get(WordViewModel::class.java)
        baseViewModel = ViewModelProvider(this).get(BaseViewModel::class.java)
    }


    private fun setupSpinner() {
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.labels_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            binding.labelsSpinner.adapter = adapter
        }
    }


    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID, "name",
                NotificationManager.IMPORTANCE_DEFAULT
            )
                .apply {
                    description = "descriptionText"
                }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }


    private fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


    private fun registerListener() {
        binding.drawerLayout.addDrawerListener(toggle)
        binding.labelsSpinner.onItemSelectedListener = this
        binding.btnSave.setOnClickListener(this)
        binding.btnGet.setOnClickListener(this)
        binding.specialView.setOnClickListener(this)
        binding.navView.setNavigationItemSelectedListener(this)
        binding.btnUserPref.setOnClickListener(this)
        binding.floatingBtn.setOnClickListener(this)
    }


    private fun <T> startActivity(cls: Class<T>, key: String?, value: String?) {
        if (key != null && value != null) {
            Intent(this, cls).apply {
                this.putExtra(key, value)
                startActivity(this)
            }
        } else {
            Intent(this, cls).apply {
                startActivity(this)
            }
        }
    }
}