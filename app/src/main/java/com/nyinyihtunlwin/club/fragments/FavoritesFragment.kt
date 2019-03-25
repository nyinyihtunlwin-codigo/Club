package com.nyinyihtunlwin.club.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayout
import com.nyinyihtunlwin.club.R
import com.nyinyihtunlwin.club.adapters.FavoritesPagerAdapter
import kotlinx.android.synthetic.main.fragment_favorites.view.*

class FavoritesFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.vpFavorites.adapter = FavoritesPagerAdapter(childFragmentManager)
        view.vpFavorites.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(view.tabs))
        view.tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(view.vpFavorites))
    }

}
