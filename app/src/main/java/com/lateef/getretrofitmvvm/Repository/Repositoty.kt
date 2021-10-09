package com.lateef.getretrofitmvvm.Repository

import com.lateef.getretrofitmvvm.model.Post
import com.lateef.getretrofitmvvm.util.RetrofitInstance
import retrofit2.Response

class Repositoty {
    //use to get result from server
    //adding header denamically
    suspend fun getPost(auth: String): Response<Post> {
        return RetrofitInstance.api.getPost(auth)
    }

    // to get result from server
    suspend fun getPost2(number: Int): Response<Post> {
        return RetrofitInstance.api.getPost2(number)
    }

    //using custom query to get list of post
    suspend fun getCustomPosts(userId: Int): Response<List<Post>>{
        return RetrofitInstance.api.getCustomPost(userId)
    }

    // using custom query for multiple query to get list of post by sorting and by order
    suspend fun getCustomMultiplePost(userId: Int, sort: String, order: String): Response<List<Post>>{
        return RetrofitInstance.api.getCustomMultiplePost(userId, sort, order)
    }

    // using query map to reduce the number of preramiters, and to use it to add multiple queries
    suspend fun getCustomQueryMap(userId: Int, options: Map<String, String>): Response<List<Post>>{
        return RetrofitInstance.api.getCustomQueryMap(userId, options)
    }

    // to make post to server
    suspend fun pushPost(post: Post): Response<Post>{
        return RetrofitInstance.api.pushPost(post)
    }

    // to post to server
    suspend fun pushPost2(userId: Int, id: Int, title: String, body: String): Response<Post>{
        return RetrofitInstance.api.pushPost2(userId, id, title, body)
    }
}