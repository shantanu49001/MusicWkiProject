package com.devst.trekclan.UI
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.devst.trekclan.R
import com.devst.trekclan.UI.TrackFragment
import com.devst.trekclan.databinding.ActivityGenereBinding

class GenereActivity : AppCompatActivity() {
    var position: String? = "0"
    lateinit var genereBinding: ActivityGenereBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        genereBinding = ActivityGenereBinding.inflate(layoutInflater)
        val view = genereBinding.root
        setContentView(view)


        genereBinding.generNavigator.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_artist -> inflateFragement(ArtistFragment.newInstance())
                R.id.menu_track -> inflateFragement(TrackFragment.newInstance())
                R.id.menu_album -> inflateFragement(AlbumFragment.newInstance())

            }
            true

        }
        genereBinding.generNavigator.selectedItemId = R.id.menu_artist

        genereBinding.parentActivity.setOnClickListener {
            val inten = Intent(this@GenereActivity, HomeActivity::class.java)
            startActivity(inten)
            finish()
        }


        position = intent.getStringExtra("Tag")
        //   Toast.makeText(applicationContext,"$position",Toast.LENGTH_LONG).show()
        genereBinding.genreName.text = intent.getStringExtra("Tag")


    }

    private fun inflateFragement(newInstance: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        var bundle = Bundle()
        bundle.putString("trackname", intent.getStringExtra("Tag"))   //send to fragment
        newInstance.arguments = bundle
        fragmentTransaction.replace(R.id.genre_container, newInstance)
        fragmentTransaction.commit()


    }
}