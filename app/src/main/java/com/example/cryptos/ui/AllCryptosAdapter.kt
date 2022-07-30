package com.example.cryptos.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptos.R
import com.example.cryptos.data.model.AllCryptosItem

class AllCryptosAdapter(private val allCryptos: List<AllCryptosItem>): RecyclerView.Adapter<AllCryptosAdapter.ViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.crypto_details_row, parent,false)
        return ViewHolder(view)

    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var status = if (allCryptos[position].is_active == true) "active"
        else "inactive"

        holder.name.text = "Name: ${allCryptos[position].name?: "N/A"}"
        holder.rank.text = "Watchers: ${allCryptos[position].rank?: "N/A"}"
        holder.status.text = "Status: $status"
        holder.type.text = "Type: ${allCryptos[position].type?: "N/A"}"
    }

    override fun getItemCount(): Int = 20

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.nameTextView)
        val rank: TextView = itemView.findViewById(R.id.rankTextView)
        val status: TextView = itemView.findViewById(R.id.statusTextView)
        val type: TextView = itemView.findViewById(R.id.typeTextView)
    }
}