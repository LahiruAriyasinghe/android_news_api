package com.iit.news_api.activities

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.iit.news_api.R
import com.iit.news_api.adapters.UsersRecyclerAdapter
import com.iit.news_api.model.User
import com.iit.news_api.sql.DatabaseHelper
import okhttp3.*
import java.io.IOException


class HomeActivity : AppCompatActivity() {
    private val activity = this@HomeActivity
    private val client = OkHttpClient()
    private lateinit var recyclerViewUsers: RecyclerView
    private lateinit var listUsers: MutableList<User>
    private lateinit var usersRecyclerAdapter: UsersRecyclerAdapter
    private lateinit var databaseHelper: DatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        supportActionBar!!.title = ""
        initViews()
        initObjects()
    }
    /**
     * This method is to initialize views
     */
    private fun initViews() {
        recyclerViewUsers = findViewById<RecyclerView>(R.id.recyclerViewUsers)
    }
    /**
     * This method is to initialize objects to be used
     */
    private fun initObjects() {
        listUsers = ArrayList()
        usersRecyclerAdapter = UsersRecyclerAdapter(listUsers)
        val mLayoutManager = LinearLayoutManager(applicationContext)
        recyclerViewUsers.layoutManager = mLayoutManager
        recyclerViewUsers.itemAnimator = DefaultItemAnimator()
        recyclerViewUsers.setHasFixedSize(true)
        recyclerViewUsers.adapter = usersRecyclerAdapter

        databaseHelper = DatabaseHelper(activity)

        val getDataFromSQLite = GetDataFromSQLite()
        getDataFromSQLite.execute()


        val GetDataFromNewsApi = GetDataFromNewsApi()
        Log.d("xyz","user list 1 $GetDataFromNewsApi")

        val request = Request.Builder()
            .url("https://newsapi.org/v2/everything?q=tesla&from=2022-07-24&sortBy=publishedAt&apiKey=8dae8ce725e84382b5363470d7c25ec8")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {}
            override fun onResponse(call: Call, response: Response) {
                println(response.body()?.string())
            }
        })

    }
    /**
     * This class is to fetch all user records from SQLite
     */
    @SuppressLint("StaticFieldLeak")
    inner class GetDataFromSQLite : AsyncTask<Void, Void, List<User>>() {
        override fun doInBackground(vararg p0: Void?): List<User> {
            return databaseHelper.getAllUser()
        }
        override fun onPostExecute(result: List<User>?) {
            super.onPostExecute(result)
            listUsers.clear()
            listUsers.addAll(result!!)
        }
    }

    /**
     * This class is to fetch all user records from SQLite
     */
    @SuppressLint("StaticFieldLeak")
    inner class GetDataFromNewsApi : AsyncTask<Void, Void, List<User>>() {
        override fun doInBackground(vararg p0: Void?): List<User>? {
            Log.d("xyz","Here")
            val request = Request.Builder()
                .url("https://newsapi.org/v2/everything?q=tesla&from=2022-07-24&sortBy=publishedAt&apiKey=8dae8ce725e84382b5363470d7c25ec8")
                .build()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {}
                override fun onResponse(call: Call, response: Response) {
                    println(response.body()?.string())
                }
            })
            return null
        }
        override fun onPostExecute(result: List<User>?) {
            super.onPostExecute(result)
            listUsers.clear()
            listUsers.addAll(result!!)
        }
    }
}

private operator fun <E> ArrayList<E>.invoke() {

}
