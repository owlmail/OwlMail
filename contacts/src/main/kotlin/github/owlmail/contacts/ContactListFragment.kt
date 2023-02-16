package github.owlmail.contacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import github.owlmail.contacts.databinding.ContactListBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ContactListFragment : Fragment() {
    private var binding: ContactListBinding? = null
    private val contactAdapter = ContactAdapter()
    private val viewModel: ContactViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ContactListBinding.inflate(inflater)
        return binding?.root
    }

    private fun setUpRecyclerView() {
        binding?.recyclerView?.adapter = contactAdapter
        binding?.editText1?.doAfterTextChanged {
            viewModel.updateSearchQuery(it?.trim()?.toString() ?: "")
        }

    }

    private fun subscribeToObservers() {
        lifecycleScope.launch() {
            viewModel.getPaginatedData(requireContext()).collectLatest {
                contactAdapter.submitData(it)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        subscribeToObservers()
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}