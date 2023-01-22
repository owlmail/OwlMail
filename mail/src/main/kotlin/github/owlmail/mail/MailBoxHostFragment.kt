package github.owlmail.mail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import github.owlmail.mail.databinding.FragmentMailBoxBinding

@AndroidEntryPoint
class MailBoxHostFragment:Fragment() {
    //adapter for viewpager
    //tablayout and viewpager plugin
    //check navigation
    private var binding: FragmentMailBoxBinding? = null
    private val tabAdapter = MailBoxTabAdapter(this)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMailBoxBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewPager()
        setUpTabLayout()
    }

    private fun setUpViewPager() = binding?.viewPager?.run {
        adapter = tabAdapter
        orientation = ViewPager2.ORIENTATION_HORIZONTAL
    }

    private fun setUpTabLayout() =binding?.run {
        val tabLayout = tabLayout
        val viewPager = viewPager
        TabLayoutMediator(tabLayout,viewPager){tab, position ->
            tab.text = tabAdapter.tabList[position]
        }.attach()
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}