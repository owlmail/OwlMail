package github.owlmail.mail

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class MailBoxTabAdapter(hostFragment: MailBoxHostFragment): FragmentStateAdapter(hostFragment) {
    //inbox,sentbox,draft,trash,junk
    override fun getItemCount(): Int {
        return 5
    }

    //make a list

    override fun createFragment(position: Int): Fragment {
//        return MailFragment.getNewInstance() work pending
    }
}