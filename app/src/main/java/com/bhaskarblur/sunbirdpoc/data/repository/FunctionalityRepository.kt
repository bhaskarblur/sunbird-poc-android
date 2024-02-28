package com.bhaskarblur.sunbirdpoc.data.repository

import android.util.Log
import com.bhaskarblur.sunbirdpoc.core.constants.ApiConstants
import com.bhaskarblur.sunbirdpoc.core.constants.ApiConstants.Credential_Base_Url
import com.bhaskarblur.sunbirdpoc.core.constants.ApiConstants.Identity_Base_Url
import com.bhaskarblur.sunbirdpoc.core.constants.ApiConstants.generateDidBody
import com.bhaskarblur.sunbirdpoc.data.local.LocalStorageManager
import com.bhaskarblur.sunbirdpoc.model.CredentialSubject
import com.bhaskarblur.sunbirdpoc.model.GenerateModelResponseBody
import com.bhaskarblur.sunbirdpoc.model.IssueCredentialResponseBody
import com.bhaskarblur.sunbirdpoc.model.VerifyCredentialResponseBody
import com.bhaskarblur.sunbirdpoc.model.stringToGenerateModelRequestBody
import com.bhaskarblur.sunbirdpoc.model.stringToIssueCredentialRequestBody
import com.bhaskarblur.sync_realtimecontentwriting.data.remote.ApiRoutes
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FunctionalityRepository(
    private val normalRoutes: ApiRoutes,
    private val templateRoutes: ApiRoutes,
    private val storageManager: LocalStorageManager
) {

    fun generateDid(values : List<String>) : Flow<GenerateModelResponseBody> = flow{
        try {
            val body = stringToGenerateModelRequestBody(generateDidBody, values)
            val result = normalRoutes.generateDid(Identity_Base_Url, body)
            if(result.content[0].id.isNullOrEmpty().not()) {
                val did = storageManager.saveDid(result.content[0].id!!)
                Log.d("generatedDid", did.toString())
            }
            emit(result)
        }
        catch (e : Exception) {
            e.printStackTrace()
            emit(GenerateModelResponseBody(listOf()))
        }
    }

    fun issueCredential(credValues : CredentialSubject) : Flow<IssueCredentialResponseBody> = flow{
        val did = storageManager.getDid("")
        try {
            val values = listOf(
                did,credValues.studentName!!, credValues.courseName!!, credValues.instituteName!!,
                credValues.issuedOn!!, credValues.skill!!
            )
            val body = stringToIssueCredentialRequestBody(ApiConstants.issueCredBody, values)
            emit(normalRoutes.issueCredential(Credential_Base_Url, body))
        }
        catch (e : Exception) {
            e.printStackTrace()
            emit(IssueCredentialResponseBody())
        }
    }

    fun verifyCredential(url : String) : Flow<VerifyCredentialResponseBody> = flow{
        val result = emit(normalRoutes.verifyCredential(Credential_Base_Url+url))

    }
}