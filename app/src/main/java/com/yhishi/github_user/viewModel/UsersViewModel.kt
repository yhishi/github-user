package com.yhishi.github_user.viewModel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.yhishi.github_user.domain.model.ui.User
import com.yhishi.github_user.domain.model.ui.UserDetail
import com.yhishi.github_user.domain.repository.GithubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.kotlin.Singles
import javax.inject.Inject


@HiltViewModel
class UsersViewModel @Inject constructor(
    private val repository: GithubRepository,
) : ViewModel() {

    private val _users = MutableLiveData<List<User>>()
    private val _userDetail = MutableLiveData<UserDetail>()

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
    val userDetail: LiveData<UserDetail> get() = _userDetail

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

    fun requestUserDetailInfo(userName: String) {
        Singles.zip(
            repository.userDetail(userName),
            repository.repositories(userName),
        )
            .map { (userDetail, repositories) ->
                val notForkedRepositories = repositories.filter { !it.fork }
                UserDetail.of(
                    userDetail = userDetail,
                    repositories = notForkedRepositories,
                )
            }
            .doOnSuccess {
                _userDetail.postValue(it)
            }
            .doOnError {
                // TODO エラー処理
            }
            .onErrorComplete()
            .subscribe()
    }
}
