package com.bhaskarblur.sunbirdpoc.presentation

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bhaskarblur.sunbirdpoc.data.repository.FunctionalityRepository
import com.bhaskarblur.sunbirdpoc.model.CredentialSubject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActivityViewModel @Inject constructor(
    private val repo : FunctionalityRepository
) : ViewModel() {

    val event = mutableStateOf("")
    fun generateDid(values : List<String>) {
        viewModelScope.launch {
            repo.generateDid(values).collect{ data ->
                Log.d("generatedIdinViewModel", data.content[0].id.toString())
            }
        }
    }

    fun issueCredential(credential : CredentialSubject) {
        viewModelScope.launch {
            repo.issueCredential(credential).collect{ data ->
                Log.d("issueCredentialinViewModel", data.credential!!.credentialSubject.toString())
            }
        }
    }

    fun verifyCredential(url : String) {
        viewModelScope.launch {
            repo.verifyCredential(url).collect{ data ->
                Log.d("verifyCredentialinViewModel", data.toString())
                if(data.status.equals("ISSUED")) {
                    event.value = "This document VC is valid. Result: ${data.status}, {${data.checks}}"
                    delay(1000)
                }
                else {
                    event.value = "This document VC is invalid"
                }
            }
        }
    }
}