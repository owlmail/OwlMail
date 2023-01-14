package github.owlmail.mail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import github.owlmail.mail.databinding.FragmentMailBinding
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MailFragment: Fragment() {
    private val mailAdapter = MailAdapter()
    private var _binding : FragmentMailBinding? = null
    private val viewModel : MailViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMailBinding.inflate(inflater)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }
    fun setupRecyclerView(){
        _binding?.recyclerView?.adapter = mailAdapter
        updateDataInRV()
    }
    //when vm paginated refresh call this
    fun updateDataInRV(){
        lifecycleScope.launch(){
            viewModel.getPaginatedData().catch {  }.collect{
                mailAdapter.submitData(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}