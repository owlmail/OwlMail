package github.owlmail.mail

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2

class MailBoxTabAdapter(hostFragment: MailBoxHostFragment): FragmentStateAdapter(hostFragment) {
    //inbox,sentbox,draft,trash,junk
    override fun getItemCount(): Int {
        return 5
    }

    //make a list

    override fun createFragment(position: Int): Fragment {
        return when(position){
            1 -> MailFragment.getNewInstance("sent")
            2 -> MailFragment.getNewInstance("drafts")
            3 -> MailFragment.getNewInstance("trash")
            4 -> MailFragment.getNewInstance("junk")
            else -> MailFragment.getNewInstance("inbox")
        }
//        return MailFragment.getNewInstance() work pending
    }
}