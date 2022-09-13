package com.example.shortnews.Screens.MainScreen

import android.util.Log
import com.example.shortnews.Components.DataOrException
import com.example.shortnews.Models.News
import com.example.shortnews.Network.NewsApi
import javax.inject.Inject

class MainRepository @Inject constructor(private val newsApi: NewsApi) {
   suspend fun getTopNews(country : String) : DataOrException<News,Boolean,Exception>{

       val response = try {
           newsApi.getTopNews(country)
       }catch ( E : Exception){
           Log.d("TAGAPI",E.toString())
           return DataOrException(e = E)
       }
       Log.d("TAGAPI",response.toString())
       return DataOrException(data = response)
   }
}