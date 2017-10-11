package cat.helm.basearchitecture.data.network

import cat.helm.basearchitecture.Result
import cat.helm.basearchitecture.model.exceptions.NetworkException
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken
import retrofit2.Response

/**
 * Created by Borja on 2/6/17.
 */

inline fun <reified T> Response<JsonElement>.parseJsonResponse(jsonObject: String = ""): T {
    val gson = Gson()
    val type = object : TypeToken<T>() {}.type

    val jsonResponse: JsonElement? = if (jsonObject.isEmpty()) {
        this.body()
    } else {
        this.body()?.asJsonObject?.get(jsonObject)
    }

    return gson.fromJson(jsonResponse, type)

}

inline fun <reified T> Response<JsonElement>.parseResponse(jsonObject: String = ""): Result<T, Exception> =
        if (this.isSuccessful) {
            Result.of {
                this.parseJsonResponse<T>(jsonObject)
            }
        } else {
            Result.Failure(getExceptionFromHttpErrorCode(this.code()))
        }

fun getExceptionFromHttpErrorCode(code: Int): Exception = when (code) {
    401 -> NetworkException.UnauthorizedException()
    500 -> NetworkException.ServerException()
    else -> Exception()
}
