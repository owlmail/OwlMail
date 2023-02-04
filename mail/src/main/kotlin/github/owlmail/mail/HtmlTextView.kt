package github.owlmail.mail

import android.content.Context
import android.util.AttributeSet
import androidx.core.text.HtmlCompat
import coil.ImageLoader
import com.google.android.material.textview.MaterialTextView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

// @NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes
@AndroidEntryPoint
class HtmlTextView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : MaterialTextView(context, attributeSet, defStyleAttr, defStyleRes) {
    @Inject
    lateinit var imageLoader: ImageLoader

    fun setHtml(html: String) {
        text =
            HtmlCompat.fromHtml(
                html,
                HtmlCompat.FROM_HTML_MODE_LEGACY,
                HtmlCoilImageLoader(this, imageLoader),
                null
            )
    }
}
