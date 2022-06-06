package uz.direction.coroutineshomework

import android.content.Context

class MemesPresenter constructor(
    private val memesService: MemesService,
    private val context: Context
) {

    private var _memeView: IMemeView? = null

    fun onInitComplete() {
        memesService.subscribeToMemes(object : MemesListener {
            override fun onSuccess(meme: Meme) {
                _memeView?.populate(meme)
            }

            override fun onError(exception: Exception) {
                CrashMonitor.trackWarning(exception.message.toString())
            }

        }, context)
    }

    fun attachView(memeView: IMemeView) {
        _memeView = memeView
    }

    fun detachView() {
        _memeView = null
    }

}
