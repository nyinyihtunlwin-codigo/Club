package com.nyinyihtunlwin.club.fragments

import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

open class BaseFragment:Fragment() {

    override fun onStart() {
        super.onStart()
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }
    }

    override fun onStop() {
        super.onStop()
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this)
        }
    }

    @Subscribe
    fun onEvent(event: Any?) {
    }

    protected fun showPromptDialog(errorMsg: String) {
        val alertDialog: AlertDialog? = this?.let {
            val builder = AlertDialog.Builder(it.context!!)
            builder.apply {
                setPositiveButton("OK"
                ) { dialog, id ->
                    dialog.dismiss()
                }
            }
            builder.create()
        }
        alertDialog!!.setMessage(errorMsg)
        alertDialog!!.show()
    }

}