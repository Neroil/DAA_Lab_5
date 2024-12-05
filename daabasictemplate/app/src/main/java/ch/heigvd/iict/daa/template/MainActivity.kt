package ch.heigvd.iict.daa.template

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import ch.heigvd.iict.daa.template.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var imageAdapter : ImageRVAdapter

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView = binding.recyclerView
        imageAdapter = ImageRVAdapter(this.lifecycleScope)
        recyclerView.apply{
            adapter = imageAdapter
            layoutManager = GridLayoutManager(context, 3) //TODO PAS EN HARDCODE LE 3 SVP
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)

        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }
}