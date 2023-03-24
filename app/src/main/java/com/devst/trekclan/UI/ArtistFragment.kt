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
import com.devst.trekclan.dataapi.ArtistAdapter
import com.devst.trekclan.dataapi.ArtistDataClasses.Artist
import com.devst.trekclan.dataapi.ArtistDataClasses.TracksParent
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URLa = "https://ws.audioscrobbler.com/"


class ArtistFragment : Fragment() {
    var selectedTrackName: String? = ""
    val list_artist = ArrayList<Artist>()
    val rv_adapteratist = ArtistAdapter(list_artist)
    var sizea = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_artist, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val rv_artist = requireView().findViewById<RecyclerView>(R.id.rv_artist)
        rv_artist.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        rv_artist.adapter = rv_adapteratist
        rv_adapteratist.setOnitemclick(object : ArtistAdapter.onItemClickListener {
            override fun onitemclickListner(position: Int) {
                val intent = Intent(requireContext(), FinalActivity::class.java)
                intent.putExtra("title", list_artist[position].name)
                intent.putExtra("decr", list_artist[position].streamable)
                intent.putExtra("fragment_name", "ArtistFragment")
                startActivity(intent)
            }

        })



        selectedTrackName = arguments?.getString("trackname")
        //     Toast.makeText(requireContext(),"$selectedTrackName",Toast.LENGTH_SHORT).show()
        getListArtist()
    }

    interface Artis {


        //  https://ws.audioscrobbler.com/2.0/?method=tag.gettopartists&tag=disco&api_key=db04f23a1f0d3f32f124631af1624cfe
        @GET("2.0/?method=tag.gettopartists")
        fun getTopArtist(
            @Query("tag") tag: String,
            @Query("api_key") api_key: String,
            @Query("format") format: String
        ): Call<TracksParent>

    }

    object ArtistFetch {
        val AlbunsInstance: Artis

        init {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URLa)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            AlbunsInstance = retrofit.create(Artis::class.java)
        }
    }

    private fun getListArtist() {
        val tracks =
            ArtistFetch.AlbunsInstance.getTopArtist(selectedTrackName.toString(), API_KEY, "json")
        tracks.enqueue(object : retrofit2.Callback<TracksParent> {
            override fun onResponse(call: Call<TracksParent>, response: Response<TracksParent>) {
                if (response != null) {
                    val dataa = response.body()
                    val items_artist = dataa?.topartists?.artist?.forEach {
                        list_artist.add(it)
                        //     Toast.makeText(requireContext(),"${it.name}",Toast.LENGTH_SHORT).show()
                        rv_adapteratist.notifyDataSetChanged()
                    }
                }
            }

            override fun onFailure(call: Call<TracksParent>, t: Throwable) {
                Log.d("artist", "${t.message}")
            }


        })
    }


    companion object {


        @JvmStatic
        fun newInstance() = ArtistFragment()
    }
}