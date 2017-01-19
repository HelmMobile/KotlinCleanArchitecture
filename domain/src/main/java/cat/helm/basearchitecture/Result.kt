package cat.helm.basearchitecture

/**
 * Created by Borja on 4/1/17.
 */
sealed class Result<out T> {


    class Success<out T>(val result: T) : Result<T>(){

        override fun equals(other: Any?): Boolean = when(other){
            is Success<*> ->  result == other.result
            else -> false
        }

        override fun hashCode(): Int{
            return result?.hashCode() ?: 0
        }
    }
    class Error<out T>: Result<T>() {
    }
}