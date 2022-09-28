package com.killerbee.apigateway.microservices

import org.springframework.http.HttpMethod
import java.net.http.HttpClient

abstract class AbstractMicroservice {

    protected val client = HttpClient.newHttpClient()

    abstract val url: String

    abstract fun forward(body: String?, method: String, path: String) : String
}