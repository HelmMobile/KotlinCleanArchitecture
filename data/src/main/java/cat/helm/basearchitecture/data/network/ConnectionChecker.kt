package cat.helm.ureentool.data.network

import android.content.Context
import android.net.ConnectivityManager
import cat.helm.basearchitecture.data.dependencyinjection.qualifier.ApplicationContext
import javax.inject.Inject

/**
 * Created by Borja on 4/4/17.
 */
class ConnectionChecker @Inject constructor(@ApplicationContext val context: Context) {

    fun thereIsConnectivity(): Boolean {
        val systemService = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val activeNetwork = systemService.activeNetworkInfo

        if (activeNetwork == null || !activeNetwork.isConnectedOrConnecting) {
            return false
        }
        return true
    }
}