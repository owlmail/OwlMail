package github.owlmail.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import github.owlmail.auth.api.AuthNavigationDeeplink
import github.owlmail.settings.databinding.FragmentSettingsBinding
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class SettingsFragment: Fragment() {

    private var binding: FragmentSettingsBinding? = null
    private val viewModel: SettingsViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeToObserver()
        setUpClickListener()
    }

    private fun setUpClickListener() {
        binding?.logout?.setOnClickListener{
            viewModel.logout()
            val request = NavDeepLinkRequest.Builder
                .fromUri(AuthNavigationDeeplink.AUTH_FRAGMENT.toUri())
                .build()
            val navOptions =
                NavOptions.Builder().setPopUpTo(R.id.settingsFragment, true).build()
            findNavController()
                .navigate(request, navOptions)
        }
    }

    private fun subscribeToObserver() {
        lifecycleScope.launchWhenStarted{
            viewModel.userId.collectLatest {
                binding?.emailId?.text = it
            }
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}