package github.owlmail.mail.inbox

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import github.owlmail.mail.R
import github.owlmail.mail.databinding.MailListItemBinding
import github.owlmail.mail.inbox.model.InboxSearchResponse

class MailAdapter() :
    PagingDataAdapter<InboxSearchResponse.Body.SearchResponse.Conversation, RecyclerView.ViewHolder>(
        diffCallback = MailListDiffer()
    ) {
    //item in rv consists of sender name first line of subject and first line of body
    var onClick: ((String?) -> Unit)? = null //store a func in var returns void

    class MailListDiffer :
        DiffUtil.ItemCallback<InboxSearchResponse.Body.SearchResponse.Conversation>() {
        override fun areItemsTheSame(
            oldItem: InboxSearchResponse.Body.SearchResponse.Conversation,
            newItem: InboxSearchResponse.Body.SearchResponse.Conversation
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: InboxSearchResponse.Body.SearchResponse.Conversation,
            newItem: InboxSearchResponse.Body.SearchResponse.Conversation
        ): Boolean {
            return oldItem == newItem
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        val binding = MailListItemBinding.bind(holder.itemView)
        val item = getItem(position)
        binding.senderName.text = item?.emailAddress?.firstOrNull {
            it?.isSenderOrReceiver?.contains("f", true) == true
        }?.fullName
        binding.mailSubject.text = if (item?.subject.isNullOrEmpty()) {
            "No Subject"
        } else {
            item?.subject
        }
        binding.mailBody.text = if (item?.body.isNullOrEmpty()) {
            "No Message Body"
        } else {
            item?.body
        }
        val hasAttachment = item?.flags?.contains("a",ignoreCase = true)?:false
        binding.ivAttachment.isVisible = hasAttachment
        val isFlagged = item?.flags?.contains("f",ignoreCase = true)?:false
        binding.ivFlag.isVisible = isFlagged
        val isUnread = item?.flags?.contains("u",ignoreCase = true)?:false
        if (isUnread){
            binding.senderName.setTextColor(Color.RED)
        } else{
            val textColor = binding.mailSubject.currentTextColor
            binding.senderName.setTextColor(textColor)
        }
        binding.root.setOnClickListener {
            onClick?.invoke(item?.id)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.mail_list_item, parent, false)
        return MailListViewHolder(view)
    }

    inner class MailListViewHolder(view: View) : RecyclerView.ViewHolder(view)

}