package com.yucheng.connexiononequiz.base

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<data : Any>(itemView: View) :
    RecyclerView.ViewHolder(itemView) {

    abstract fun onBind(data: data)

    fun getString(resId: Int): String {
        return context.getString(resId)
    }

    val context: Context = itemView.context
}