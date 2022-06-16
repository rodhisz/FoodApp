package com.rsz.foodapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rsz.foodapp.databinding.FragmentHomeBinding
import com.rsz.foodapp.model.dummy.HomeModel
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(), HomeAdapter.ItemAdapterCallback {

    private var foodList : ArrayList<HomeModel> = ArrayList()
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

        initDataDummy()
        val adapter = HomeAdapter(foodList,this)
        var layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rv_home.layoutManager = layoutManager
        rv_home.adapter = adapter

        val sectionPagerAdapter = SectionPagerAdapter(childFragmentManager)

        home_viewPager.adapter = sectionPagerAdapter
        home_tabLayout.setupWithViewPager(home_viewPager)
    }


    fun initDataDummy() {

        foodList = ArrayList()
        foodList.add(HomeModel("Nasgor","",5f))
        foodList.add(HomeModel("Naspad","",4f))
        foodList.add(HomeModel("bubur","",2f))

    }

    override fun onClick(v: View, data: HomeModel) {
        Toast.makeText(context, "Detail", Toast.LENGTH_SHORT).show()
    }
}