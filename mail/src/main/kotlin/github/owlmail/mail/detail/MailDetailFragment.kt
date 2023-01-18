package github.owlmail.mail.detail

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import github.owlmail.mail.databinding.MailDetailsBinding

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
                binding?.mailDetailSubject?.text =
                    it?.body?.searchConvResponse?.message?.firstOrNull()?.subject
                binding?.senderId?.text =
                    it?.body?.searchConvResponse?.message?.firstOrNull()?.emailAdd?.firstOrNull()?.a
                binding?.receiverId?.text =
                    it?.body?.searchConvResponse?.message?.firstOrNull()?.emailAdd?.lastOrNull()?.a

                val html =
                    it?.body?.searchConvResponse?.message?.firstOrNull()?.mp?.joinToString { mp ->
                        mp?.content ?:""
                    }
//                { mp ->
//                        mp?.contentType == "text/html"
//                    }?.content
                binding?.mailBody?.text =
                    HtmlCompat.fromHtml(html?:"",HtmlCompat.FROM_HTML_MODE_COMPACT)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}