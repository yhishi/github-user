package com.yhishi.github_user.domain.model.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.time.ZonedDateTime

/**
 * ユーザーリポジトリAPIのレスポンス情報
 */
@JsonClass(generateAdapter = true)
data class Repository(
    @Json(name = "id")
    val id: Int,
    @Json(name = "node_id")
    val nodeId: String,
    @Json(name = "name")
    val name: String?,
    @Json(name = "full_name")
    val fullName: String,
    @Json(name = "private")
    val private: Boolean,
    @Json(name = "owner")
    val ownerInfo: UserInfo,
    @Json(name = "language")
    val language: String,
    @Json(name = "stargazers_count")
    val starCount: Int,
    @Json(name = "description")
    val description: String?,
    @Json(name = "fork")
    val fork: Boolean,
    @Json(name = "created_at")
    val createdTime: ZonedDateTime,
    @Json(name = "updated_at")
    val updatedTime: ZonedDateTime,
    // TODO その他多数の項目あり
)
