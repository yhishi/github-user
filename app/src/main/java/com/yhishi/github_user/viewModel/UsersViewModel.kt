package com.yhishi.github_user.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yhishi.github_user.domain.model.ui.User
import com.yhishi.github_user.domain.repository.GithubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class UsersViewModel @Inject constructor(
    repository: GithubRepository,
) : ViewModel() {

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> get() = _users

    init {
        // TODO プログレス表示
        // TODO 検索文字列をuserNameとして渡す
        repository.users(userName = "yhishi")
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
