package com.example.shortnews.Screens.MainScreen

import androidx.lifecycle.ViewModel
import com.example.shortnews.Components.DataOrException
import com.example.shortnews.Models.News
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {

    suspend fun getTopNews(country : String) : DataOrException<News,Boolean,Exception>{
        return repository.getTopNews(country)
    }
}