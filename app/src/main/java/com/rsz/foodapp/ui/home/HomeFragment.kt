package com.rsz.foodapp.ui.home

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.rsz.foodapp.FoodApp
import com.rsz.foodapp.R
import com.rsz.foodapp.databinding.FragmentHomeBinding
import com.rsz.foodapp.model.response.home.Data
import com.rsz.foodapp.model.response.home.HomeResponse
import com.rsz.foodapp.model.response.login.User
import com.rsz.foodapp.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(), HomeAdapter.ItemAdapterCallback, HomeContract.View {

    //tab
    private var newTasteList: ArrayList<Data> = ArrayList()
    private var recommendedList: ArrayList<Data> = ArrayList()
    private var popularList: ArrayList<Data> = ArrayList()


    private lateinit var presenter: HomePresenter
    var progressBarDialog: Dialog? = null

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        presenter = HomePresenter(this)
        presenter.getHome()

//        initDataDummy()
        initView()
    }

    fun initView() {
        progressBarDialog = Dialog(requireContext())

        val dialogLayout = layoutInflater.inflate(R.layout.dialog_loading, null)

        progressBarDialog?.let {
            it.setContentView(dialogLayout)
            it.setCancelable(false)
            it.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }

        //get user photo profile
        var user = FoodApp.getApp().getUser()
        var userResponse = Gson().fromJson(user, User::class.java)

        if (userResponse.profile_photo_url.isNotEmpty()) {
            Glide.with(requireActivity())
                .load(userResponse.profile_photo_url)
                .into(binding.ivHomeProfile)
        }
    }


//    fun initDataDummy() {
//        foodList = ArrayList()
//        foodList.add(HomeModelHorizontal("Nasgor","",5f))
//        foodList.add(HomeModelHorizontal("Naspad","",4f))
//        foodList.add(HomeModelHorizontal("bubur","",2f))
//    }

    override fun onClick(v: View, data: Data) {
        val detail = Intent(activity, DetailActivity::class.java)
        startActivity(detail)
    }

    override fun onHomeSuccess(homeResponse: HomeResponse) {

        //misahin tab
        for (a in homeResponse.data.indices) {

            var items: List<String> = homeResponse.data[a].types.split(",") ?: ArrayList()
            for (x in items.indices) {
                if (items[x].equals("new_taste", true)) {
                    newTasteList.add(homeResponse.data[a])
                } else if (items[x] == "recommended") {
                    recommendedList.add(homeResponse.data[a])
                } else if (items[x] == "popular") {
                    popularList.add(homeResponse.data[a])
                }


            }

            val adapter = HomeAdapter(homeResponse.data, this)
            var layoutManager: RecyclerView.LayoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            rv_home.layoutManager = layoutManager
            rv_home.adapter = adapter

            val sectionPagerAdapter = SectionPagerAdapter(childFragmentManager)
            sectionPagerAdapter.setData(newTasteList, recommendedList, popularList)

            home_viewPager.adapter = sectionPagerAdapter
            home_tabLayout.setupWithViewPager(home_viewPager)
        }
    }

    override fun onHomeFailed(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        progressBarDialog?.show()
    }

    override fun dismissLoading() {
        progressBarDialog?.dismiss()
    }
}