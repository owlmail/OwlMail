package github.owlmail.app.initializer

import android.content.Context
import androidx.startup.Initializer
import com.facebook.stetho.Stetho
import github.owlmail.app.BuildConfig

class StethoInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(context)
        }
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return emptyList()
    }
}