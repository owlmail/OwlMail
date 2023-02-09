package github.owlmail.mail

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.core.view.MenuProvider
import androidx.core.view.forEach
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import androidx.work.*
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import github.owlmail.mail.databinding.FragmentMailBoxBinding
import github.owlmail.mail.workermanager.AttachmentDownloadWorker
import github.owlmail.mail.workermanager.UnreadMailNotificationWorker
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class MailBoxHostFragment : Fragment(), MenuProvider {
    private var tabAdapter: MailBoxTabAdapter? = null

    //adapter for viewpager
    //tablayout and viewpager plugin
    //check navigation
    private var binding: FragmentMailBoxBinding? = null
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
        tabAdapter = MailBoxTabAdapter(this)
        requireActivity().addMenuProvider(this)
        setUpViewPager()
        setUpTabLayout()
        WorkManager.getInstance(requireContext()).enqueueUniquePeriodicWork(
            "OwlMailNotification",
            ExistingPeriodicWorkPolicy.KEEP,
            PeriodicWorkRequestBuilder<UnreadMailNotificationWorker>(
                15,
                TimeUnit.MINUTES
            ).setConstraints(
                Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
            ).build()
        )

    }

    private fun setUpViewPager() = binding?.viewPager?.run {
        adapter = tabAdapter
        orientation = ViewPager2.ORIENTATION_HORIZONTAL
    }

    private fun setUpTabLayout() = binding?.run {
        val tabLayout = tabLayout
        val viewPager = viewPager
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabAdapter?.tabList?.getOrNull(position) ?: ""
        }.attach()
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.search_menu_bar, menu)
        menu.forEach {
            when (val view = it.actionView) {
                is SearchView -> {
                    view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                        override fun onQueryTextSubmit(query: String?): Boolean {
                            return true
                        }

                        override fun onQueryTextChange(newText: String?): Boolean {
                            tabAdapter?.doAfterTextChanged(newText?.trim() ?: "")
                            return true
                        }
                    })
                }
            }
        }
    }


    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return true
    }
}