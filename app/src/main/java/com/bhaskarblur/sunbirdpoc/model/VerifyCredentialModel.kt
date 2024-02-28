package com.bhaskarblur.sunbirdpoc.model

import com.google.gson.annotations.SerializedName

data class VerifyCredentialResponseBody (

    @SerializedName("status" ) var status : String?           = null,
    @SerializedName("checks" ) var checks : ArrayList<Checks> = arrayListOf()

)

data class Checks (

    @SerializedName("active"  ) var active  : String? = null,
    @SerializedName("revoked" ) var revoked : String? = null,
    @SerializedName("expired" ) var expired : String? = null,
    @SerializedName("proof"   ) var proof   : String? = null

)