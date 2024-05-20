package com.example.news.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.news.api.ApiUtils
import com.example.news.models.Article
import com.example.news.models.NewsAPIResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    private val api = ApiUtils.getApi()

    private val _items = MutableLiveData<List<Article>>()

    val items: LiveData<List<Article>> = _items

    val isLoading = MutableLiveData<Boolean>()

    init {
        getdata()
    }

    private fun getdata() {
        isLoading.value = true

        api.getNews("azerbaijan").enqueue(object : Callback<NewsAPIResponse> {
            override fun onResponse(call: Call<NewsAPIResponse>, response: Response<NewsAPIResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let { model ->
                        model.articles?.let {
                            isLoading.value = false
                            _items.postValue(it)
                            Log.i("HomeViewModel", "Items received: $it")
                        }
                    }
                } else {
                    Log.e("HomeViewModel", "Error: ${response.code()}")
                    isLoading.value = false
                }
            }

            override fun onFailure(call: Call<NewsAPIResponse>, t: Throwable) {
                Log.e("HomeViewModel", "Failure: ${t.message}")
                isLoading.value = false
            }
        })
    }
}
