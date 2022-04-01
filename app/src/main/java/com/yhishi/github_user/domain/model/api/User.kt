package com.yhishi.github_user.domain.model.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * ユーザー検索APIのレスポンスクラス
 */
@JsonClass(generateAdapter = true)
data class User(
    @Json(name = "total_count")
    val totalCount: Int,
    @Json(name = "incomplete_results")
    val incompleteResults: Boolean,
    @Json(name = "items")
    val userInfo: List<UserInfo>,
)
