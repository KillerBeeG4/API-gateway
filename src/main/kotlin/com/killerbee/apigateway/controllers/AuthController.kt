package com.killerbee.apigateway.controllers

import com.killerbee.apigateway.microservices.AuthMicroservice
import com.killerbee.apigateway.models.LoginResponse
import com.killerbee.apigateway.utils.DecryptBody
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.configurationprocessor.json.JSONObject
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import java.util.*
import javax.servlet.http.HttpServletRequest

@RestController
class AuthController {

    @Autowired
    private lateinit var authMs: AuthMicroservice

    @Autowired
    private lateinit var decryptComp: DecryptBody

    @PostMapping("/auth")
    fun auth(request: HttpServletRequest, @RequestBody body: String) : ResponseEntity<Any> {
        val authResp = authMs.forward(body, request.method, "auth")
        return if(authResp == "ok") {
            ResponseEntity.ok(LoginResponse(true))
        } else {
            ResponseEntity.notFound().build()
        }
    }
}