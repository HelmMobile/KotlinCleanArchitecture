package cat.helm.basearchitecture.model.exceptions

/**
 * Created by Borja on 19/5/17.
 */
sealed class NetworkException : Exception() {
    class NoInternetConnection : NetworkException()
    class ServerException : NetworkException()
    class UnauthorizedException : NetworkException()

}
