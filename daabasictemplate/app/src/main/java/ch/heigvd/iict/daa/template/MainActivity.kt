package ch.heigvd.iict.daa.template

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import ch.heigvd.iict.daa.template.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var imageAdapter : ImageRVAdapter

    private lateinit var binding: ActivityMainBinding
    private lateinit var imageDownloader: ImageDownloader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var adapter = ImageRVAdapter(this.lifecycleScope, cacheDir)
        imageDownloader = ImageDownloader(cacheDir)
        val recyclerView = binding.recyclerView
        imageAdapter = ImageRVAdapter(this.lifecycleScope, cacheDir)
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

    override fun onOptionsItemSelected (item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.main_menu_refresh -> {
                lifecycleScope.launch { imageDownloader.clearCache() }
                true
            }
            else -> super.onOptionsItemSelected (item)
        }
    }
}