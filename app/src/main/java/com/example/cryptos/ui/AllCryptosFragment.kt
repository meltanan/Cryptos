package com.example.cryptos.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptos.R
import com.example.cryptos.showToast
import com.example.cryptos.util.Resources

class AllCryptosFragment : Fragment() {
    private val viewModel: HomeActivityViewModel by activityViewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressDialog: ProgressBar
    private lateinit var mProgressDialog: ProgressBar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_all_cryptos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        progressDialog = view.findViewById(R.id.progressBar)
        mProgressDialog = view.findViewById(R.id.mProgressBar)
        setUpUi()
    }

    private fun setUpUi() {
        viewModel.allCryptos.observe(requireActivity()){ dataState ->
            when (dataState) {
                is Resources.Success -> {
                    progressDialog.visibility = View.INVISIBLE
                    mProgressDialog.visibility = View.INVISIBLE
                    recyclerView.adapter = dataState.data?.let { AllCryptosAdapter(it) }
                    recyclerView.recycledViewPool.setMaxRecycledViews(0,0)
                }
                is Resources.Loading -> {
                    progressDialog.visibility = View.VISIBLE
                    mProgressDialog.visibility = View.VISIBLE
                }
                is Resources.Error -> requireActivity().showToast("Error!")
            }
        }
    }
}