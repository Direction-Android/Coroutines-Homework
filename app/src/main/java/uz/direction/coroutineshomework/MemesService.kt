package uz.direction.coroutineshomework

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import kotlinx.coroutines.*
import java.io.InputStream
import java.nio.charset.Charset
import kotlin.random.Random

class MemesService {
    val myCoroutineScope = CoroutineScope(
            Dispatchers.IO +
                    CoroutineName("MemesServiceCoroutine") +
                    CoroutineExceptionHandler { _, exception ->
                        Log.e("EX", "Exception \"$exception\" is caught")
                    })

    suspend fun subscribeToMemes(context: Context): Meme {
        val meme = myCoroutineScope.async {
            val inputStream: InputStream = context.assets.open("facts.json")
            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            val jsonString = String(buffer, Charset.defaultCharset())

            val memes = Gson().fromJson(jsonString, Array<Meme>::class.java)
            val randomIndex = Random.nextInt(memes.size - 1)
            return@async memes[randomIndex]
        }
        return meme.await()
    }

}

interface MemesListener {

    fun onSuccess(meme: Meme)

    fun onError(exception: Exception)

}
