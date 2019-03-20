package com.nyinyihtunlwin.club.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.nyinyihtunlwin.club.R
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : BaseActivity() {

    companion object {
        fun newInstance(context: Context):Intent{
            return Intent(context,SearchActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        setSupportActionBar(toolbar)
    }

}
