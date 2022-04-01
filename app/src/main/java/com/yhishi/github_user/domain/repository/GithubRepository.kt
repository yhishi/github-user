package com.yhishi.github_user.domain.repository.api

import com.yhishi.github_user.domain.model.api.User
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class GithubRepository @Inject constructor(
    private val service: GithubService,
) {
    /**
     * ユーザーを検索して一覧を取得する
     */
    fun users(userName: String): Single<List<User>> {
        return service.users(userName)
    }
}
