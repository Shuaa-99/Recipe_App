package com.example.recipeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var rvMain: RecyclerView
    private lateinit var rvAdapter: RVAdapter

    private lateinit var titleTxt: EditText
    private lateinit var authorTxt: EditText
    private lateinit var ingredientsTxt:EditText
    private lateinit var instructionsTxt:EditText
    private lateinit var buttonPress: Button

    private lateinit var users: Users

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        titleTxt = findViewById(R.id.etTitle)
        authorTxt = findViewById(R.id.etAuthor)
        buttonPress = findViewById(R.id.btAdd)
        ingredientsTxt = findViewById(R.id.etIngredients)
        instructionsTxt = findViewById(R.id.etInstructions)

        users = Users()
        rvMain = findViewById(R.id.rvMain)
        rvAdapter = RVAdapter(users)
        rvMain.adapter = rvAdapter
        rvMain.setHasFixedSize(true)
        rvMain.layoutManager = LinearLayoutManager(this)
        val apiInterface = RetrofitBuilder().getRetrofitBuilder()?.create(ServiceAPI::class.java)

        apiInterface?.getAPIUsers()?.enqueue(object : Callback<Users> {
            override fun onResponse(call: Call<Users>, response: Response<Users>) {
                users = response.body()!!
                rvAdapter.update(users)
            }

            override fun onFailure(call: Call<Users>, t: Throwable) {
                Log.d("MAIN", "Unable to get data")
            }
        })

        buttonPress.setOnClickListener {
            apiInterface!!.addUser(
                UsersItem(
                    authorTxt.text.toString(),
                    titleTxt.text.toString(),
                    ingredientsTxt.text.toString(),
                    instructionsTxt.text.toString(),
                    0,
                )
            ).enqueue(object : Callback<UsersItem> {
                override fun onResponse(call: Call<UsersItem>, response: Response<UsersItem>) {
                    authorTxt.text = null
                    titleTxt.text = null
                    ingredientsTxt.text = null
                    instructionsTxt.text = null
                    recreate()
                }

                override fun onFailure(call: Call<UsersItem>, t: Throwable) {
                    Log.d("MAIN", "Something went wrong!")
                }

            })

        }
    }




}