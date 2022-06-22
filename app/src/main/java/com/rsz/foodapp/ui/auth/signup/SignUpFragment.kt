package com.rsz.foodapp.ui.auth.signup

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.gson.Gson
import com.rsz.foodapp.FoodApp
import com.rsz.foodapp.R
import com.rsz.foodapp.model.request.RegisterRequest
import com.rsz.foodapp.model.response.login.LoginResponse
import com.rsz.foodapp.ui.auth.AuthActivity
import kotlinx.android.synthetic.main.fragment_sign_in.*
import kotlinx.android.synthetic.main.fragment_sign_up.*

class SignUpFragment : Fragment(), SignupContract.View {

    var filePath: Uri? = null
    private lateinit var data:RegisterRequest
    lateinit var presenter: SignupPresenter
    var progressBarDialog: Dialog? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    //buat pindah dari sign up ke succsess
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        presenter = SignupPresenter(this)

        initDummy()
        initListener()
        initView()

    }

    private fun initListener() {
        add_photo_sign_up.setOnClickListener {
            ImagePicker.with(this)
                .galleryOnly()
                .start()
        }

        btn_signUpNow.setOnClickListener {

            var name = et_name_sign_up.text.toString()
            var email = et_email_sign_up.text.toString()
            var password = et_password_sign_up.text.toString()

            if (name.isEmpty()) {
                et_name_sign_up.error = "Name is required"
                et_name_sign_up.requestFocus()
            } else if (email.isEmpty()) {
                et_email_sign_up.error = "Email is required"
                et_email_sign_up.requestFocus()
            } else if (password.isEmpty()) {
                et_password_sign_up.error = "Password is required"
                et_password_sign_up.requestFocus()
            } else {
                var data = RegisterRequest(
                    name,
                    email,
                    password,
                    password,
                    filePath)

                FoodApp.getApp().getToken()?.let { Log.v("cekToken", it) }
                presenter.submitRegister(data,it)
//                presenter.submitPhotoRegister(data.filePath!!,it)
            }
        }
    }

    private fun initDummy() {
        et_name_sign_up.setText("salim")
        et_email_sign_up.setText("salim@gmail.com")
        et_password_sign_up.setText("12345678")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK){
            filePath = data?.data

            Glide.with(this)
                .load(filePath)
                .apply(RequestOptions.circleCropTransform())
                .into(add_photo_sign_up)
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(context, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Task Canceled", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onRegisterSuccess(loginResponse: LoginResponse, view: View) {
        FoodApp.getApp().setToken(loginResponse.access_token)
        val gson = Gson()
        val json = gson.toJson(loginResponse.user)
        FoodApp.getApp().setUser(json)

//        if (data.filePath == null){
//            Navigation.findNavController(view)
//                .navigate(R.id.action_signup_to_success,null)
//            (activity as AuthActivity).toolbarSignUpSuccess()
//        } else {
//            presenter.submitPhotoRegister(data.filePath!!,view)
//        }

        Navigation.findNavController(view)
            .navigate(R.id.action_signup_to_success,null)

        (activity as AuthActivity).toolbarSignUpSuccess()
    }

    override fun onRegisterPhotoSuccess(view: View) {
        Navigation.findNavController(view)
            .navigate(R.id.action_signup_to_success,null)

        (activity as AuthActivity).toolbarSignUpSuccess()
    }

    override fun onRegisterFailed(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
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