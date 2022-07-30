package com.example.cryptos.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.example.cryptos.R

class BitCoinFragment : Fragment() {
    private val viewModel: HomeActivityViewModel by activityViewModels()
    lateinit var nameTextView: TextView
    lateinit var rateTextView: TextView
    lateinit var timeTextView: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_bit_coin, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nameTextView = view.findViewById(R.id.nameTextView)
        rateTextView = view.findViewById(R.id.rankTextView)
        timeTextView = view.findViewById(R.id.statusTextView)
        setUpUi()
    }


    private fun setUpUi() {
        viewModel.bitCoin.observe(requireActivity()) {
            nameTextView.text = it.chartName
            when(viewModel.favoriteCurrency) {
                "USD" -> rateTextView.text = "${it.bpi?.USD?.rate?.dropLast(2)} USD"
                "GBP" -> rateTextView.text = "${it.bpi?.GBP?.rate?.dropLast(2)} GBP"
                "EUR" -> rateTextView.text = "${it.bpi?.EUR?.rate?.dropLast(2)} EUR"
            }
            timeTextView.text = it.time?.updated?.removePrefix("updated :")
        }
    }
}