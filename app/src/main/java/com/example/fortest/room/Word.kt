package com.example.fortest.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class Word(
    @PrimaryKey
    val name: String
) {


}