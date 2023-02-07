package github.owlmail.mail.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import github.owlmail.mail.R
import github.owlmail.mail.databinding.ConvMailBodyBinding
import github.owlmail.mail.detail.model.MailDetailResponse

class MailDetailAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var onClick: ((String?,String?,String?) -> Unit)? = null
    private val diffUtilCallback =
        object : DiffUtil.ItemCallback<MailDetailResponse.Body.SearchConvResponse.Message>() {
            override fun areItemsTheSame(
                oldItem: MailDetailResponse.Body.SearchConvResponse.Message,
                newItem: MailDetailResponse.Body.SearchConvResponse.Message
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: MailDetailResponse.Body.SearchConvResponse.Message,
                newItem: MailDetailResponse.Body.SearchConvResponse.Message
            ): Boolean {
                return oldItem == newItem
            }
        }
    val differ = AsyncListDiffer(this, diffUtilCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.conv_mail_body, parent, false)
        return MailDetailViewHolder(view)
    }

    inner class MailDetailViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = ConvMailBodyBinding.bind(holder.itemView)
        val item = differ.currentList.getOrNull(position)
        binding.senderId.text =
            item?.emailAdd?.filter {
                it?.isSenderOrReceiver?.contains("f", true) == true
            }?.joinToString("\n") { it?.mailAddress ?: "" }
        binding.receiverId.text =
            item?.emailAdd?.filter {
                it?.isSenderOrReceiver?.contains("t", true) == true
            }?.joinToString("\n") {
                it?.mailAddress ?: ""
            }

        val html =
            item?.multiPart?.joinToString { mp ->
                mp?.getHtmlData() ?: ""
            }
        val messageBody = if (html.isNullOrEmpty()) {
            "No Message Body"
        } else {
            html
        }
        binding.mailBody.setHtml(messageBody)
        item?.multiPart?.map {
            it?.renderAttachmentButton(binding,item.id)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private fun MailDetailResponse.Body.SearchConvResponse.Message.MultiPart.getHtmlData(): String {
        return when {
            contentType?.contains("text", true) == true -> content ?: ""
            contentType?.contains("multipart", true) == true -> multiPart?.joinToString {
                it?.getHtmlData() ?: ""
            } ?: ""
            contentDescription?.equals("attachment", true) == true -> "" //to download the file
            //https://mail.nitrkl.ac.in/service/home/~/?auth=co&loc=en_GB&id=19923&part=2
            else -> ""
        }
    }

    private fun MailDetailResponse.Body.SearchConvResponse.Message.MultiPart.renderAttachmentButton(
        binding: ConvMailBodyBinding,
        messageId: String?
    ) {
        when {
            contentType?.contains("multipart", true) == true -> multiPart?.map{
                it?.renderAttachmentButton(binding,messageId)
            }
            contentDescription?.equals("attachment", true) == true -> {
                val button = Button(binding.root.context).apply {
                    text = fileName
                    setOnClickListener{
                        onClick?.invoke(fileName,part,messageId)
                    }
                }
                binding.attachmentContainer.addView(button)
            }//to download the file
            //https://mail.nitrkl.ac.in/service/home/~/?auth=co&loc=en_GB&id=19923&part=2
            else -> {}
        }
    }
}