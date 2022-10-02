package github.owlmail.networking.provider

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import github.owlmail.networking.state.NetworkState
import github.owlmail.networking.usecase.NetworkStateFlow
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

class NetworkStateFlowProvider(context: Context) : NetworkStateFlow {

    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private val networkRequest =
        NetworkRequest.Builder().addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET).build()

    private val networkStateFlow = callbackFlow {
        val networkStateCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onCapabilitiesChanged(
                network: Network,
                networkCapabilities: NetworkCapabilities
            ) {
                val hasInternet =
                    networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                trySend(
                    if (hasInternet) {
                        NetworkState.Available
                    } else {
                        NetworkState.Unavailable
                    }
                )
            }
        }
        connectivityManager.registerNetworkCallback(networkRequest, networkStateCallback)

        awaitClose { connectivityManager.unregisterNetworkCallback(networkStateCallback) }
    }

    override fun invoke() = networkStateFlow
}
