package com.bhaskarblur.sunbirdpoc.model

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

data class IssueCredentialRequestBody (
    @SerializedName("credential"              ) var credential              : Credential?       = Credential(),
    @SerializedName("credentialSchemaId"      ) var credentialSchemaId      : String?           = null,
    @SerializedName("credentialSchemaVersion" ) var credentialSchemaVersion : String?           = null,
    @SerializedName("tags"                    ) var tags                    : ArrayList<String> = arrayListOf()

)

data class CredentialSubject (
    @SerializedName("id"           ) var id           : String? = null,
    @SerializedName("type"           ) var type           : String? = null,
    @SerializedName("studentName"   ) var studentName   : String? = null,
    @SerializedName("courseName"    ) var courseName    : String? = null,
    @SerializedName("instituteName" ) var instituteName : String? = null,
    @SerializedName("issuedOn"      ) var issuedOn      : String? = null,
    @SerializedName("skill"        ) var skill        : String? = null

)

data class Credential (
    @SerializedName("@context"          ) var context          : ArrayList<String>  = arrayListOf(),
    @SerializedName("id"                ) var id                : String?            = null,
    @SerializedName("type"              ) var type              : ArrayList<String>  = arrayListOf(),
    @SerializedName("issuer"            ) var issuer            : String?            = null,
    @SerializedName("expirationDate"    ) var expirationDate    : String?            = null,
    @SerializedName("credentialSubject" ) var credentialSubject : CredentialSubject? = CredentialSubject()

)

data class IssueCredentialResponseBody (

    @SerializedName("credential"         ) var credential         : Credential?       = Credential(),
    @SerializedName("credentialSchemaId" ) var credentialSchemaId : String?           = null,
    @SerializedName("createdAt"          ) var createdAt          : String?           = null,
    @SerializedName("createdBy"          ) var createdBy          : String?           = null,
    @SerializedName("updatedBy"          ) var updatedBy          : String?           = null,
    @SerializedName("tags"               ) var tags               : ArrayList<String> = arrayListOf()

)

data class Proof (

    @SerializedName("type"               ) var type               : String? = null,
    @SerializedName("created"            ) var created            : String? = null,
    @SerializedName("proofValue"         ) var proofValue         : String? = null,
    @SerializedName("proofPurpose"       ) var proofPurpose       : String? = null,
    @SerializedName("verificationMethod" ) var verificationMethod : String? = null

)

fun stringToIssueCredentialRequestBody(stringBody: String, values : List<String>) : IssueCredentialRequestBody? {
    val finalBody = stringBody
        .replace("testIssuerId", values[0])
        .replace("testName", values[1])
        .replace("testCourse", values[2])
        .replace("testInstitute", values[3])
        .replace("testDate", values[4])
        .replace("testSkill", values[5])
    return try {
        val gson = Gson()
        gson.fromJson(finalBody, IssueCredentialRequestBody::class.java)
    } catch (e: Exception) {
        null
    }
}