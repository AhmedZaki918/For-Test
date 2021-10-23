package com.example.fortest.preferences

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fortest.R
import android.content.SharedPreferences
import android.view.View
import android.widget.Button
import android.widget.TextView


class UserPref : AppCompatActivity(), View.OnClickListener {

    // Views
    private lateinit var tvNum: TextView
    private lateinit var btnAdd: Button
    private lateinit var btnReset: Button


    private var count = 0
    private val KEY_NUM = "count_key"

    // Shared preferences objects
    private lateinit var preferences: SharedPreferences
    private val sharedPrefFile = "com.example.fortest.preferences"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_pref)

        initView()
        // Restore preferences
        count = preferences.getInt(KEY_NUM, 0)
        tvNum.text = count.toString()
    }


    override fun onClick(v: View) {
        val id: Int = v.id
        if (id == R.id.btn_plus) {
            count++
            tvNum.text = count.toString()
        } else if (id == R.id.btn_reset) {
            reset()
        }
    }


    override fun onPause() {
        super.onPause()
        // Shared preferences are saved here.
        preferences.edit().apply {
            putInt(KEY_NUM, count)
            apply()
        }
    }


    private fun initView() {
        tvNum = findViewById(R.id.tv_num)
        btnAdd = findViewById(R.id.btn_plus)
        btnReset = findViewById(R.id.btn_reset)
        preferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE)
        btnAdd.setOnClickListener(this)
        btnReset.setOnClickListener(this)
    }


    private fun reset() {
        count = 0
        tvNum.text = count.toString()
        // Clear preferences
        preferences.edit().apply {
            clear()
            apply()
        }
    }
}