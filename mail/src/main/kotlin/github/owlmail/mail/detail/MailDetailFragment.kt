package github.owlmail.mail.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import github.owlmail.mail.databinding.MailDetailsBinding
import github.owlmail.networking.ResponseState

@AndroidEntryPoint
class MailDetailFragment : Fragment() {
    private var binding: MailDetailsBinding? = null
    private val viewModel: MailDetailViewModel by viewModels()
    private val args: MailDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MailDetailsBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getMailDetail()
    }

    fun getMailDetail() {
        viewModel.getMailDetail(convDetails = ConvDetails(args.cid ?: ""))
        //observe state
        lifecycleScope.launchWhenStarted {
            viewModel.mailDetail.collect { it ->

                when (it) {
                    //check and move into rv
                    is ResponseState.Success -> {
                        binding?.mailDetailSubject?.text =
                            it.data?.body?.searchConvResponse?.message?.firstOrNull()?.subject
                        binding?.senderId?.text =
                            it.data?.body?.searchConvResponse?.message?.firstOrNull()?.emailAdd?.firstOrNull()?.a
                        binding?.receiverId?.text =
                            it.data?.body?.searchConvResponse?.message?.firstOrNull()?.emailAdd?.lastOrNull()?.a

                        val html =
                            it.data?.body?.searchConvResponse?.message?.firstOrNull()?.mp?.joinToString { mp ->
                                mp?.content ?: ""
                            }
                        binding?.mailBody?.setHtml(html ?: "")
                    }
                    is ResponseState.Empty -> {

                    }
                    is ResponseState.Error -> {

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