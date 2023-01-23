package github.owlmail.mail

import android.content.Context
import android.util.AttributeSet
import androidx.core.text.HtmlCompat
import com.google.android.material.textview.MaterialTextView

//@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes
class HtmlTextView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : MaterialTextView(context, attributeSet, defStyleAttr, defStyleRes) {
    private val imageGetter = HtmlCoilImageLoader(this)

    fun setHtml(html: String) {
        text =
        HtmlCompat.fromHtml(html, HtmlCompat.FROM_HTML_MODE_COMPACT,imageGetter, null)
    }
}