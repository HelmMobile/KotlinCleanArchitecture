package cat.helm.basearchitecture.dependencyinjection.activity

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Named

/**
 * Created by Borja on 21/12/16.
 */

@Module
class ActivityModule(val activity: Context) {

    companion object{
        const val ACTIVITY_CONTEXT = "activityContext"
    }


    @Provides
    @Named(ACTIVITY_CONTEXT)
    fun providesActivityContext() = activity


}