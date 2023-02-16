package github.owlmail.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import github.owlmail.auth.api.AuthState
import github.owlmail.auth.databinding.FragmentAuthBinding
import kotlinx.coroutines.flow.collectLatest

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
        //trigger fun on click login button
        binding?.loginButton?.setOnClickListener {
            triggerLoginOnButtonClick()
        }
        //login success or failure state
        observeLoginState()
    }


    //sets edit text values to user detail data class
    private fun getUserDetailsFromInput(): UserDetails {
        val user = binding?.useridEdit?.text.toString()
        val pass = binding?.passwordEdit?.text.toString()
        return UserDetails(user, pass)
    }

    //get details and start authentication
    private fun triggerLoginOnButtonClick() {
        val userDetails = getUserDetailsFromInput()
        viewModel.userLogin(userDetails)
    }

    //to observe Login State
    private fun observeLoginState() {
        lifecycleScope.launchWhenStarted {

            //check login state, success or failure
            viewModel.loginState.collectLatest {
                when (it) {
                    AuthState.AUTHENTICATED -> {

                        //navigates to mailbox on success
                        val deeplink = "android-app://github.owlmail.mail/mailBoxHostFragment"
                        val request = NavDeepLinkRequest.Builder
                            .fromUri(deeplink.toUri())
                            .build()
                        val navOptions =
                            NavOptions.Builder().setPopUpTo(R.id.authFragment, true).build()
                        findNavController()
                            .navigate(request, navOptions)

                    }
                    AuthState.NON_AUTHENTICATED -> {
                        //in case of login fail or logout makes auth screen visible
                        binding?.root?.isVisible = true
                    }
                    else -> {
                        binding?.root?.isVisible = false
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}