package com.iit.news_api.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.iit.news_api.R
import com.iit.news_api.model.User

class NewsRecyclerAdapter(private val listUsers: List<User>) : RecyclerView.Adapter<NewsRecyclerAdapter.UserViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        // inflating recycler item view
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_user_recycler, parent, false)
        return UserViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.textViewName.text = listUsers[position].name
        holder.textViewEmail.text = listUsers[position].email
        holder.textViewPassword.text = listUsers[position].password
    }
    override fun getItemCount(): Int {
        return listUsers.size
    }
    /**
     * ViewHolder class
     */
    inner class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var textViewName: AppCompatTextView
        var textViewEmail: AppCompatTextView
        var textViewPassword: AppCompatTextView
        init {
            textViewName = view.findViewById(R.id.textViewName) as AppCompatTextView
            textViewEmail = view.findViewById(R.id.textVNewsAdapteriewEmail) as AppCompatTextView
            textViewPassword = view.findViewById(R.id.textViewPassword) as AppCompatTextView
        }
    }
}