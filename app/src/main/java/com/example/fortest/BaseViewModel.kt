package com.example.fortest

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.work.Constraints
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BaseViewModel @Inject constructor(application: Application) : AndroidViewModel(application) {

    private val workManager = WorkManager.getInstance(application)
//    lateinit var outputWorkInfo: LiveData<WorkInfo>


    fun applyWork() {
//        val data = Data.Builder()
//            .putString("KEY", "Input data")
//            .build()

        val constraints = Constraints.Builder()
            .setRequiresCharging(true)
            .build()

        val request = OneTimeWorkRequestBuilder<BaseWorker>()
//            .setInputData(data)
//            .setConstraints(constraints)
            .build()

        workManager.enqueue(request)
//        outputWorkInfo = workManager.getWorkInfoByIdLiveData(request.id)
    }
}