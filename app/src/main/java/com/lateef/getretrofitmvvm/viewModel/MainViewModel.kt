package com.lateef.getretrofitmvvm.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lateef.getretrofitmvvm.Repository.Repositoty
import com.lateef.getretrofitmvvm.model.Post
import kotlinx.coroutines.launch
import retrofit2.Response

open class MainViewModel(private val repository: Repositoty): ViewModel() {

    val myResponse: MutableLiveData<Response<Post>> = MutableLiveData()
    val myResponse2: MutableLiveData<Response<Post>> = MutableLiveData()
    val newResponse: MutableLiveData<Response<Post>> = MutableLiveData()
    val encodeResponse: MutableLiveData<Response<Post>> = MutableLiveData()
    val myCustomPost: MutableLiveData<Response<List<Post>>> = MutableLiveData()
    val myCustomMultiplePost: MutableLiveData<Response<List<Post>>> = MutableLiveData()
    val myCustomQueryMapPost: MutableLiveData<Response<List<Post>>> = MutableLiveData()

    //to get post
    //adding header denamically
    fun getPost(auth: String){
        viewModelScope.launch {
            val response = repository.getPost(auth)
            myResponse.value = response

        }
    }

    // to get post
    fun getPost2(number: Int){
        viewModelScope.launch {
            val response = repository.getPost2(number)
            myResponse2.value = response

        }
    }

    //using custom query to get list of post
    fun getCustomPosts(userId: Int){
        viewModelScope.launch {
            val response = repository.getCustomPosts(userId)
            myCustomPost.value = response
        }
    }

    // using multiple query to get list of post by sorting and order
    fun getCustomMultiplePosts(userId: Int, sort: String, order: String){
        viewModelScope.launch {
            val response = repository.getCustomMultiplePost(userId, sort, order)
            myCustomMultiplePost.value = response
        }
    }

    // using query map to reduce the number of preramiters, and to use it to add multiple queries
    fun getCustomQueryMap(userId: Int, options: Map<String, String>){
        viewModelScope.launch {
            val response = repository.getCustomQueryMap(userId, options)
            myCustomQueryMapPost.value = response
        }
    }

    //to post to server
    fun pushPost(post: Post){
        viewModelScope.launch {
            val  response = repository.pushPost(post)
            newResponse.value = response
        }
    }

    //to post to server
    fun pushPost2(userId: Int, id: Int, title: String, body: String){
        viewModelScope.launch {
            val  response = repository.pushPost2(userId, id, title, body)
            encodeResponse.value = response
        }
    }
}
