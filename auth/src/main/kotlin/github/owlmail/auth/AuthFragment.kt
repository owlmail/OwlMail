package github.owlmail.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import github.owlmail.auth.databinding.FragmentAuthBinding


class AuthFragment : Fragment() {
    //screen for user id, password
    //xml of this will have 2 edit texts, button
    //login on button click
    //observe the login state
    lateinit var viewModel: AuthViewModel
    private var _binding: FragmentAuthBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAuthBinding.inflate(inflater)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        button.setOnClickListener{
//            triggerLoginOnButtonClick()
//        }
        observeLoginState()
    }

    fun getUserDetailsFromInput(): UserDetails {
        return UserDetails("Test123", "123test")
    }

    fun triggerLoginOnButtonClick() {
        val userDetails = getUserDetailsFromInput()
        viewModel.userLogin(userDetails)
    }

    fun observeLoginState() {
        lifecycleScope.launchWhenStarted {
            viewModel.loginState.collect {
                //todo
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}