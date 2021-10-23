package com.example.fortest

import android.content.Context
import com.example.fortest.room.WordDatabase
import com.example.fortest.room.WordRepo
import com.example.fortest.room.WordViewModel

class Utils(ctx: Context) {
    private var db: WordDatabase = WordDatabase.getDatabase(ctx)
    private var repo: WordRepo = WordRepo(db.wordDao())
    var factory = WordViewModel.WordViewModelFactory(repo)
}