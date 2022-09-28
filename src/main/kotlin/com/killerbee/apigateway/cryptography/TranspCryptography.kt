package com.killerbee.apigateway.cryptography

class TranspCryptography : ICryptography {

    private val matrixLength = 4

    override fun crypt(input: String): String {
        val height = (input.length/matrixLength) + 1
        val matrix = Array(matrixLength) { CharArray(height) }
        for(cIndex in input.indices) {
            matrix[cIndex % matrixLength][cIndex / matrixLength] = input[cIndex]
        }

        var finalStr = ""
        for(i in 0 until matrixLength) {
            for(j in 0 until height) {
                try {
                    finalStr += matrix[i][j]
                }
                catch (e: Exception) {

                }
            }

        }

        return finalStr
    }

    override fun decrypt(input: String): String {

        val height = (input.length / matrixLength)+1
        val matrix = Array(matrixLength) { CharArray(height) { '\n' } }

        val highestCols = input.length % matrixLength
        var cIndex = 0
        for(i in 0 until highestCols) {
            for(j in 0 until height) {
                matrix[i][j] = input[cIndex]
                cIndex++
            }
        }

        for(i in highestCols until matrixLength) {
            for(j in 0 until height-1) {
                matrix[i][j] = input[cIndex]
                cIndex++
            }
        }

        var finalStr = ""
        for(j in 0 until height) {
            for(i in 0 until matrixLength) {
                finalStr += matrix[i][j]
            }
        }

        return finalStr
    }
}