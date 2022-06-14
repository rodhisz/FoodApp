package com.rsz.foodapp.ui.auth.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.rsz.foodapp.R
import com.rsz.foodapp.ui.auth.AuthActivity
import kotlinx.android.synthetic.main.fragment_sign_up.*

class SignUpFragment : Fragment() {

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

        btn_signUpNow.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_signup_to_success,null)

            (activity as AuthActivity).toolbarSignUpSuccess()
        }
    }


}