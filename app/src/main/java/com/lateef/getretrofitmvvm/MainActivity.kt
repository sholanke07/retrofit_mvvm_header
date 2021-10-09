package com.lateef.getretrofitmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.lateef.getretrofitmvvm.Repository.Repositoty
import com.lateef.getretrofitmvvm.adapter.MyAdapter
import com.lateef.getretrofitmvvm.model.Post
import com.lateef.getretrofitmvvm.viewModel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel : MainViewModel
    private val myAdapter by lazy { MyAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRecyclerView()

        val repository = Repositoty()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        //we are using this getCustomMultiplePost to display in recyclerView
        viewModel.getCustomMultiplePosts(5, "id", "desc")
        viewModel.myCustomMultiplePost.observe(this, Observer { response->
            if (response.isSuccessful){
                response.body()?.let {myAdapter.setData(it)}
            }else{
                Toast.makeText(this, response.code(), Toast.LENGTH_SHORT).show()
            }

        })

        // posting to server
        val myPost = Post(2, 2, "lateef", "software developer")
        viewModel.pushPost(myPost)
        viewModel.newResponse.observe(this, Observer { response->
            if (response.isSuccessful) {
                Log.d("Main", response.body().toString())
                Log.d("Main", response.body().toString())
                //Log.d("Main", response.body()?.title!!)
               // Log.d("Main", response.body()?.body!!)
                Log.d("Main", response.message())
                textView2.text = response.body().toString()

            }else {
                Log.d("Main", response.code().toString())
            }
        })

        //to post to server
        viewModel.pushPost2(2, 2,"sholanke","developer")
        viewModel.encodeResponse.observe(this, Observer { response->
            if (response.isSuccessful) {
                Log.d("Main2", response.body().toString())
                Log.d("Main2", response.body().toString())
                //Log.d("Main", response.body()?.title!!)
                // Log.d("Main", response.body()?.body!!)
                Log.d("Main2", response.message())
                textView1.text = response.body().toString()

            }else {
                Log.d("Main", response.code().toString())
            }
        })

        //getting request from server
        //adding header denamically
        viewModel.getPost("11112222")
        viewModel.myResponse.observe(this, Observer { response->
            if (response.isSuccessful) {
                Log.d("Response", response.body()?.userId.toString())
                Log.d("Response", response.body()?.id.toString())
                Log.d("Response", response.body()?.title!!)
                Log.d("Response", response.body()?.body!!)
                 textView.text = response.body()?.title

                   //adding header to get request
                   Log.d("Response", response.body().toString())
                Log.d("Response", response.headers().toString())

            }else{
                Log.d("Response", response.errorBody().toString())
               // textView.text = response.code().toString()
            }
        })

        btn.setOnClickListener {
            //using to get result from server

           /* val myNumber = num_edit.text.toString()
            viewModel.getPost2(Integer.parseInt(myNumber))
            viewModel.myResponse2.observe(this, Observer { response->
                if (response.isSuccessful) {
                    textView4.text = response.body().toString()
                    Log.d("Main3", response.body().toString())
                }else{
                    textView4.text = response.code().toString()
                }
            })*/

            // using custom query to get list of post from server

           /* val myNumber = num_edit.text.toString()
            viewModel.getCustomPosts(Integer.parseInt(myNumber))
            viewModel.myCustomPost.observe(this, Observer { response->
                if (response.isSuccessful) {
                    textView4.text = response.body().toString()
                    response.body()?.forEach {
                        Log.d("CustomPost", it.userId.toString())
                        Log.d("CustomPost", it.id.toString())
                        Log.d("CustomPost", it.title)
                        Log.d("CustomPost", it.body)
                        Log.d("CustomPost", "..............")

                    }
                }else{
                    textView4.text = response.code().toString()
                }
            })*/

            //using multiple query to get list post from server by sorting and by order
            // the order will be descending order will be desc while accesding will be asc

            /*val myNumber = num_edit.text.toString()
            viewModel.getCustomMultiplePosts(Integer.parseInt(myNumber), "id", "asc")
            viewModel.myCustomMultiplePost.observe(this, Observer { response->
                if (response.isSuccessful) {
                    textView4.text = response.body().toString()
                    response.body()?.forEach {
                        Log.d("CustomPost", it.userId.toString())
                        Log.d("CustomPost", it.id.toString())
                        Log.d("CustomPost", it.title)
                        Log.d("CustomPost", it.body)
                        Log.d("CustomPost", "..............")

                    }
                }else{
                    textView4.text = response.code().toString()
                }
            })*/

            // using query map to reduce the number of preramiters, and to use it to add multiple queries
            val options: HashMap<String, String> = HashMap()
            options["_sort"] = "id"
            options["_order"] = "desc"
            val myNumber = num_edit.text.toString()
            viewModel.getCustomQueryMap(Integer.parseInt(myNumber), options)
            viewModel.myCustomQueryMapPost.observe(this, Observer { response->
                if (response.isSuccessful) {
                    textView4.text = response.body().toString()
                    response.body()?.forEach {
                        Log.d("CustomPost", it.userId.toString())
                        Log.d("CustomPost", it.id.toString())
                        Log.d("CustomPost", it.title)
                        Log.d("CustomPost", it.body)
                        Log.d("CustomPost", "..............")

                    }
                }else{
                    textView4.text = response.code().toString()
                }
            })
        }
    }

    //setting up recyclerview
    fun setupRecyclerView(){
        recycler.adapter = myAdapter
        recycler.layoutManager = LinearLayoutManager(this)
    }
}
