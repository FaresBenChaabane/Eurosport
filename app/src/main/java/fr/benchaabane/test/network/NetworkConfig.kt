package fr.benchaabane.test.network


class NetworkConfig private constructor(val baseUrl: String,
                                        val connectionTimeOut: Long = 10 * SECOND,
                                        val socketTimeOut: Long = connectionTimeOut) {

    companion object {

        val PROD = NetworkConfig(baseUrl = "https://extendsclass.com/")

        val REGULAR = NetworkConfig(baseUrl = "https://extendsclass.com/",
                                    connectionTimeOut = 30 * SECOND)

        const val CACHE_SIZE = 10 * MEGA


    }
}

fun networkConfig(env: String) = when (env) {
    "prod" -> NetworkConfig.PROD
    else -> NetworkConfig.REGULAR
}

private const val SECOND = 1000L
private const val MEGA = 1024 * 1024L

