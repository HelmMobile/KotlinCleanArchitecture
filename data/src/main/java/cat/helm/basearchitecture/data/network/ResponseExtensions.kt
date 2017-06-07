package cat.helm.basearchitecture.data.network

import cat.helm.basearchitecture.Result
import cat.helm.ureentool.model.exceptions.NetworkException
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

    val jsonResponse : JsonElement?

    if (jsonObject.isEmpty()) {
        jsonResponse = this.body()
    } else {
        jsonResponse = this.body()?.asJsonObject?.get(jsonObject)
    }
    val parsedResponse: T = gson.fromJson(jsonResponse, type)
    return parsedResponse

}

inline fun <reified T> Response<JsonElement>.parseResponse(jsonObject: String = ""): Result<T, Exception> {
    if (this.isSuccessful) {
        return Result.of {
            this.parseJsonResponse<T>(jsonObject)
        }
    } else {
        return Result.Failure(getExceptionFromHttpErrorCode(this.code()))
    }
}

fun getExceptionFromHttpErrorCode(code: Int): Exception {
    when (code) {
        401 -> return NetworkException.UnauthorizedException()
        500 -> return NetworkException.ServerException()
        else -> return Exception()
    }
}
