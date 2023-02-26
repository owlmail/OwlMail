package github.owlmail.contacts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import github.owlmail.contacts.databinding.ContactListItemBinding
import github.owlmail.contacts.model.ContactResponse

class ContactAdapter :
    PagingDataAdapter<ContactResponse.Body.SearchGalResponse.Cn, RecyclerView.ViewHolder>(
        diffCallback = ContactListDiffer(),
    ) {
    class ContactListDiffer :
        DiffUtil.ItemCallback<ContactResponse.Body.SearchGalResponse.Cn>() {
        override fun areItemsTheSame(
            oldItem: ContactResponse.Body.SearchGalResponse.Cn,
            newItem: ContactResponse.Body.SearchGalResponse.Cn,
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: ContactResponse.Body.SearchGalResponse.Cn,
            newItem: ContactResponse.Body.SearchGalResponse.Cn,
        ): Boolean {
            return oldItem == newItem
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
    ) {
        val binding = ContactListItemBinding.bind(holder.itemView)
        val item = getItem(position)
        binding.fullName.text = item?.attrs?.fullName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.contact_list_item, parent, false)
        return ContactListViewHolder(view)
    }

    inner class ContactListViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
