package com.nyinyihtunlwin.club.activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.navigation.NavigationView
import com.nyinyihtunlwin.club.R
import com.nyinyihtunlwin.club.dialogs.CompanyFilterDialog
import com.nyinyihtunlwin.club.fragments.AboutFragment
import com.nyinyihtunlwin.club.fragments.CompaniesFragment
import com.nyinyihtunlwin.club.fragments.FavoritesFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : BaseActivity(),
    NavigationView.OnNavigationItemSelectedListener {

    private var mCurrentSection = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        fabSearch.setOnClickListener {
            startActivity(SearchActivity.newInstance(applicationContext))
        }
        setFragment(CompaniesFragment(), "Companies")

        ivFilter.setOnClickListener {
            val fragmentManager = supportFragmentManager
            val newFragment = CompanyFilterDialog()
            newFragment.show(fragmentManager.beginTransaction(),"")
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_companies -> {
                if (mCurrentSection != 0) {
                    mCurrentSection = 0
                    ivFilter.visibility = View.VISIBLE
                    setFragment(CompaniesFragment(), "Companies")
                }
            }
            R.id.nav_fav -> {
                if (mCurrentSection != 1) {
                    mCurrentSection = 1
                    ivFilter.visibility = View.GONE
                    setFragment(FavoritesFragment(), "Favorites")
                }
            }
            R.id.nav_about -> {
                if (mCurrentSection != 2) {
                    mCurrentSection = 2
                    ivFilter.visibility = View.GONE
                    setFragment(AboutFragment(), "About")
                }
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun setFragment(replaceFragment: Fragment, tag: String) {
        val manager = supportFragmentManager
        val ft = manager.beginTransaction()
        manager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        supportActionBar!!.title = tag
        nav_view.menu.getItem(mCurrentSection).isChecked = true
        ft.replace(R.id.container, replaceFragment)
        ft.commitAllowingStateLoss()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
                drawer_layout.closeDrawer(GravityCompat.START)
            } else {
                if (mCurrentSection != 0) {
                    mCurrentSection = 0
                    setFragment(CompaniesFragment(), "Companies")
                } else {
                    val intent = Intent(Intent.ACTION_MAIN)
                    intent.addCategory(Intent.CATEGORY_HOME)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                    android.os.Process.killProcess(android.os.Process.myPid())
                    System.exit(0)
                }
            }
        }
    }
}
