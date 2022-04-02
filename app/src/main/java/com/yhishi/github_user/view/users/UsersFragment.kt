package com.yhishi.github_user.view.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.yhishi.github_user.R
import com.yhishi.github_user.databinding.UsersFragmentBinding
import com.yhishi.github_user.viewModel.UsersViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class UsersFragment : Fragment(R.layout.users_fragment) {

    private lateinit var binding: UsersFragmentBinding
    private val viewModel: UsersViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.users_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner

        val adapter = UserAdapter()
        binding.recyclerView.adapter = adapter

        viewModel.users.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    companion object {
        fun newInstance() = UsersFragment()
    }
}