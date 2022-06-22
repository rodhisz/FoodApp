package com.rsz.foodapp.ui.home

import com.rsz.foodapp.base.BasePresenter
import com.rsz.foodapp.base.BaseView
import com.rsz.foodapp.model.response.home.HomeResponse
import com.rsz.foodapp.model.response.login.LoginResponse

interface HomeContract {

    interface View: BaseView {
        fun onHomeSuccess(homeResponse: HomeResponse)
        fun onHomeFailed(message: String)

    }

    interface Presenter : HomeContract, BasePresenter {
        fun getHome()
    }
}