package github.owlmail.mail

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import github.owlmail.mail.inbox.MailFragment

class MailBoxTabAdapter(hostFragment: MailBoxHostFragment): FragmentStateAdapter(hostFragment) {
    //inbox,sentbox,draft,trash,junk
    override fun getItemCount(): Int {
        return tabList.size
    }

    //make a list
    val tabList = listOf("Inbox","Sent","Drafts","Trash","Junk")

    override fun createFragment(position: Int): Fragment {
        return MailFragment.getNewInstance(tabList[position])
    }
}