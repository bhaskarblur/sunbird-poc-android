package com.bhaskarblur.sunbirdpoc.presentation

sealed class Screens(val route: String) {
    object VerifyCredScreen : Screens("VerifyCredScreen")
    object IssueCredScreen : Screens("IssueCredScreen")
    object GenerateDidScreen : Screens("GenerateDidScreen")

}