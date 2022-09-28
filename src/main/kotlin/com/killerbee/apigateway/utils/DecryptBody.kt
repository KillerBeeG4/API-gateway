package com.killerbee.apigateway.utils

import com.killerbee.apigateway.cryptography.TranspCryptography
import org.springframework.stereotype.Component

@Component
class DecryptBody {

    fun decrypt(body: String) : String {
        return TranspCryptography().decrypt(body)
    }

    fun crypt(body: String) : String {
        return TranspCryptography().crypt(body)
    }
}