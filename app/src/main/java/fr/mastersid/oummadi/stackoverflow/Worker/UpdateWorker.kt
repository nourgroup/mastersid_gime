package fr.mastersid.oummadi.stackoverflow.Worker

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import fr.mastersid.oummadi.stackoverflow.data.repository.DataRepository

@HiltWorker
class UpdateWorker @AssistedInject constructor(
                    @Assisted appContext: Context,
                    @Assisted workerParameters: WorkerParameters,
                    private val repository : DataRepository) :
    CoroutineWorker( appContext , workerParameters ) {

    override suspend fun doWork() : Result {
        Log.d("workManager","just doWork")
        setForeground ( NotificationUtils.createForegroundInfo(id , applicationContext ))
        repository.insertInNoSqlAndGetQuestionWebService()
        Log.d("workManager","after insertInNoSqlAndGetQuestionWebService")
        return Result.success()
    }
}