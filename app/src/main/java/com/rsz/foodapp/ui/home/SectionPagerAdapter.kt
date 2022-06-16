package com.rsz.foodapp.ui.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.rsz.foodapp.ui.home.newtaste.NewTasteHomeFragment
import com.rsz.foodapp.ui.home.popular.PopularHomeFragment
import com.rsz.foodapp.ui.home.recommended.RecommendedHomeFragment

class SectionPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

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
                return fragment
            }
            1 -> {
                fragment = PopularHomeFragment()
                return fragment
            }
            2 -> {
                fragment = RecommendedHomeFragment()
                return fragment
            }
            else -> {
                fragment = NewTasteHomeFragment()
                return fragment
            }
        }
    }
}