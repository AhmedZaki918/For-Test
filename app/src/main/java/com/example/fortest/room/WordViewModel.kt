package com.example.fortest.room

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class WordViewModel(private val repo: WordRepo) : ViewModel() {

    val allWords: LiveData<List<Word>> = repo.allwords.asLiveData()

    fun insert(word: Word) = viewModelScope.launch {
        repo.insert(word)
    }


    class WordViewModelFactory(private val repo: WordRepo) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(WordViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return WordViewModel(repo) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}