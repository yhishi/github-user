package com.yhishi.github_user.domain.repository.api.impl

import com.yhishi.github_user.domain.model.api.Repository
import com.yhishi.github_user.domain.model.api.User
import com.yhishi.github_user.domain.model.api.UserDetail
import com.yhishi.github_user.domain.repository.api.GithubService
import com.yhishi.github_user.domain.repository.api.retrofit.RetrofitService
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class GithubServiceImpl @Inject constructor(
    private val service: RetrofitService,
) : GithubService {
    override fun users(userName: String): Single<List<User>> {
        // TODO HttpException時のstatus codeを用いたエラーハンドリングやリトライ処理
        return service.users(userName)
    }

    override fun userDetail(userName: String): Single<UserDetail> {
        return service.userDetail(userName)
    }

    override fun repositories(userName: String): Single<List<Repository>> {
        return service.repositories(userName)
    }
}
