package com.yhishi.github_user.domain.model.ui

import java.time.ZonedDateTime

/**
 * リポジトリ情報のUIモデル
 */
data class Repository(
    val id: Int,
    val name: String?,
    val language: String?,
    val starCountText: String,
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
                language = if (repository.language != null) {
                    "言語：" + repository.language
                } else {
                    "言語：なし"
                },
                starCountText = "☆" + repository.starCount.toString(),
                description = repository.description,
                fork = repository.fork,
                updatedTime = repository.updatedTime,
            )
        }
    }
}
