package com.bhaskarblur.sunbirdpoc.model

import com.google.gson.Gson

data class GenerateModelRequestBody(
    val content: List<GenerateContentBody>
)

data class GenerateContentBody(
    val id : String?,
    var alsoKnownAs : List<String>,
    var services : List<Any> = listOf(),
    var method : String = "witsInnovationLab"
)


data class GenerateModelResponseBody(
    val content: List<GenerateContentBody>
)

fun stringToGenerateModelRequestBody(stringBody: String, values : List<String>) : GenerateModelRequestBody? {
    val finalBody = stringBody.replace("testName", values[0])
        .replace("testEmail", values[1])
    return try {
        val gson = Gson()
        gson.fromJson(finalBody, GenerateModelRequestBody::class.java)
    } catch (e: Exception) {
        null
    }
}