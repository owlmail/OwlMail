package github.owlmail.auth

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import github.owlmail.auth.databinding.FragmentAuthBinding

@AndroidEntryPoint
class AuthFragment : Fragment() {
    //screen for user id, password
    //xml of this will have 2 edit texts, button
    //login on button click
    //observe the login state
    private val viewModel: AuthViewModel by viewModels()
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
        _binding?.loginButton?.setOnClickListener{
            triggerLoginOnButtonClick()
        }
        observeLoginState()
    }

    private fun getUserDetailsFromInput(): UserDetails {
        val user = _binding?.useridEdit?.text.toString()
        val pass = _binding?.passwordEdit?.text.toString()
        return UserDetails(user,pass)
    }

    private fun triggerLoginOnButtonClick() {
        val userDetails = getUserDetailsFromInput()
        viewModel.userLogin(userDetails)
    }

    private fun observeLoginState() {
        lifecycleScope.launchWhenStarted {
            viewModel.loginState.collect {
                Log.e("Auth Fragment","$it")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}