package com.example.cryptos.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptos.R

class AllCryptosFragment : Fragment() {
    private val viewModel: HomeActivityViewModel by activityViewModels()
    lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_all_cryptos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        setUpUi()
    }

    private fun setUpUi() {
        viewModel.allCryptos.observe(requireActivity()){
            recyclerView.adapter = AllCryptosAdapter(it)
            recyclerView.recycledViewPool.setMaxRecycledViews(0,0)
        }
    }
}