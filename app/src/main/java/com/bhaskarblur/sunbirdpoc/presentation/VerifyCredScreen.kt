package com.bhaskarblur.sunbirdpoc.presentation

import android.app.Activity
import android.content.ContentValues.TAG
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.journeyapps.barcodescanner.CaptureManager
import com.journeyapps.barcodescanner.CompoundBarcodeView
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions

@Composable
fun VerifyCredScreen(viewModel: ActivityViewModel,
                     navController: NavController) {

    val context = LocalContext.current
    var scanFlag = remember {
        mutableStateOf(false)
    }

    val scanLauncher = rememberLauncherForActivityResult(
        contract = ScanContract(),
        onResult = { result ->
            viewModel.event.value = "Please wait while we verify the document..."
            Log.i(TAG, "scanned code: ${result.contents}")
            viewModel.verifyCredential(result.contents)
        }
    )

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize().padding(18.dp)) {
        Button(onClick = { scanLauncher.launch(ScanOptions()) }) {
            Text(text = "Scan barcode")
        }
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

//    AndroidView(
//        modifier = Modifier,
//        factory = { compoundBarcodeView },
//    )
}