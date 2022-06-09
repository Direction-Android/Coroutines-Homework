package uz.direction.coroutineshomework

import android.content.Context
import android.util.Log
import kotlinx.coroutines.*

val presenterScope =
    CoroutineScope(
        Dispatchers.Main +
                SupervisorJob() +
                CoroutineExceptionHandler { _, exeption ->
                    Log.e("Exection", "Exception $exeption is caught")
                }
                + CoroutineName("PresenterCoroutine"))

class MemesPresenter constructor(
    private val memesService: MemesService,
    private val context: Context,
) {

    private var _memeView: IMemeView? = null

    suspend fun onInitComplete() {
        var job = presenterScope.launch {
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
