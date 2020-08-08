package services.interceptors

interface TokenProvider {
    fun getToken(): String
}