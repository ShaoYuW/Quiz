package com.yucheng.connexiononequiz.ui.record

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.yucheng.connexiononequiz.R
import com.yucheng.connexiononequiz.base.BaseViewHolder
import com.yucheng.connexiononequiz.model.RecordData

class RecordAdapter : ListAdapter<RecordData, BaseViewHolder<RecordData>>(diff) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<RecordData> {
        return when (viewType) {
            0 -> MoneyViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.viewholder_record_money, parent, false)
            )
            else -> AdViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.viewholder_record_ad, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<RecordData>, position: Int) {
        holder.onBind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).type
    }
}

val diff = object : DiffUtil.ItemCallback<RecordData>() {
    override fun areItemsTheSame(oldItem: RecordData, newItem: RecordData): Boolean {
        return false
    }

    override fun areContentsTheSame(oldItem: RecordData, newItem: RecordData): Boolean {
        return false
    }

}
