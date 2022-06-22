package com.rsz.foodapp.ui.auth.signup

import android.net.Uri
import android.view.View
import com.rsz.foodapp.base.BasePresenter
import com.rsz.foodapp.base.BaseView
import com.rsz.foodapp.model.request.RegisterRequest
import com.rsz.foodapp.model.response.login.LoginResponse

interface SignupContract {

    interface View: BaseView {
        fun onRegisterSuccess(loginResponse: LoginResponse, view:android.view.View)
        fun onRegisterPhotoSuccess(view:android.view.View)
        fun onRegisterFailed(message: String)

    }

    interface Presenter : SignupContract, BasePresenter {
        fun submitRegister(registerRequest: RegisterRequest, view:android.view.View)
        fun submitPhotoRegister(filePath : Uri, view:android.view.View)
    }
}