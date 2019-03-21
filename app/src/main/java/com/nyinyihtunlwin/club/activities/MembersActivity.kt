package com.nyinyihtunlwin.club.activities

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import com.nyinyihtunlwin.club.R

import kotlinx.android.synthetic.main.activity_members.*

class MembersActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_members)
        setSupportActionBar(toolbar)
}

}
