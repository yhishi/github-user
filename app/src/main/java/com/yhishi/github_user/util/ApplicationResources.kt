package com.yhishi.github_user.util

import android.content.Context
import androidx.annotation.StringRes
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ApplicationResources @Inject constructor(
    @ApplicationContext context: Context
) {
    private val resources = context.resources

    fun getString(@StringRes id: Int): String = resources.getString(id)
}
