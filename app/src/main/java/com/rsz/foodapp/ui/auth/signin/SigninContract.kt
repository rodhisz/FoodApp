package com.rsz.foodapp.ui.auth.signin

import com.rsz.foodapp.base.BasePresenter
import com.rsz.foodapp.base.BaseView
import com.rsz.foodapp.model.response.login.LoginResponse

interface SigninContract {

    interface View: BaseView {
        fun onLoginSuccess(loginResponse: LoginResponse)
        fun onLoginFailed(message: String)

    }

    interface Presenter : SigninContract, BasePresenter {
        fun submitLogin(email: String, password: String)
    }
}