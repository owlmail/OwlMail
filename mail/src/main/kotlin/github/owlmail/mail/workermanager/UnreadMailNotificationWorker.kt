package github.owlmail.mail.workermanager

import android.content.Context
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import github.owlmail.mail.MailRepository
import github.owlmail.mail.R
import github.owlmail.mail.inbox.model.InboxSearchRequest
import github.owlmail.networking.ResponseState
import github.owlmail.networking.mapToResponseState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@HiltWorker
class UnreadMailNotificationWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted params: WorkerParameters,
    private val mailRepository: MailRepository,
    private val notificationManager: NotificationManager
) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        Log.e("Preeti","Notification running")
        val inboxSearchRequest = InboxSearchRequest(
            body = InboxSearchRequest.Body(
                searchRequest = InboxSearchRequest.Body.SearchRequest(
                    jsns = "urn:zimbraMail",
                    limit = 10,
                    offset = 0,
                    query = "in:inbox"
                )
            )
        )
        val response = mailRepository.getMailList(inboxSearchRequest).mapToResponseState()
        when(response){
            is ResponseState.Success-> {
                val result = response.data?.body?.searchResponse?.conversation?.any {
                    it?.f?.contains("u", true) == true
                }
                if (result == true) {
                    val notification = NotificationCompat.Builder(context, "owlmail_notification_id")
                        .setContentTitle("You have a mail")
                        .setContentText("This is a test notification")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setSmallIcon(R.drawable.ic_baseline_search_24)
                        .build()

                    notificationManager.showNotification(notification)
                }
                Result.success()
            }
            else-> {
                Result.failure()
            }
        }
    }
}