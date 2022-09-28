package com.killerbee.apigateway.controllers

import com.killerbee.apigateway.microservices.FrisbeeMicroservice
import com.killerbee.apigateway.models.Frisbee
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.configurationprocessor.json.JSONObject
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

@RestController
class FrisbeeController {

    @Autowired
    private lateinit var frisbeeMs: FrisbeeMicroservice

    @GetMapping("/frisbee")
    fun getAllFrisbees(): ResponseEntity<Frisbee> {
        val resp = frisbeeMs.forward(null, "GET", "all")
        val json = JSONObject(resp)
        return ResponseEntity.ok(Frisbee(json.getString("name"), json.getString("description"), json.getString("PUHT"),
            json.getString("gamme"), json.getString("ingredients")))
    }
}