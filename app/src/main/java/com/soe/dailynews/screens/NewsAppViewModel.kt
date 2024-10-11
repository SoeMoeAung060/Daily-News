package com.soe.dailynews.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soe.dailynews.util.ERROR_TAG
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

open class NewsAppViewModel : ViewModel() {

    fun launchCatching(block: suspend CoroutineScope.() -> Unit) =
        viewModelScope.launch(
            CoroutineExceptionHandler { _, throwable ->
                Log.d(ERROR_TAG, throwable.message.orEmpty())
            },
            block = block
        )
}
