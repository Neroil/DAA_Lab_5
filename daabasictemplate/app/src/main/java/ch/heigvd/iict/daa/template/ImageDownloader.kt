package ch.heigvd.iict.daa.template

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.IOException
import java.net.URL

class ImageDownloader {

    companion object {
        private val TAG = this.javaClass.simpleName

        private suspend fun downloadImage(number: Int): ByteArray? = withContext(Dispatchers.IO) {
            try {
                val url = URL("https://daa.iict.ch/images/$number.jpg")
                url.readBytes()
            } catch (e: IOException) {
                Log.w(TAG, "Exception while downloading image", e)
                null
            }
        }

        private suspend fun decodeImage(bytes: ByteArray?): Bitmap? =
            withContext(Dispatchers.Default) {
                try {
                    BitmapFactory.decodeByteArray(bytes, 0, bytes?.size ?: 0)
                } catch (e: IOException) {
                    Log.w(TAG, "Exception while decoding image", e)
                    null
                }
            }

        suspend fun getImage(number: Int): Bitmap? {
            val bytes = downloadImage(number)
            return decodeImage(bytes)
        }
    }
}