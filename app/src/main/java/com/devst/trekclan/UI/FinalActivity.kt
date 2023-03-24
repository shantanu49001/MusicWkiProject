package com.devst.trekclan.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.devst.trekclan.R
import com.devst.trekclan.databinding.ActivityFinalBinding

class FinalActivity : AppCompatActivity() {

    lateinit var binding_final: ActivityFinalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding_final = ActivityFinalBinding.inflate(layoutInflater)
        val view = binding_final.root
        setContentView(view)
        supportActionBar?.hide()

        val TopTile = intent.getStringExtra("title")
        val description = intent.getStringExtra("decr")
        val fragmentName = intent.getStringExtra("fragment_name")


        binding_final.finalTitle.text = TopTile
        binding_final.finaltitle.text = description
        when (fragmentName) {
            "AlbumFragment" -> Glide.with(this).load(R.drawable.onem).into(binding_final.finalimage)
            "ArtistFragment" -> Glide.with(this).load(R.drawable.fivem)
                .into(binding_final.finalimage)
            "TrackFragment" -> Glide.with(this).load(R.drawable.twom).into(binding_final.finalimage)
        }

        binding_final.buttonBack.setOnClickListener {
            val intent_Genreactivty = Intent(this@FinalActivity, HomeActivity::class.java)
            startActivity(intent_Genreactivty)
        }

    }
}