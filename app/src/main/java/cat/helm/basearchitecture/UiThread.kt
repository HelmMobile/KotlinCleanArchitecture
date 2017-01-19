package cat.helm.basearchitecture

import android.os.Handler
import android.os.Looper
import cat.helm.basearchitecture.async.PostExecutionThread

/**
 * Created by Borja on 3/1/17.
 */
class UiThread constructor(val handler: Handler = Handler(Looper.getMainLooper())) : PostExecutionThread
{
    override fun <T> submit(function: () -> T?){
        handler.post {
            function.invoke()
        }
    }

}