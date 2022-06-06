package uz.direction.coroutineshomework

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var memesPresenter: MemesPresenter

    private val diContainer = DiContainer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = layoutInflater.inflate(R.layout.activity_main, null) as MemeView
        setContentView(view)

        memesPresenter = MemesPresenter(diContainer.memesService, this)
        view.presenter = memesPresenter
        memesPresenter.attachView(view)
        memesPresenter.onInitComplete()
    }

    override fun onStop() {
        if (isFinishing) {
            memesPresenter.detachView()
        }
        super.onStop()
    }
}
