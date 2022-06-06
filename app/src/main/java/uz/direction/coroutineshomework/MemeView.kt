package uz.direction.coroutineshomework

import android.content.Context
import android.util.AttributeSet
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target

class MemeView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr), IMemeView {

    var presenter: MemesPresenter? = null

    override fun onFinishInflate() {
        super.onFinishInflate()
        findViewById<Button>(R.id.button).setOnClickListener {
            presenter?.onInitComplete()
        }
    }

    override fun populate(meme: Meme) {
        findViewById<TextView>(R.id.caption).text = meme.caption
        val img = findViewById<ImageView>(R.id.img_meme)
        Glide.with(context).load(meme.image).into(img)
    }
}

interface IMemeView {

    fun populate(meme: Meme)
}
