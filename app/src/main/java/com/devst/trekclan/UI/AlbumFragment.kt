package com.devst.trekclan.UI

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devst.trekclan.R
import com.devst.trekclan.dataapi.API_KEY
import com.devst.trekclan.dataapi.AlbumDataClasses.Album
import com.devst.trekclan.dataapi.AlbumDataClasses.ArtistParent
import com.devst.trekclan.dataapi.AlbumAdapter
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://ws.audioscrobbler.com/"


class AlbumFragment : Fragment() {
    var selected: String? = ""
    val list_album = ArrayList<Album>()
    val rv_adapter = AlbumAdapter(list_album)
    var size = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_album, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val rv_allbum = requireView().findViewById<RecyclerView>(R.id.rv_album)
        rv_allbum.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        rv_allbum.adapter = rv_adapter
        rv_adapter.setOnitemclick(object : AlbumAdapter.onItemClickListener {
            override fun onitemclickListner(position: Int) {
                val intent = Intent(requireContext(), FinalActivity::class.java)
                intent.putExtra("title", list_album[position].name)
                intent.putExtra("decr", list_album[position].artist.name)
                intent.putExtra("fragment_name", "AlbumFragment")
                startActivity(intent)

            }

        })

        selected = arguments?.getString("trackname")


        getList()
    }


    interface Albums {


        // @GET("2.0/?method=tag.gettopalbums&tag=disco&api_key=db04f23a1f0d3f32f124631af1624cfe&format=json")
        @GET("2.0/?method=tag.gettopalbums")
        fun getTopAlbums(
            @Query("tag") tag: String,
            @Query("api_key") api_key: String,
            @Query("format") format: String
        ): Call<ArtistParent>

    }

    object AlbumsFetch {
        val AlbunsInstance: Albums

        init {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            AlbunsInstance = retrofit.create(Albums::class.java)
        }
    }

    private fun getList() {
        val tracks = AlbumsFetch.AlbunsInstance.getTopAlbums(selected.toString(), API_KEY, "json")
        tracks.enqueue(object : retrofit2.Callback<ArtistParent> {
            override fun onResponse(call: Call<ArtistParent>, response: Response<ArtistParent>) {
                if (response != null) {
                    val data = response.body()
                    val items_albums_to_add = data?.albums?.album?.forEach {
                        list_album.add(it)
                        //       Toast.makeText(requireContext(),"${it.name}",Toast.LENGTH_SHORT).show()
                        rv_adapter.notifyDataSetChanged()
                    }
                }
            }

            override fun onFailure(call: Call<ArtistParent>, t: Throwable) {
                Log.d("album", "${t.message}")
            }


        })
    }

    companion object {


        @JvmStatic
        fun newInstance() = AlbumFragment()
    }
}