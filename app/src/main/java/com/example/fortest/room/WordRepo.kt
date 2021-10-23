package com.example.fortest.room

import kotlinx.coroutines.flow.Flow

class WordRepo(private val wordDao: WordDao) {


    val allwords: Flow<List<Word>> = wordDao.getWords()


    suspend fun insert(word: Word) {
        wordDao.insert(word)
    }
}