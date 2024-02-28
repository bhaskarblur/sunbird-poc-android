package com.bhaskarblur.sunbirdpoc.presentation

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bhaskarblur.sunbirdpoc.data.repository.FunctionalityRepository
import com.bhaskarblur.sunbirdpoc.model.CredentialSubject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@HiltViewModel
class ActivityViewModel(
    private val repo : FunctionalityRepository
) : ViewModel() {


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
            }
        }
    }
}