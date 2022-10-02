package github.owlmail.networking

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import kotlinx.coroutines.flow.MutableStateFlow

class NetworkStateFlowProvider(context: Context) : NetworkStateFlow {

    private val networkStateFlow = MutableStateFlow<NetworkState>(NetworkState.Unknown)

    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private val networkStateCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onCapabilitiesChanged(
            network: Network,
            networkCapabilities: NetworkCapabilities
        ) {
            val hasInternet =
                networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            networkStateFlow.value = if (hasInternet) {
                NetworkState.Available
            } else {
                NetworkState.Unavailable
            }
        }
    }

    private val networkRequest =
        NetworkRequest.Builder().addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET).build()

    init {
        connectivityManager.registerNetworkCallback(networkRequest, networkStateCallback)
//        connectivityManager.unregisterNetworkCallback(networkStateCallback)
    }

    override fun invoke() = networkStateFlow
}
