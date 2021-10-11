package com.example.projecttrue.party

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.projecttrue.R
import kotlinx.android.synthetic.main.card_layout.view.*

class Adapter : RecyclerView.Adapter<Adapter.MyViewHolder>() {
    private var userList = emptyList<String>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = userList[position]
        val partyFullname = when (currentItem) {
            "kok" -> "Kokoomus"
            "kesk" -> "Keskusta"
            "kd" -> "Kristillisdemokraatit"
            "r" -> "Sfp Rkp"
            "liik" -> "Liike Nyt"
            "vas" -> "Vasemmisto"
            "ps" -> "Perussuomalaisten"
            "sd" -> "Sosialidemokraatit"
            "vihr" -> "Viheätdegröna"
            else -> "nothing"
        }
        holder.itemView.party.text = partyFullname
        val picture = when (currentItem) {
            "kok" -> R.drawable.kokoomus
            "kesk" -> R.drawable.keskusta
            "kd" -> R.drawable.kristillinendemokratia
            "r" -> R.drawable.sppfrkp
            "liik" -> R.drawable.liikenyt
            "vas" -> R.drawable.vasemmisto
            "ps" -> R.drawable.perussuomalaisten
            "sd" -> R.drawable.sosialidemokraatit
            "vihr" -> R.drawable.viheatdegrona
            else -> R.drawable.ic_launcher_foreground
        }
        holder.itemView.partyImage.setImageResource(picture)
        holder.itemView.rowLayout.setOnClickListener {
            val action = PartyFragmentDirections.actionToNext(currentItem)
            holder.itemView.findNavController().navigate(action)
            Log.i("MONKEY",currentItem)
        }
    }

    fun setData(user: List<String>) {
        this.userList = user
        notifyDataSetChanged()
    }
}