package com.example.cryptos.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.example.cryptos.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BitCoinFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
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
        timeTextView = view.findViewById(R.id.activeTextView)
        setUpUi()
    }


    private fun setUpUi() {
        viewModel.bitCoin.observe(requireActivity()) {
            nameTextView.text = it.chartName
            rateTextView.text = it.bpi.USD.rate.toString()
            timeTextView.text = it.time.updated.removePrefix("updated :")
        }
    }
}