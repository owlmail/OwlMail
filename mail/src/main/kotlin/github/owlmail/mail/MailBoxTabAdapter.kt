package github.owlmail.mail

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import github.owlmail.mail.inbox.MailFragment

class MailBoxTabAdapter(hostFragment: MailBoxHostFragment) : FragmentStateAdapter(hostFragment) {
    // inbox,sentbox,draft,trash,junk
    override fun getItemCount(): Int {
        return tabList.size
    }

    // make a list
    val tabList = listOf("Inbox", "Sent", "Drafts", "Trash", "Junk")
    private val tabFragmentList = mutableListOf<MailFragment>()

    override fun createFragment(position: Int): Fragment {
        return MailFragment.getNewInstance(tabList[position]).also {
            tabFragmentList.add(it)
        }
    }

    fun doAfterTextChanged(query: String) {
        tabFragmentList.forEach {
            if (it.isAdded) {
                it.doAfterTextChanged(query)
            }
        }
    }
}
