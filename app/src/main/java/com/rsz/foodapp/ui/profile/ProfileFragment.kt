package com.rsz.foodapp.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.rsz.foodapp.FoodApp
import com.rsz.foodapp.databinding.FragmentProfileBinding
import com.rsz.foodapp.model.response.login.User

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initView()
    }

    private fun initView() {
        var user = FoodApp.getApp().getUser()
        var userResponse = Gson().fromJson(user, User::class.java)

        binding.tvProfileName.text = userResponse.name
        binding.tvProfileEmail.text = userResponse.email

        if (userResponse.profile_photo_url.isNotEmpty()) {
            Glide.with(requireActivity())
                .load(userResponse.profile_photo_url)
                .into(binding.ivProfileProfile)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}