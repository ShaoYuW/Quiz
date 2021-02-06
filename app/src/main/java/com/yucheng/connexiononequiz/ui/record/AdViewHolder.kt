package com.yucheng.connexiononequiz.ui.record

import android.view.View
import com.yucheng.connexiononequiz.base.BaseViewHolder
import com.yucheng.connexiononequiz.model.Ad
import com.yucheng.connexiononequiz.model.RecordData

class AdViewHolder(itemView: View) : BaseViewHolder<RecordData>(itemView) {
    override fun onBind(data: RecordData) {
        val ad = data as? Ad ?: return
    }
}