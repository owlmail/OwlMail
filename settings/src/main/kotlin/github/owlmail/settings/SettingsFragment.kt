package github.owlmail.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import github.owlmail.settings.databinding.FragmentSettingsBinding

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
        }
    }

    private fun subscribeToObserver() {
        lifecycleScope.launchWhenStarted{
            viewModel.userId.collect {
                binding?.emailId?.text = it
            }
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}