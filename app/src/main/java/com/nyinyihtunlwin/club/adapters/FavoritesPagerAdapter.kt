package com.nyinyihtunlwin.club.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.nyinyihtunlwin.club.fragments.FavoriteCompaniesFragment
import com.nyinyihtunlwin.club.fragments.FavoriteMembersFragment

class FavoritesPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = FavoriteCompaniesFragment()
            1 -> fragment = FavoriteMembersFragment()
        }
        return fragment!!
    }

    override fun getCount(): Int {
        return 2
    }

}