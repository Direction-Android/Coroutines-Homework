package uz.direction.coroutineshomework

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*

val mainScope =
    CoroutineScope(
        Dispatchers.Main +
                SupervisorJob() +
                CoroutineExceptionHandler { _, exeption ->
                    Log.e("Exection", "Exception $exeption is caught")
                }
                + CoroutineName("MainCoroutine"))

lateinit var job: Job

class MainActivity : AppCompatActivity() {

    private lateinit var memesPresenter: MemesPresenter

    private val diContainer = DiContainer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = layoutInflater.inflate(R.layout.activity_main, null) as MemeView
        setContentView(view)

        job = mainScope.launch {
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