package com.yucheng.connexiononequiz

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.yucheng.connexiononequiz.ui.empty.Empty
import com.yucheng.connexiononequiz.ui.wallet.Wallet
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        with(window) {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            decorView.systemUiVisibility =
                (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            statusBarColor = Color.TRANSPARENT
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, Wallet.newInstance(), Empty::class.simpleName)
            .addToBackStack(Wallet::class.simpleName)
            .commit()

        page1.setOnClickListener {
            changePage(Empty.newInstance())
        }

        page2.setOnClickListener {
            changePage(Empty.newInstance())
        }

        page3.setOnClickListener {
            changePage(Wallet.newInstance())
        }

        page4.setOnClickListener {
            changePage(Empty.newInstance())
        }

        page5.setOnClickListener {
            changePage(Empty.newInstance())
        }
    }

    fun changePage(fragment :Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment, fragment::class.simpleName)
            .addToBackStack(fragment::class.simpleName)
            .commit()
    }
}