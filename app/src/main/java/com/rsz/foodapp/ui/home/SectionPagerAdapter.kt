package com.rsz.foodapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.rsz.foodapp.model.response.home.Data
import com.rsz.foodapp.ui.home.newtaste.NewTasteHomeFragment
import com.rsz.foodapp.ui.home.popular.PopularHomeFragment
import com.rsz.foodapp.ui.home.recommended.RecomendedHomeFragment

class SectionPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    var newTasteList : ArrayList<Data>? = ArrayList()
    var recommendedList : ArrayList<Data>? = ArrayList()
    var popularList : ArrayList<Data>? = ArrayList()

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position) {
            0 -> "New Taste"
            1 -> "Popular"
            2 -> "Recommended"
            else -> ""
        }
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment
        return when (position) {
            0 -> {
                fragment = NewTasteHomeFragment()

                val bundle = Bundle()
                bundle.putParcelableArrayList("data", newTasteList)
                fragment.arguments = bundle

                return fragment
            }
            1 -> {
                fragment = PopularHomeFragment()

                val bundle = Bundle()
                bundle.putParcelableArrayList("data", popularList)
                fragment.arguments = bundle

                return fragment
            }
            2 -> {
                fragment = RecomendedHomeFragment()

                val bundle = Bundle()
                bundle.putParcelableArrayList("data", recommendedList)
                fragment.arguments = bundle

                return fragment
            }
            else -> {
                fragment = NewTasteHomeFragment()

                val bundle = Bundle()
                bundle.putParcelableArrayList("data", newTasteList)
                fragment.arguments = bundle

                return fragment
            }
        }
    }

//

    fun setData(newTasteListParms : ArrayList<Data>?, recommendedListParms : ArrayList<Data>?, popularListParms : ArrayList<Data>?) {
        newTasteList = newTasteListParms
        recommendedList = recommendedListParms
        popularList = popularListParms
    }
}