package com.yhishi.github_user.domain.model.ui

import android.net.Uri

/**
 * ユーザー詳細のUIモデル
 */
data class UserDetail(
    val id: Int,
    val userName: String,
    val avatarUrl: Uri,
    val fullName: String?,
    val followerText: String,
    val followingText: String,
    val repositories: List<Repository>,
) {
    companion object {
        fun of(
            userDetail: com.yhishi.github_user.domain.model.api.UserDetail,
            repositories: List<com.yhishi.github_user.domain.model.api.Repository>,
        ): UserDetail {
            return UserDetail(
                id = userDetail.id,
                userName = userDetail.loginName,
                avatarUrl = Uri.parse(userDetail.avatarUrl),
                fullName = userDetail.name,
                followerText = userDetail.followers.toString() + "人",
                followingText = userDetail.following.toString() + "人",
                repositories = repositories.map { Repository.of(it) },
            )
        }
    }
}