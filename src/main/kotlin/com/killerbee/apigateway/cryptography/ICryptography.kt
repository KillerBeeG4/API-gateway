package com.killerbee.apigateway.cryptography

interface ICryptography {

    enum class CryptographyType {
        TRANSP,
        SUB
    }

    companion object {
        fun getFor(type: CryptographyType) : ICryptography {
            return when(type) {
                CryptographyType.TRANSP -> TranspCryptography()
                CryptographyType.SUB -> SubCryptography()
            }
        }
    }

    fun crypt(input: String): String

    fun decrypt(input: String) : String
}