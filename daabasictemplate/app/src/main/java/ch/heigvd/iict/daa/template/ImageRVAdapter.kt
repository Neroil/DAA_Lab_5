package ch.heigvd.iict.daa.template

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.Visibility
import ch.heigvd.iict.daa.template.databinding.NumberListBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File

class ImageRVAdapter(private val lifecycle: LifecycleCoroutineScope, private val cacheDir : File) : RecyclerView.Adapter<ImageRVAdapter.ViewHolder>() {

    private val imageDownloader = ImageDownloader(cacheDir)

    override fun getItemCount() = 10000

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageRVAdapter.ViewHolder {
        return ViewHolder(NumberListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    fun clearCache() {
        lifecycle.launch {
            imageDownloader.clearCache()
            notifyDataSetChanged()
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class ViewHolder(private val binding: NumberListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int){
            lifecycle.launch {
                val image = imageDownloader.getImage(position)
                withContext(Dispatchers.Main) {
                    binding.apply {
                        numberImage.setImageBitmap(image)
                        numberImage.visibility = View.VISIBLE
                        progressCircular.visibility = View.GONE
                    }
                }
            }
        }
    }


}