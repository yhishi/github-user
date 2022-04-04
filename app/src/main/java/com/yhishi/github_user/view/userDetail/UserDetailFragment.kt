package com.yhishi.github_user.view.userDetail

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import com.yhishi.github_user.R
import com.yhishi.github_user.databinding.UserDetailFragmentBinding
import com.yhishi.github_user.domain.model.ui.UserDetail
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class UserDetailFragment : Fragment(R.layout.user_detail_fragment) {

    private lateinit var binding: UserDetailFragmentBinding
    private val userDetailData: UserDetail by lazy {
        requireArguments().getSerializable(ARGS_USER_DETAIL) as UserDetail
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.user_detail_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.userDetail = userDetailData
        Picasso.get()
            .load(userDetailData.avatarUrl)
            .into(binding.avatarImage)

        val adapter = RepositoryAdapter { repositoryUrl ->
            launchCustomTabsIntent(repositoryUrl)
        }
        binding.recyclerView.adapter = adapter

        if (userDetailData.repositories.isNotEmpty()) {
            adapter.submitList(userDetailData.repositories)
            binding.noRepositoryText.visibility = View.INVISIBLE
        } else {
            binding.noRepositoryText.visibility = View.VISIBLE
        }
    }

    private fun launchCustomTabsIntent(repositoryUrl: String) {
        val colorSchemeParams = CustomTabColorSchemeParams.Builder()
            .setToolbarColor(ContextCompat.getColor(requireContext(), android.R.color.white))
            .setNavigationBarColor(ContextCompat.getColor(requireContext(), android.R.color.black))
            .setNavigationBarDividerColor(ContextCompat.getColor(requireContext(), android.R.color.transparent))
            .build()

        val intent = CustomTabsIntent.Builder()
            .setDefaultColorSchemeParams(colorSchemeParams)
            .setShowTitle(true)
            .build()

        intent.launchUrl(requireContext(), Uri.parse(repositoryUrl))
    }

    companion object {
        private const val ARGS_USER_DETAIL = "ARGS_USER_DETAIL"
        fun newInstance(
            userDetail: UserDetail,
        ) = UserDetailFragment().apply {
            arguments = Bundle().apply {
                putSerializable(ARGS_USER_DETAIL, userDetail)
            }
        }
    }
}