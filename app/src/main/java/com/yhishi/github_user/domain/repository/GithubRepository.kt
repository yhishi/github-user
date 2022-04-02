package com.yhishi.github_user.domain.repository

import com.yhishi.github_user.domain.model.api.Repository
import com.yhishi.github_user.domain.model.api.User
import com.yhishi.github_user.domain.model.api.UserDetail
import com.yhishi.github_user.domain.repository.api.GithubService
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
    fun users(userName: String): Single<User> {
        return service.users(userName)
    }

    /**
     * ユーザー詳細情報を取得する
     */
    fun userDetail(userName: String): Single<UserDetail> {
        return service.userDetail(userName)
    }

    /**
     * ユーザーのリポジトリ情報を取得する
     */
    fun repositories(userName: String): Single<List<Repository>> {
        return service.repositories(userName)
    }
}
