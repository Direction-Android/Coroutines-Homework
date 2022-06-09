package uz.direction.coroutineshomework

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import kotlinx.coroutines.*

class MemesPresenter constructor(
    private val memesService: MemesService,
    private val context: Context
) {
    val myCoroutineScope =
        CoroutineScope(
            Dispatchers.Main +
                    CoroutineName("PresenterCoroutine") +
                    CoroutineExceptionHandler { _, exception ->
                        Log.e("EX", "Exception \"$exception\" is caught")
                    })
    private var _memeView: IMemeView? = null
    private val handler = Handler(Looper.getMainLooper())

    suspend fun onInitComplete() {
        val meme = myCoroutineScope.launch {
            _memeView?.populate(memesService.subscribeToMemes(context))
        }
    }

    fun attachView(memeView: IMemeView) {
        _memeView = memeView
    }

    fun detachView() {
        _memeView = null
    }

}
