package com.bhaskarblur.sync_realtimecontentwriting.data.remote

import com.bhaskarblur.sunbirdpoc.model.GenerateModelRequestBody
import com.bhaskarblur.sunbirdpoc.model.GenerateModelResponseBody
import com.bhaskarblur.sunbirdpoc.model.IssueCredentialRequestBody
import com.bhaskarblur.sunbirdpoc.model.IssueCredentialResponseBody
import com.bhaskarblur.sunbirdpoc.model.VerifyCredentialResponseBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiRoutes {

   @POST("{baseUrl}/did/generate")
   suspend fun generateDid(@Url baseUrl: String,@Body body: GenerateModelRequestBody?) : GenerateModelResponseBody

   @POST("/{baseUrl}/credentials/issue")
   suspend fun issueCredential(@Url baseUrl: String, @Body body: IssueCredentialRequestBody?) : IssueCredentialResponseBody

   @GET("{baseUrl}/credentials/{credId}")
   suspend fun getCredentialWithTemplate(@Url baseUrl: String,
                                         @Path("credId") credId : String) : IssueCredentialResponseBody

   @GET("{url}")
   suspend fun verifyCredential(@Url url: String) : VerifyCredentialResponseBody
}