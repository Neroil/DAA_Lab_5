package ch.heigvd.iict.daa.template

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class clearCacheWorker(appContext: Context, workerParams: WorkerParameters ) : Worker(appContext , workerParams) {
}