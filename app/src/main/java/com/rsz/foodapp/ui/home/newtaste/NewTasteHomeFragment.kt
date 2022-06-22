package com.rsz.foodapp.ui.home.newtaste

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rsz.foodapp.R
import com.rsz.foodapp.model.response.home.Data
import com.rsz.foodapp.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.fragment_new_taste_home.*

class NewTasteHomeFragment : Fragment(), NewTasteHomeAdapter.ItemAdapterCallback {

    private var newTasteList : ArrayList<Data>? = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_taste_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        newTasteList = arguments?.getParcelableArrayList("data")

//        initDataDummy()
        val adapter = NewTasteHomeAdapter(newTasteList!!,this)
        var layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(activity)
        rv_tab_new_taste.layoutManager = layoutManager
        rv_tab_new_taste.adapter = adapter
    }

//    fun initDataDummy() {
//        foodList = ArrayList()
//        foodList.add(HomeModelVertical("Nasgor","1000000","",5f))
//        foodList.add(HomeModelVertical("Naspad","500000","",4f))
//        foodList.add(HomeModelVertical("bubur","1800000","",2f))
//    }

    override fun onClick(v: View, data: Data) {
        val detail = Intent(activity, DetailActivity::class.java).putExtra("data", data)
        startActivity(detail)
    }
}