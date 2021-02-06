package com.yucheng.connexiononequiz.ui.record

import android.view.View
import com.yucheng.connexiononequiz.base.BaseViewHolder
import com.yucheng.connexiononequiz.model.Money
import com.yucheng.connexiononequiz.model.RecordData
import kotlinx.android.synthetic.main.viewholder_record_money.view.*

class MoneyViewHolder(itemView: View) : BaseViewHolder<RecordData>(itemView) {
    override fun onBind(data: RecordData) {
        val money = data as? Money ?: return
        itemView.tvCount.text = "${money.count}"
        itemView.tvValue.text = "${money.value}"
        itemView.tvPercent.text = "(${money.percent}%)"
        itemView.tvInteractiveReward.text = money.interactiveReward
        itemView.tvCommunityReward.text = money.communityReward
    }
}
