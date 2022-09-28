package com.killerbee.apigateway.microservices

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Component
import java.net.URI
import java.net.http.HttpRequest
import java.net.http.HttpRequest.BodyPublishers
import java.net.http.HttpResponse.BodyHandlers

@Component
class FrisbeeMicroservice() : AbstractMicroservice() {

    @Value("ms.frisbee.url")
    override lateinit var url: String


    override fun forward(body: String?, method: String, path: String): String {
        var request = HttpRequest.newBuilder(URI.create("$url/$path"))
        request = if(body != null) request.method(method.uppercase(), BodyPublishers.ofString(body)) else request.method(method.uppercase(), null)
        request = request.header("Content-Type", "application/json")
        val resp = client.send(request.build(), BodyHandlers.ofString())
        return resp.body()
    }
}