package uz.direction.coroutineshomework

import android.content.Context
import android.os.SystemClock
import android.util.AttributeSet
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import kotlinx.coroutines.*

class MemeView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr), IMemeView {

    var presenter: MemesPresenter? = null

    private val myCoroutineScope =
        CoroutineScope(
            Dispatchers.IO +
                    SupervisorJob() +
                    CoroutineExceptionHandler { _, exception ->
                        Log.e("EX", "Exception \"$exception\" is caught")
                    })

    override fun onFinishInflate() {
        super.onFinishInflate()
        findViewById<Button>(R.id.button).setOnClickListener {
            myCoroutineScope.launch { presenter?.onInitComplete() }
        }
    }

    override fun populate(meme: Meme) {
        findViewById<TextView>(R.id.caption).text = meme.caption
        Log.d("TAG", "Caption = ${meme.caption}")
        val img = findViewById<ImageView>(R.id.img_meme)
        Log.d("TAG", "Image link = ${meme.image}")
        Glide.with(context).load(meme.image).into(img)
    }
}

interface IMemeView {

    fun populate(meme: Meme)
}
