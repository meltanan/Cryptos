package com.example.cryptos.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.example.cryptos.R
import com.example.cryptos.showToast
import com.example.cryptos.util.Resources

class BitCoinFragment : Fragment() {
    private val viewModel: HomeActivityViewModel by activityViewModels()
    private lateinit var nameTextView: TextView
    private lateinit var rateTextView: TextView
    private lateinit var timeTextView: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var mProgressBar: ProgressBar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_bit_coin, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nameTextView = view.findViewById(R.id.nameTextView)
        rateTextView = view.findViewById(R.id.rankTextView)
        timeTextView = view.findViewById(R.id.statusTextView)
        progressBar = view.findViewById(R.id.progressBar)
        mProgressBar = view.findViewById(R.id.mProgressBar)
        setUpUi()
    }


    private fun setUpUi() {
        viewModel.bitCoin.observe(requireActivity()) { dataState->
            when (dataState) {
               is Resources.Success -> {
                   progressBar.visibility = View.INVISIBLE
                   mProgressBar.visibility = View.INVISIBLE
                   nameTextView.text = dataState?.data?.chartName
                   when(viewModel.favoriteCurrency) {
                       "USD" -> rateTextView.text = "${dataState?.data?.bpi?.USD?.rate?.dropLast(2)} USD"
                       "GBP" -> rateTextView.text = "${dataState?.data?.bpi?.GBP?.rate?.dropLast(2)} GBP"
                       "EUR" -> rateTextView.text = "${dataState?.data?.bpi?.EUR?.rate?.dropLast(2)} EUR"
                   }
                   timeTextView.text = dataState?.data?.time?.updated?.removePrefix("updated :")
                }
                is Resources.Loading -> {
                    progressBar.visibility = View.VISIBLE
                    progressBar.visibility = View.VISIBLE
                }
                else -> requireActivity().showToast("Error!")
            }

        }
    }
}