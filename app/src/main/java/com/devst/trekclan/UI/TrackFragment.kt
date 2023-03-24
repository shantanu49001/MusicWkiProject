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
import com.devst.trekclan.UI.FinalActivity
import com.devst.trekclan.dataapi.API_KEY
import com.devst.trekclan.dataapi.TrackAdapter
import com.devst.trekclan.dataapi.TracksDataClasses.TrackLow
import com.devst.trekclan.dataapi.TracksDataClasses.TrackTopLevel
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


const val BASE_URL_TR="https://ws.audioscrobbler.com/"

class TrackFragment : Fragment() {

    var stledtr:String?=""
    val list_tracks=ArrayList<TrackLow>()
    val rv_adapter_tr= TrackAdapter(list_tracks)
    var size_tr=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_track, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val rv_tr=requireView().findViewById<RecyclerView>(R.id.rv_track)
        rv_tr.layoutManager= LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
        rv_tr.adapter=rv_adapter_tr
        rv_adapter_tr.setOnitemclick(object : TrackAdapter.onItemClickListener{
            override fun onitemclickListner(position: Int) {
                val intent= Intent(requireContext(), FinalActivity::class.java)
                intent.putExtra("title",list_tracks[position].name)
                intent.putExtra("decr",list_tracks[position].artist.name)
                intent.putExtra("fragment_name","TrackFragment")
                startActivity(intent)
            }

        })

      stledtr=arguments?.getString("trackname")
   //     Toast.makeText(requireContext(),"$stledtr",Toast.LENGTH_SHORT).show()

        getListTracks()
    }



    interface Tracks{
        //https://ws.audioscrobbler.com/2.0/?method=tag.gettoptracks&tag=disco&api_key=db04f23a1f0d3f32f124631af1624cfe&format=json
      //base url before thus -->this is the end point after this the params
        @GET("2.0/?method=tag.gettoptracks")                                                                          //the data class that is overal enclosed
        fun getAllTopTracks(@Query("tag") tag:String,@Query("api_key")api_key:String,@Query("format")format:String):retrofit2.Call<TrackTopLevel>
    }
    object TracksFetch{
        val TracksInstance:Tracks       //interface
        init {
            val retrofit=Retrofit.Builder()
                .baseUrl(BASE_URL_TR)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            TracksInstance=retrofit.create(Tracks::class.java)  //interface
        }
    }

    private fun getListTracks() {
                      //obj        interf        funcoon
      val tracks_data=TracksFetch.TracksInstance.getAllTopTracks(stledtr.toString(),API_KEY,"json")
        tracks_data.enqueue(object :Callback<TrackTopLevel>{
            override fun onResponse(call: retrofit2.Call<TrackTopLevel>, response: Response<TrackTopLevel>) {
              if (response!=null){
                  val data_tr=response.body()
                  val items_tr_to_add=data_tr?.tracks?.track?.forEach {
                      list_tracks.add(it)
                   //   Toast.makeText(requireContext(),"${it.artist}",Toast.LENGTH_SHORT).show()
                      rv_adapter_tr.notifyDataSetChanged()
                  }
              }
            }

            override fun onFailure(call: retrofit2.Call<TrackTopLevel>, t: Throwable) {  //to do is to be removed
              Log.d("tracks","${t.message}")
            }

        })
    }


    companion object {

        @JvmStatic
        fun newInstance() = TrackFragment()
    }
}