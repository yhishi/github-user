package com.yhishi.github_user.domain.repository.api.retrofit

import com.yhishi.github_user.domain.model.api.User
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitService {
    /**
     * ユーザーを検索して一覧を取得する
     */
    @GET("search/users?q={user_name}")
    fun users(@Path("user_name") userName: String): Single<List<User>>
}