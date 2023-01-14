package github.owlmail.networking

import okhttp3.Interceptor
import okhttp3.Response

class AuthIntercepter : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newRequest = request.newBuilder().addHeader(
            "Cookie",
            "ZM_AUTH_TOKEN=0_176565691e5969ef19251ca9daf0152e749eb24a_69643d33363a33386132363661312d353834332d343233652d393461372d3236373436313565643766393b6578703d31333a313637333736313238373230333b747970653d363a7a696d6272613b7469643d393a3136373539353933383b76657273696f6e3d31333a382e362e305f47415f313135333b637372663d313a313b"
        ).build()
        return chain.proceed(newRequest)
    }
}