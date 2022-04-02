package com.yhishi.github_user.view.users

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
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
        binding.viewModel = viewModel

        binding.searchUserName.setOnEditorActionListener { _, actionId, _ ->
            when (actionId and EditorInfo.IME_MASK_ACTION) {
                EditorInfo.IME_ACTION_SEARCH,
                EditorInfo.IME_ACTION_DONE -> {
                    search()
                    true
                }
                else -> {
                    false
                }
            }
        }

        val adapter = UserAdapter { userName ->
            viewModel.requestUserDetailInfo(userName = userName)
        }
        binding.recyclerView.adapter = adapter

        viewModel.users.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    private fun search() {
        val inputText = binding.searchUserName.text.toString().trim()
        if (inputText.isNotEmpty()) {
            hideKeyBoard()
            viewModel.searchUsers(userName = inputText)
        }
    }

    private fun hideKeyBoard() {
        requireActivity().currentFocus?.let { view ->
            val imm = requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    companion object {
        fun newInstance() = UsersFragment()
    }
}