package com.yucheng.connexiononequiz.ui.record

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.yucheng.connexiononequiz.R
import com.yucheng.connexiononequiz.model.Ad
import com.yucheng.connexiononequiz.model.Money
import kotlinx.android.synthetic.main.fragment_record.*

class Record : Fragment() {

    val adapter by lazy { RecordAdapter() }

    val fakeData by lazy {
        listOf(Money(), Ad())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_record, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        rvRecord.adapter = adapter
        rvRecord.layoutManager = LinearLayoutManager(context)
        adapter.submitList(fakeData)
    }
}