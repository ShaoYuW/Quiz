package com.yucheng.connexiononequiz.ui.wallet

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.yucheng.connexiononequiz.ui.record.Record
import com.yucheng.connexiononequiz.ui.ticket.Ticket

class WalletAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> Record()
            else -> Ticket()
        }
    }
}