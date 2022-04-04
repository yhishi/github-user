package com.yhishi.github_user.domain.model.ui

import java.io.Serializable

/**
 * ユーザー詳細のUIモデル
 */
data class UserDetail(
    val id: Int,
    val userName: String,
    val avatarUrl: String,
    val fullName: String?,
    val followerText: String,
    val followingText: String,
    val repositories: List<Repository>,
) : Serializable {
    companion object {
        fun of(
            userDetail: com.yhishi.github_user.domain.model.api.UserDetail,
            repositories: List<com.yhishi.github_user.domain.model.api.Repository>,
        ): UserDetail {
            return UserDetail(
                id = userDetail.id,
                userName = userDetail.loginName,
                avatarUrl = userDetail.avatarUrl,
                fullName = userDetail.name,
                followerText = userDetail.followers.toString() + " フォロワー",
                followingText = userDetail.following.toString() + " フォロー",
                repositories = repositories.map { Repository.of(it) },
            )
        }
    }
}