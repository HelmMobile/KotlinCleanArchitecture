package cat.helm.basearchitecture

/**
 * Created by Borja on 4/1/17.
 */
sealed class Result {
    class Success<out T>(val result: T) : Result()
    class Error: Result()
}