package com.rsz.foodapp.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import com.rsz.foodapp.R
import kotlinx.android.synthetic.main.layout_toolbar.*

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        val pageRequest = intent.getIntExtra("page_request",0)

        if (pageRequest == 2) {
            toolbarSignUp()
            val navOption = NavOptions.Builder()
                .setPopUpTo(R.id.fragment_signin, true)
                .build()

            Navigation.findNavController(findViewById(R.id.authHostFragment))
                .navigate(R.id.action_signin_to_signup, null, navOption)
        }
    }

    fun toolbarSignUp() {
        toolbar.title = "Sign Up"
        toolbar.subtitle = "Register & eat"
        toolbar.navigationIcon = resources.getDrawable(R.drawable.ic_baseline_arrow_back_ios_new_24, null)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    fun toolbarSignUpSuccess() {
        toolbar.visibility = View.GONE
    }
}