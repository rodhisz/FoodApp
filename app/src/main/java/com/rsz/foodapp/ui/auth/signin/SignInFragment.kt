package com.rsz.foodapp.ui.auth.signin

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.rsz.foodapp.FoodApp
import com.rsz.foodapp.R
import com.rsz.foodapp.model.response.login.LoginResponse
import com.rsz.foodapp.ui.MainActivity
import com.rsz.foodapp.ui.auth.AuthActivity
import kotlinx.android.synthetic.main.fragment_sign_in.*

class SignInFragment : Fragment(), SigninContract.View {

    lateinit var presenter: SigninPresenter
    var progressBarDialog: Dialog? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        presenter = SigninPresenter(this)

        if (FoodApp.getApp().getToken() != null) {
            startActivity(Intent(activity, MainActivity::class.java))
            activity?.finish()
        }

        initDummy()
        initView()

        btn_signUp.setOnClickListener {
            val signUp = Intent(activity, AuthActivity::class.java)
            signUp.putExtra("page_request", 2)
            startActivity(signUp)
        }

        btn_signIn.setOnClickListener {

            var email = et_email_sign_in.text.toString()
            var password = et_password_sign_in.text.toString()

            if (email.isEmpty()) {
                et_email_sign_in.error = "Email is required"
                et_email_sign_in.requestFocus()
            } else if (password.isEmpty()) {
                et_password_sign_in.error = "Password is required"
                et_password_sign_in.requestFocus()
            } else {
                presenter.submitLogin(email, password)
            }
        }
    }



    override fun onLoginSuccess(loginResponse: LoginResponse) {

        FoodApp.getApp().setToken(loginResponse.access_token)
        val gson = Gson()
        val json = gson.toJson(loginResponse.user)
        FoodApp.getApp().setUser(json)

        startActivity(Intent(activity, MainActivity::class.java))
        activity?.finish()
    }

    override fun onLoginFailed(message: String) {
        Toast.makeText(activity, "salah", Toast.LENGTH_SHORT).show()
    }

    private fun initDummy() {
        et_email_sign_in.setText("admin@gmail.com")
        et_password_sign_in.setText("12345678")
    }

    private fun initView() {
        progressBarDialog = Dialog(requireContext())

        val dialogLayout = layoutInflater.inflate(R.layout.dialog_loading, null)

        progressBarDialog?.let {
            it.setContentView(dialogLayout)
            it.setCancelable(false)
            it.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }
    }

    override fun showLoading() {
        progressBarDialog?.show()
    }

    override fun dismissLoading() {
        progressBarDialog?.dismiss()
    }
}