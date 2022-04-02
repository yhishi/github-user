package com.yhishi.github_user.domain.repository.api

import com.yhishi.github_user.domain.model.api.User
import com.yhishi.github_user.domain.model.api.UserDetail
import io.reactivex.rxjava3.core.Single

interface GithubService {
    /**
     * ユーザーを検索して一覧を取得する
     */
    fun users(userName: String): Single<List<User>>

    /**
     * ユーザー詳細情報を取得する
     */
    fun userDetail(userName: String): Single<UserDetail>
}
