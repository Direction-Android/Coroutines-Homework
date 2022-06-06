package uz.direction.coroutineshomework

import android.content.Context
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset
import kotlin.concurrent.thread
import kotlin.random.Random

class MemesService {

    fun subscribeToMemes(memesListener: MemesListener, context: Context) {
        thread {
            val jsonString: String = try {
                val inputStream: InputStream = context.assets.open("facts.json")
                val size: Int = inputStream.available()
                val buffer = ByteArray(size)
                inputStream.read(buffer)
                inputStream.close()
                String(buffer, Charset.defaultCharset())
            } catch (e: IOException) {
                memesListener.onError(e)
                ""
            }

            try {
                val memes = Gson().fromJson(jsonString, Array<Meme>::class.java)
                val randomIndex = Random.nextInt(memes.size - 1)
                memesListener.onSuccess(memes[randomIndex])
            } catch (e: JsonSyntaxException) {
                memesListener.onError(e)
            }
        }
    }

}

interface MemesListener {

    fun onSuccess(meme: Meme)

    fun onError(exception: Exception)

}
