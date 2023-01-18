package github.owlmail.auth

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import dagger.hilt.android.AndroidEntryPoint
import github.owlmail.auth.databinding.FragmentAuthBinding
import github.owlmail.networking.AuthIntercepter
import github.owlmail.networking.ResponseState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import javax.inject.Inject

@AndroidEntryPoint
class AuthFragment : Fragment() {
    //screen for user id, password
    //xml of this will have 2 edit texts, button
    //login on button click
    //observe the login state
    @Inject
    lateinit var authIntercepter: AuthIntercepter
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
                when(it){
                    is ResponseState.Success -> {

                        val csrfToken = it.data?.body?.authResponse?.csrfToken?.content?: ""
                        val cookieToken = it.data?.body?.authResponse?.authToken?.firstOrNull()?.content?: ""
                        authIntercepter.csrfToken = csrfToken
                        authIntercepter.cookie = cookieToken
                        _binding?.root?.findNavController()?.navigate(R.id.action_authFragment_to_mailFragment)
                        //save user details

                    }
                    is ResponseState.Error -> {

                    }
                    is ResponseState.Empty -> {

                    }
                }
                it?.let {
                }
                Log.e("Auth Fragment","$it")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}