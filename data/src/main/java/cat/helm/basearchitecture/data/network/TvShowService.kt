package cat.helm.basearchitecture.data.network

import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by Borja on 2/6/17.
 */
interface TvShowService {

    @GET("discover/tv/")
    fun getPopularTvShows(): Call<JsonElement>
}