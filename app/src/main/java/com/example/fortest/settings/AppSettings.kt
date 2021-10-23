package com.example.fortest.settings

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import com.example.fortest.databinding.AppSettingsBinding


class AppSettings : AppCompatActivity() {

    private lateinit var binding: AppSettingsBinding

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AppSettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        readSettings()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun readSettings() {
        val sp = PreferenceManager.getDefaultSharedPreferences(this)

        binding.apply {
            tvSignature.text = sp.getString("signature", "")
            tvReply.text = sp.getString("reply", "")
            tvSync.text = sp.getBoolean("sync", true).toString()
            tvNotifications.text = sp.getBoolean("notifications", true).toString()
            pbVolume.setProgress(sp.getInt("volume_notifications", 0), true)
        }
    }
}