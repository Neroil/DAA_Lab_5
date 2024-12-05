package ch.heigvd.iict.daa.template

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

class ImageRVAdapter(private val lifecycle: LifecycleCoroutineScope) : RecyclerView.Adapter<ImageRVAdapter.ViewHolder>() {
    override fun getItemCount() = 10000

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageRVAdapter.ViewHolder {
        return ViewHolder(NumberListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class ViewHolder(private val binding: NumberListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int){

            lifecycle.launch {
                //withContext avant ? mis par le prof au début
                val image = ImageDownloader.getImage(position)
                withContext(Dispatchers.Main) {
                    binding.apply {
                        with(image){
                            numberImage.setImageBitmap(image)
                            numberImage.visibility = View.VISIBLE
                            progressCircular.visibility = View.GONE
                        }
                    }
                }
            }
        }
    }


}