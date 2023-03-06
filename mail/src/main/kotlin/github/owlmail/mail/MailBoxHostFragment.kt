package github.owlmail.mail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.net.toUri
import androidx.core.view.MenuProvider
import androidx.core.view.forEach
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import github.owlmail.contacts.api.ContactsNavigationDeeplink
import github.owlmail.mail.databinding.FragmentMailBoxBinding
import github.owlmail.mail.manager.UnreadMailNotificationWorker
import github.owlmail.settings.api.SettingsNavigationDeeplink
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@AndroidEntryPoint
class MailBoxHostFragment : Fragment(), MenuProvider {

    @Inject
    lateinit var tabAdapter: MailBoxTabAdapter

    // adapter for viewpager
    // tablayout and viewpager plugin
    // check navigation
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

        requireActivity().addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        setUpViewPager()
        setUpTabLayout()

        // notification manager for unread mail
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

        // sets tab names
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabAdapter?.tabList?.getOrNull(position) ?: ""
        }.attach()
    }

    override fun onDestroyView() {
        binding?.viewPager?.adapter = null
        binding = null
        super.onDestroyView()
    }

    // search icon in app bar menu
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
        when (menuItem.itemId) {
            R.id.settings -> {
                val request = NavDeepLinkRequest.Builder
                    .fromUri(SettingsNavigationDeeplink.SETTINGS_FRAGMENT.toUri())
                    .build()
                findNavController()
                    .navigate(request)
            }

            R.id.contacts -> {
                val request = NavDeepLinkRequest.Builder
                    .fromUri(ContactsNavigationDeeplink.CONTACTS_FRAGMENT.toUri())
                    .build()
                findNavController()
                    .navigate(request)
            }
        }
        return true
    }
}
