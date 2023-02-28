package github.owlmail.mail

import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.text.Html
import android.widget.TextView
import coil.ImageLoader
import coil.request.ImageRequest

class HtmlCoilImageLoader(private val textView: TextView, private val imageLoader: ImageLoader) :
    Html.ImageGetter {

    override fun getDrawable(source: String?): Drawable {
        val drawablePlaceholder = DrawablePlaceHolder()
        val request = ImageRequest.Builder(textView.context)
            .data(source)
            .crossfade(true)
//            .placeholder(R.drawable.ic_baseline_cloud_download_24)
//            .error(R.drawable.ic_baseline_cloud_download_24)
            .target {
                drawablePlaceholder.updateDrawable(it)
                textView.text = textView.text
            }
            .build()
        imageLoader.enqueue(request)
        return drawablePlaceholder
    }

    private inner class DrawablePlaceHolder : BitmapDrawable() {

        private var drawable: Drawable? = null

        override fun draw(canvas: Canvas) {
            drawable?.draw(canvas)
        }

        fun updateDrawable(drawable: Drawable) {
            this.drawable = drawable
            val width = drawable.intrinsicWidth
            val height = drawable.intrinsicHeight
            drawable.setBounds(0, 0, width, height)
            setBounds(0, 0, width, height)
        }
    }
}
