package com.devst.trekclan.UI

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.devst.trekclan.R
import com.devst.trekclan.Room.HomeRepository
import com.devst.trekclan.Room.HomeViewModel
import com.devst.trekclan.Room.HomeVmFactory
import com.devst.trekclan.dataapi.*
import com.devst.trekclan.dataapi.GenreDataClasses.GenereDataParent
import com.devst.trekclan.dataapi.GenreDataClasses.Tag
import com.devst.trekclan.databinding.ActivityHomeBinding

import retrofit2.Call
import retrofit2.Response


class HomeActivity : AppCompatActivity() {
    lateinit var HomeBinding: ActivityHomeBinding
    var listgenre = ArrayList<Tag>()
    val genreAdapter = GenereHomeAdapter(listgenre)
    lateinit var mainViewModelProvider: HomeViewModel

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        HomeBinding = ActivityHomeBinding.inflate(layoutInflater)
        val view = HomeBinding.root
        setContentView(view)
        supportActionBar?.hide()
        val service = HomeGenre.tracksInstance

        //2
        val repository = HomeRepository(service)

        //1
        mainViewModelProvider =
            ViewModelProvider(this, HomeVmFactory(repository)).get(HomeViewModel::class.java)


        val image = HomeBinding.imageViewHome

        Glide.with(this).asGif().load(R.drawable.logohome).into(image)


        val rv_genere = HomeBinding.rvGenereItems
        rv_genere.layoutManager = GridLayoutManager(this, 1)
        rv_genere.adapter = genreAdapter

        var adapterposclicked: Int = 0
        //7
        genreAdapter.setOnItemClickListner(object : GenereHomeAdapter.onItemClickListner {
            override fun onItemClick(position: Int) {
                adapterposclicked = position

                val intent = Intent(this@HomeActivity, GenereActivity::class.java)
                intent.putExtra(
                    "Tag",
                    listgenre[position].name
                )   //it will sedn the positon clicked

                startActivity(intent)
            }

        })









        getList()
    }


    private fun getList() {
        val tracks = HomeGenre.tracksInstance.getTopTags()
        tracks.enqueue(object : retrofit2.Callback<GenereDataParent> {
            override fun onResponse(
                call: Call<GenereDataParent>,
                response: Response<GenereDataParent>
            ) {
                val data = response.body()
                if (data != null) {
                    val items_toadd = data.toptags.tag.forEach {
                        //    Toast.makeText(applicationContext,it.name,Toast.LENGTH_SHORT).show()
                        listgenre.add(it)



                        genreAdapter.notifyDataSetChanged()
                    }
                }
            }

            override fun onFailure(call: Call<GenereDataParent>, t: Throwable) {

            }


        })
    }


}





