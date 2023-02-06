package github.owlmail.mail.workermanager

import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import github.owlmail.mail.MailRepository
import github.owlmail.mail.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

@HiltWorker
class AttachmentDownloadWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted params: WorkerParameters,
    private val mailRepository: MailRepository,
    private val notificationManager: NotificationManager
) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        //api call and response
        //save response in file
        //notification of download

        val file = File(context.externalCacheDir,"filename")
        if(file.exists()){
            return@withContext Result.success()
        } else {
            val response =
                mailRepository.getMailAttachment("19923","2")
            with(file){
                createNewFile()
                val fileContent = response.string().toString()
//                writeText(fileContent)
            }
            val notification = NotificationCompat.Builder(context, "owlmail_notification_id")
                .setContentTitle("File downloaded")
                .setContentText("filepath = ${file.absolutePath}")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setSmallIcon(R.drawable.ic_baseline_search_24)
                .build()

            notificationManager.showNotification(notification)
        }
        Result.success()
    }
}