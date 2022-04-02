package com.yhishi.github_user.domain.model.ui

import java.time.ZonedDateTime

/**
 * リポジトリ情報のUIモデル
 */
data class Repository(
    val id: Int,
    val name: String?,
    val language: String,
    val starCount: Int,
    val description: String?,
    val fork: Boolean,
    val updatedTime: ZonedDateTime,
) {
    companion object {
        fun of(
            repository: com.yhishi.github_user.domain.model.api.Repository,
        ): Repository {
            return Repository(
                id = repository.id,
                name = repository.name,
                language = repository.language,
                starCount = repository.starCount,
                description = repository.description,
                fork = repository.fork,
                updatedTime = repository.updatedTime,
            )
        }
    }
}
