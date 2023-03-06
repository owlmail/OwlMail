package github.owlmail.networking

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    var cookie = ""
    var csrfToken = ""
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        if (cookie.isEmpty() || csrfToken.isEmpty()) {
            return chain.proceed(request)
        }
        val newRequest = request.newBuilder().addHeader(
            "Cookie",
            "ZM_AUTH_TOKEN=$cookie"
        ).addHeader("X-Zimbra-Csrf-Token", csrfToken)
            .build()
        return chain.proceed(newRequest)
    }
}

//        @Header("X-Zimbra-Csrf-Token") csrfToken: String = "0_c9e079d6ec49938f691726c3bbb00501b57726bd",
//        @Header("Cookie") authToken: String = "ZM_AUTH_TOKEN=0_f8450d2ed32cce48c7bd0c0daa8db88f3749c281_69643d33363a33386132363661312d353834332d343233652d393461372d3236373436313565643766393b6578703d31333a313637333935313136353539323b747970653d363a7a696d6272613b7469643d393a3437343033393430333b76657273696f6e3d31333a382e362e305f47415f313135333b637372663d313a313b"
