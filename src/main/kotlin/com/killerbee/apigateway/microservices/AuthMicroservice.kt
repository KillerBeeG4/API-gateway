package com.killerbee.apigateway.microservices

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Component
import java.net.URI
import java.net.http.HttpRequest
import java.net.http.HttpRequest.BodyPublishers
import java.net.http.HttpResponse.BodyHandlers

@Component
class AuthMicroservice : AbstractMicroservice() {

    @Value("\${ms.auth.url}")
    override lateinit var url: String

    override fun forward(body: String?, method: String, path: String) : String {
        val request = HttpRequest.newBuilder(URI.create("$url/auth")).POST(BodyPublishers.ofString(body))
            .header("Content-Type", "application/json").build()
        val resp = client.send(request, BodyHandlers.ofString())
        return resp.body()
    }

}