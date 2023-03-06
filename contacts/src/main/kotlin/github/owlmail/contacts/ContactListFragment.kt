package github.owlmail.contacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.view.MenuProvider
import androidx.core.view.forEach
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import github.owlmail.contacts.databinding.ContactListBinding
import javax.inject.Inject
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ContactListFragment : Fragment(), MenuProvider {
    private var binding: ContactListBinding? = null

    @Inject
    lateinit var contactAdapter: ContactAdapter
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
    }

    private fun subscribeToObservers() {
        lifecycleScope.launch() {
            viewModel.getPaginatedData().collectLatest {
                contactAdapter.submitData(it)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        setUpRecyclerView()
        subscribeToObservers()
    }

    override fun onDestroyView() {
        binding?.recyclerView?.adapter = null
        binding = null
        super.onDestroyView()
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.contacts_menu, menu)

        menu.forEach {
            when (val view = it.actionView) {
                is SearchView -> {
                    view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                        override fun onQueryTextSubmit(query: String?): Boolean {
                            return true
                        }

                        override fun onQueryTextChange(newText: String?): Boolean {
                            viewModel.updateSearchQuery(newText?.trim().orEmpty())
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
