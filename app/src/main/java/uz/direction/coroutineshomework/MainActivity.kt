package uz.direction.coroutineshomework

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private lateinit var job: Job
    private lateinit var memesPresenter: MemesPresenter

    val myCoroutineScope =
        CoroutineScope(
            Dispatchers.Main +
                    CoroutineName("MainCoroutine") +
                    CoroutineExceptionHandler { _, exception ->
                        Log.e("EX", "Exception \"$exception\" is caught")
                    })

    private val diContainer = DiContainer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = layoutInflater.inflate(R.layout.activity_main, null) as MemeView
        setContentView(view)

        job = myCoroutineScope.launch {
            memesPresenter = MemesPresenter(diContainer.memesService, applicationContext)
            view.presenter = memesPresenter
            memesPresenter.attachView(view)
            memesPresenter.onInitComplete()
        }
    }

    override fun onStop() {
        if (isFinishing) {
            memesPresenter.detachView()
            job.cancel()
        }
        super.onStop()
    }
}
