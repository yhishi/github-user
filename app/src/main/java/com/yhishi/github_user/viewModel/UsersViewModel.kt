package com.yhishi.github_user.viewModel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.yhishi.github_user.domain.model.ui.User
import com.yhishi.github_user.domain.repository.GithubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class UsersViewModel @Inject constructor(
    private val repository: GithubRepository,
) : ViewModel() {

    private val _users = MutableLiveData<List<User>>()

    val users: LiveData<List<User>> get() = _users
    val userListVisibility: LiveData<Int>
        get() = _users.map {
            if (it.isEmpty()) {
                View.GONE
            } else {
                View.VISIBLE
            }
        }
    val noUserTextVisibility: LiveData<Int>
        get() = _users.map {
            if (it.isEmpty()) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }

    fun searchUsers(userName: String) {
        // TODO プログレス表示
        repository.users(userName = userName)
            .doOnSuccess { users ->
                _users.postValue(
                    users.userInfo.map { user ->
                        User.of(user)
                    }
                )
            }
            .doOnError {
                // TODO エラー処理
            }
            .onErrorComplete()
            .subscribe()
    }
}
