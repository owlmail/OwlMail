package github.owlmail.mail.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import github.owlmail.mail.R
import github.owlmail.mail.databinding.ConvMailBodyBinding
import github.owlmail.mail.detail.model.MailDetailResponse

class MailDetailAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
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
            item?.emailAdd?.firstOrNull()?.a
        binding.receiverId.text =
            item?.emailAdd?.lastOrNull()?.a

        val html =
            item?.multiPart?.joinToString { mp ->
                mp?.getHtmlData() ?: ""
            }
        binding.mailBody.setHtml(html ?: "")
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private fun MailDetailResponse.Body.SearchConvResponse.Message.MultiPart.getHtmlData(): String {
        return when(contentType){
            "text/plain" -> content?:""
            "text/html" -> content?:""
            "multipart/alternative" -> this.multiPart?.joinToString {
                it?.getHtmlData()?:""
            } ?: ""
            else -> ""
        }
    }
}