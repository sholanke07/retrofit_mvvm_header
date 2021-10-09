package com.lateef.getretrofitmvvm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.lateef.getretrofitmvvm.R
import com.lateef.getretrofitmvvm.model.Post
import kotlinx.android.synthetic.main.layout_row.view.*
import okio.blackholeSink

class MyAdapter: RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    private var myList = emptyList<Post>()

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_row, parent, false))
    }

    override fun getItemCount(): Int {
        return myList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.text1.text = myList[position].userId.toString()
        holder.itemView.text2.text = myList[position].id.toString()
        holder.itemView.text3.text = myList[position].title
        holder.itemView.text4.text = myList[position].body
    }
    fun setData(newList: List<Post>){
        myList = newList
        notifyDataSetChanged()
    }
}