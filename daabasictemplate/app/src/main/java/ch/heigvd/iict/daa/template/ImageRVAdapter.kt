package ch.heigvd.iict.daa.template

import android.graphics.Bitmap
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class ImageRVAdapter(_items : List<Bitmap> = listOf()) : RecyclerView.Adapter<ImageRVAdapter.ViewHolder>() {
    var items = listOf<Bitmap>()
        set(value) {
            val diffCallback = ImageDiffCallback(items, value)
            val diffItems = DiffUtil.calculateDiff(diffCallback)
            field = value
            diffItems.dispatchUpdatesTo(this)
        }

    init {
        items = _items
    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    companion object {
        private val IMAGE = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageRVAdapter.ViewHolder {

    }

    override fun onBindViewHolder(holder: ImageRVAdapter.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {

    }


}


class ImageDiffCallback(private val oldList: List<Bitmap>, private val newList: List<Bitmap>) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size
    override fun getNewListSize() = newList.size
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList.get(newItemPosition)
    }
    override fun areContentsTheSame(oldItemPosition : Int, newItemPosition : Int): Boolean {
        val old = oldList[oldItemPosition]
        val new = newList[newItemPosition]
        return old::class == new::class
    }
}