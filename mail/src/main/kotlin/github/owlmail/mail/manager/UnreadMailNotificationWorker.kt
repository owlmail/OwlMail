package github.owlmail.mail.manager

import android.content.Context
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import github.owlmail.core.DataStoreManager
import github.owlmail.core.DataStoreManager.Companion.NOTIFICATION_TIME_STAMP
import github.owlmail.mail.MailRepository
import github.owlmail.mail.R
import github.owlmail.mail.inbox.model.InboxSearchRequest
import github.owlmail.networking.ResponseState
import github.owlmail.networking.mapToResponseState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext

@HiltWorker
class UnreadMailNotificationWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted params: WorkerParameters,
    private val mailRepository: MailRepository,
    private val notificationManager: NotificationManager,
    private val dataStoreManager: DataStoreManager
) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        Log.e("Preeti", "Notification running")
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
        val timeStamp = System.currentTimeMillis()
        val previousTimeStamp =
            dataStoreManager.readFromDataStore().first().get(NOTIFICATION_TIME_STAMP) ?: 0

        val response = mailRepository.getMailList(inboxSearchRequest).mapToResponseState()
        when (response) {
            is ResponseState.Success -> {

                dataStoreManager.saveToDataStore(timeStamp)

                val result = response.data?.body?.searchResponse?.conversation?.any {
                    (it?.flags?.contains("u", true) == true) && ((it.d ?: 0) >= previousTimeStamp)
                }
                if (result == true) {
                    val notification =
                        NotificationCompat.Builder(context, "owlmail_notification_id")
                            .setContentTitle("You have a mail")
                            .setContentText("This is a test notification")
                            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                            .setSmallIcon(R.drawable.ic_baseline_search_24)
                            .build()

                    notificationManager.showNotification(notification)
                }
                Result.success()
            }
            else -> {
                Result.failure()
            }
        }
    }
}