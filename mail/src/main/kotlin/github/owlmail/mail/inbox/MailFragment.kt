package github.owlmail.mail.inbox

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import github.owlmail.mail.MailBoxHostFragmentDirections
import github.owlmail.mail.databinding.FragmentMailBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MailFragment : Fragment() {
    private val mailAdapter = MailAdapter()
    private var mailFolder = "inbox"
    private var binding: FragmentMailBinding? = null
    private val viewModel: MailViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMailBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpMailFolder()
        setupRecyclerView()
        subscribeToObservers()
    }

    private fun setUpMailFolder() {
        arguments?.getString(TAB_KEY)?.let {
            mailFolder = it
        }
    }

    fun setupRecyclerView() {
        mailAdapter.onClick = {
            findNavController().navigate(
                MailBoxHostFragmentDirections.actionMailBoxHostFragmentToMailDetailFragment(
                    it
                )
            )
        }
        binding?.recyclerView?.adapter = mailAdapter
    }

    //when vm paginated refresh call this
    private fun subscribeToObservers() {
        lifecycleScope.launch() {
            viewModel.getPaginatedData(mailFolder).collectLatest {

                mailAdapter.submitData(it)
            }
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    companion object {
        const val TAB_KEY = "tab_key"
        fun getNewInstance(mailFolder: String): MailFragment {
            return MailFragment().also {
                it.arguments = bundleOf(TAB_KEY to mailFolder)
            }
        }
    }

    fun doAfterTextChanged(query: String) {
        viewModel.updateSearchQuery(query)
    }
}