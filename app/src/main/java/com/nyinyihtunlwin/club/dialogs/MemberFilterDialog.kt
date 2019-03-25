package com.nyinyihtunlwin.club.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.DialogFragment
import com.nyinyihtunlwin.club.R
import com.nyinyihtunlwin.club.events.MemberFilterEvent
import com.nyinyihtunlwin.club.utils.AppConstants
import com.nyinyihtunlwin.club.utils.ConfigUtils
import kotlinx.android.synthetic.main.dialog_member_filter.*
import kotlinx.android.synthetic.main.dialog_member_filter.view.*
import org.greenrobot.eventbus.EventBus

class MemberFilterDialog : DialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout to use as dialog or embedded fragment
        return inflater.inflate(R.layout.dialog_member_filter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val loadMemSortOrder = ConfigUtils.getInstance().loadMemSortOrder()
        val loadSortBy = ConfigUtils.getInstance().loadMemSortBy()

        when (loadMemSortOrder) {
            AppConstants.MEMBER_ORDER_DEFAULT -> {
                view.rbDefault.isChecked = true
            }
            AppConstants.MEMBER_ORDER_ASC -> {
                view.rbAsc.isChecked = true
            }
            AppConstants.MEMBER_ORDER_DESC -> {
                view.rbDesc.isChecked = true
            }
        }

        when (loadSortBy) {
            AppConstants.MEMBER_ORDERBY_NAME -> {
                spinMemberFilter.setSelection(0)
            }
            AppConstants.MEMBER_ORDERBY_AGE -> {
                spinMemberFilter.setSelection(1)
            }
        }

        isCancelable = false

        view.rGFilter.setOnCheckedChangeListener { group, checkedId ->
            run {
                when (group.checkedRadioButtonId) {
                    view.rbDefault.id -> {
                        ConfigUtils.getInstance().saveMemSortOrder(AppConstants.MEMBER_ORDER_DEFAULT)
                    }
                    view.rbAsc.id -> {
                        ConfigUtils.getInstance().saveMemSortOrder(AppConstants.MEMBER_ORDER_ASC)
                    }
                    view.rbDesc.id -> {
                        ConfigUtils.getInstance().saveMemSortOrder(AppConstants.MEMBER_ORDER_DESC)
                    }
                }
            }
        }

        view.spinMemberFilter.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                ConfigUtils.getInstance().saveMemSortBy(position)
            } // to close the onItemSelected

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }

        view.btnOk.setOnClickListener{
            EventBus.getDefault().post(
                MemberFilterEvent(
                    ConfigUtils.getInstance().loadMemSortBy(),
                    ConfigUtils.getInstance().loadMemSortOrder()
                )
            )
            dismiss()
        }
    }


}