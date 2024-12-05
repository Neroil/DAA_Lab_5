package ch.heigvd.iict.daa.template

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.File
import java.io.IOException
import java.net.URL

class ImageDownloader {

    private val TAG = this.javaClass.simpleName
    private var cacheDir : File;

    constructor(cache: File) {
        cacheDir = cache
    }

    private suspend fun putByteArrayInCache(number: Int, byteArray: ByteArray) = withContext(Dispatchers.IO) {
        try {
            val file = File(cacheDir, "$number.jpg")
            file.createNewFile()
            file.writeBytes(byteArray)
            Log.d(TAG, "Image $number saved in cache")
        } catch (e: IOException) {
            Log.w(TAG, "Exception while saving image in cache", e)
        }
    }

    suspend fun clearCache() = withContext(Dispatchers.IO) {
        Log.d(TAG, "Clearing cache of size: ${cacheDir.listFiles()?.size}" )
        for(file in cacheDir.listFiles()!!) {
            file.delete()
        }
    }

    private suspend fun downloadImage(number: Int): ByteArray? = withContext(Dispatchers.IO) {
        try {
            val url = URL("https://daa.iict.ch/images/$number.jpg")
            val byteArray = url.readBytes()
            putByteArrayInCache(number, byteArray)
            return@withContext byteArray
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
        val file = File(cacheDir, "$number.jpg")
        val bytes: ByteArray

        if (file.exists()) {
            bytes = file.readBytes()
        } else {
            bytes = downloadImage(number) ?: return null
        }

        return decodeImage(bytes)
    }
}