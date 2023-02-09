package github.owlmail.mail.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import dagger.hilt.android.AndroidEntryPoint
import github.owlmail.mail.databinding.MailDetailsBinding
import github.owlmail.mail.detail.model.ConvDetails
import github.owlmail.mail.workermanager.AttachmentDownloadWorker
import github.owlmail.networking.ResponseState

@AndroidEntryPoint
class MailDetailFragment : Fragment() {
    private var binding: MailDetailsBinding? = null
    private val viewModel: MailDetailViewModel by viewModels()
    private val args: MailDetailFragmentArgs by navArgs()
    private val mailDetailAdapter = MailDetailAdapter()

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
        setUpRV()
        makeApiCall()
        subscribeToObservers()
    }

    private fun makeApiCall() {
        viewModel.getMailDetail(convDetails = ConvDetails(args.cid ?: ""))
    }

    private fun setUpRV() {
        mailDetailAdapter.onClick = { fileName, part, id ->
            val data = Data.Builder().putString("id", id).putString("part", part)
                .putString("filename", fileName).build()
            WorkManager.getInstance(requireContext()).enqueueUniqueWork(
                "OwlMailDownload",
                ExistingWorkPolicy.KEEP,
                OneTimeWorkRequestBuilder<AttachmentDownloadWorker>().setInputData(data).build()
            )

        }
        binding?.recyclerView1?.adapter = mailDetailAdapter
    }

    fun subscribeToObservers() {
        //observe state
        lifecycleScope.launchWhenStarted {
            viewModel.mailDetail.collect { it ->

                when (it) {
                    //check and move into rv
                    is ResponseState.Success -> {


                        binding?.mailDetailSubject?.text =
                            if (it.data?.body?.searchConvResponse?.message?.firstOrNull()?.subject.isNullOrEmpty()) {
                                "No Subject"
                            } else {
                                it.data?.body?.searchConvResponse?.message?.firstOrNull()?.subject
                            }
                        mailDetailAdapter.differ.submitList(it.data?.body?.searchConvResponse?.message)
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