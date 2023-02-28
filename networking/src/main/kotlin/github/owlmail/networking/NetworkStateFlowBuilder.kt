package github.owlmail.networking

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

class NetworkStateFlowBuilder(context: Context) {
    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    private val networkCallbackFlow = callbackFlow {
        val networkStateCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                trySend(NetworkState.Available)
            }

            override fun onUnavailable() {
                trySend(NetworkState.Unavailable)
            }

            override fun onLost(network: Network) {
                trySend(NetworkState.Unavailable)
            }
        }
        val networkRequest =
            NetworkRequest.Builder().addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                .build()
        connectivityManager.registerNetworkCallback(networkRequest, networkStateCallback)
        awaitClose {
            connectivityManager.unregisterNetworkCallback(networkStateCallback)
        }
    }

    operator fun invoke() = networkCallbackFlow
}
