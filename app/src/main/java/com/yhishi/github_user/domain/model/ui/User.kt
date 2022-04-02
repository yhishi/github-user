package com.yhishi.github_user.domain.model.ui

import android.net.Uri
import com.yhishi.github_user.domain.model.api.UserInfo

/**
 * ユーザー一覧のUIモデル
 */
data class User(
    val id: Int,
    val userName: String,
    val avatarUrl: Uri,
) {
    companion object {
        fun of(userInfo: UserInfo): User {
            return User(
                id = userInfo.id,
                userName = userInfo.loginName,
                avatarUrl = Uri.parse(userInfo.avatarUrl),
            )
        }
    }
}