package com.nyinyihtunlwin.club.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.nyinyihtunlwin.club.R
import com.nyinyihtunlwin.club.events.CompanyFilterEvent
import com.nyinyihtunlwin.club.utils.AppConstants
import com.nyinyihtunlwin.club.utils.ConfigUtils
import kotlinx.android.synthetic.main.dialog_company_filter.view.*
import org.greenrobot.eventbus.EventBus

class CompanyFilterDialog : DialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout to use as dialog or embedded fragment
        return inflater.inflate(R.layout.dialog_company_filter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val loadComSortOrder = ConfigUtils.getInstance().loadComSortOrder()
        when(loadComSortOrder){
            AppConstants.COMPANY_ORDER_DEFAULT -> {view.rbDefault.isChecked = true}
            AppConstants.COMPANY_ORDER_ASC -> {view.rbAsc.isChecked = true}
            AppConstants.COMPANY_ORDER_DESC -> {view.rbDesc.isChecked = true}
        }

        view.rGFilter.setOnCheckedChangeListener { group, checkedId ->
            run {
                when (group.checkedRadioButtonId) {
                    view.rbDefault.id -> {
                        ConfigUtils.getInstance().saveComSortOrder(AppConstants.COMPANY_ORDER_DEFAULT)
                        EventBus.getDefault().post(CompanyFilterEvent(AppConstants.COMPANY_ORDER_DEFAULT))
                    }
                    view.rbAsc.id -> {
                        ConfigUtils.getInstance().saveComSortOrder(AppConstants.COMPANY_ORDER_ASC)
                        EventBus.getDefault().post(CompanyFilterEvent(AppConstants.COMPANY_ORDER_ASC))
                    }
                    view.rbDesc.id -> {
                        ConfigUtils.getInstance().saveComSortOrder(AppConstants.COMPANY_ORDER_DESC)
                        EventBus.getDefault().post(CompanyFilterEvent(AppConstants.COMPANY_ORDER_DESC))
                    }
                }
                dismiss()
            }
        }
    }
}