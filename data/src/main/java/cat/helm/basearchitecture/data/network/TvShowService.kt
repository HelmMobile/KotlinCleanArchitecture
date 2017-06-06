package cat.helm.basearchitecture.data.network

import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Borja on 2/6/17.
 */
interface TvShowService {

    @GET("discover/tv/")
    fun getPopularTvShows(): Call<JsonElement>

    @GET("tv/{id}")
    fun getTvShowById(@Path("id") id: Int): Call<JsonElement>
}