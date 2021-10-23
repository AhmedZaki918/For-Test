package com.example.fortest

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.fortest.room.WordAdapter
import com.example.fortest.room.WordViewModel


class SecondActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var tvMessage: TextView
    private lateinit var wordViewModel: WordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)


        initViews()
        wordViewModel.allWords.observe(this) {
            recyclerView.adapter = WordAdapter(it)
        }
        updateUi()
    }


    private fun initViews() {
        tvMessage = findViewById(R.id.tv_reply)
        recyclerView = findViewById(R.id.recycler_view)
        val factory = Utils(applicationContext).factory
        wordViewModel = ViewModelProvider(this, factory).get(WordViewModel::class.java)
    }


    private fun updateUi() {
        val message = intent.getStringExtra("KEY")
        // Put that message on TextView
        tvMessage.text = message
    }
}