package com.yucheng.connexiononequiz.ui.wallet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import com.yucheng.connexiononequiz.R
import kotlinx.android.synthetic.main.fragment_wallet.*

class Wallet : Fragment() {

    val adapter by lazy { WalletAdapter(this) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wallet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        vpWallet.adapter = adapter

        TabLayoutMediator(tlWallet, vpWallet) { tab, position ->
            tab.text = when (position) {
                0 -> "容幣紀錄"
                else -> "兌換券"
            }
        }.attach()
    }

    companion object {
        fun newInstance() = Wallet()
    }
}