package com.yhishi.github_user.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import com.yhishi.github_user.R
import com.yhishi.github_user.view.users.UsersFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.commit {
            replace(
                R.id.container,
                UsersFragment.newInstance(),
                UsersFragment::class.java.simpleName
            )
        }
    }
}