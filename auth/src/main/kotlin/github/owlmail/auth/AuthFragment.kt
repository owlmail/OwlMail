package github.owlmail.auth

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import github.owlmail.auth.databinding.FragmentAuthBinding
import github.owlmail.networking.AuthIntercepter
import github.owlmail.networking.ResponseState
import javax.inject.Inject

@AndroidEntryPoint
class AuthFragment : Fragment() {
    //screen for user id, password
    //xml of this will have 2 edit texts, button
    //login on button click
    //observe the login state
    private val viewModel: AuthViewModel by viewModels()
    private var binding: FragmentAuthBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAuthBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.loginButton?.setOnClickListener{
            triggerLoginOnButtonClick()
        }
        observeLoginState()
        observeUserDetails()
    }

    private fun observeUserDetails() {
        lifecycleScope.launchWhenStarted{
            viewModel.readUserDetails().collect{
                val userid = it[DataStoreManager.userId]
                val password = it[DataStoreManager.password]
                if (userid.isNullOrEmpty()||password.isNullOrEmpty()){
                    binding?.root?.isVisible = true

                } else {
                    val userDetails = UserDetails(userid, password)
                    viewModel.userLogin(userDetails)
                }
            }
        }
    }

    private fun getUserDetailsFromInput(): UserDetails {
        val user = binding?.useridEdit?.text.toString()
        val pass = binding?.passwordEdit?.text.toString()
        return UserDetails(user,pass)
    }

    private fun triggerLoginOnButtonClick() {
        val userDetails = getUserDetailsFromInput()
        viewModel.userLogin(userDetails)
    }

    private fun observeLoginState() {
        lifecycleScope.launchWhenStarted {
            viewModel.loginState.collect {
                when(it) {
                    is ResponseState.Success -> {

                        val csrfToken = it.data?.body?.authResponse?.csrfToken?.content ?: ""
                        val cookieToken =
                            it.data?.body?.authResponse?.authToken?.firstOrNull()?.content ?: ""
                        viewModel.saveAuthTokens(csrfToken = csrfToken, cookieToken = cookieToken)

                        viewModel.saveUserDetails()
                        binding?.root?.findNavController()
                            ?.navigate(R.id.action_authFragment_to_mailFragment)
                        //save user details

                    }
                    is ResponseState.Error -> {

                    }
                    is ResponseState.Empty -> {

                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}