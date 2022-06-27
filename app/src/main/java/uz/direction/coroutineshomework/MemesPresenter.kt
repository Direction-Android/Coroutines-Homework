package uz.direction.coroutineshomework

import android.content.Context
import android.util.Log
import kotlinx.coroutines.*

val presenterScope =
    CoroutineScope(
        Dispatchers.Main +
                SupervisorJob() +
                CoroutineExceptionHandler { _, exeption ->
                    Log.e("Exception", "Exception $exeption is caught")
                }
                + CoroutineName("PresenterCoroutine"))

class MemesPresenter constructor(
    private val memesService: MemesService,
    private val context: Context,
) {
    private var _memeView: IMemeView? = null

    fun onInitComplete() {
        presenterScope.launch {
            _memeView?.populate(memesService.subscribeToMemes(presenterScope, context))
        }
    }

    fun attachView(memeView: IMemeView) {
        _memeView = memeView
    }

    fun detachView() {
        _memeView = null
        presenterScope.cancel()
    }

}
