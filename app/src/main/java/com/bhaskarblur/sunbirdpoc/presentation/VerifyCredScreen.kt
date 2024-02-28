package com.bhaskarblur.sunbirdpoc.presentation

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.journeyapps.barcodescanner.CaptureManager
import com.journeyapps.barcodescanner.CompoundBarcodeView

@Composable
fun VerifyCredScreen(viewModel: ActivityViewModel,
                     navController: NavController) {

    val context = LocalContext.current
    var scanFlag = remember {
        mutableStateOf(false)
    }

    val compoundBarcodeView = remember {
        CompoundBarcodeView(context).apply {
            val capture = CaptureManager(context as Activity, this)
            capture.initializeFromIntent(context.intent, null)
            this.setStatusText("")
            capture.decode()
            this.decodeContinuous { result ->
                if(scanFlag.value){
                    return@decodeContinuous
                }
                scanFlag.value = true
                result.text?.let { barCodeOrQr->
                    viewModel.verifyCredential(barCodeOrQr)

                    scanFlag.value = false
                }


            }
        }
    }

    AndroidView(
        modifier = Modifier,
        factory = { compoundBarcodeView },
    )
}