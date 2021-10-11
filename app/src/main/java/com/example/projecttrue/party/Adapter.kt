package com.example.projecttrue.party

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.projecttrue.R
import kotlinx.android.synthetic.main.card_layout.view.*

// Name: Johnkai Cortez
// Student id: 2012960
// 10.10.2021
class Adapter : RecyclerView.Adapter<Adapter.MyViewHolder>() {
    private var userList = emptyList<String>()

    //describes an item view and metadata about its place within the RecyclerView.
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

    // create new recyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
        )
    }

    //get the size of the list
    override fun getItemCount(): Int {
        return userList.size
    }
    // Called by RecyclerView to display the data at the specified position. This method should
    // update the contents of the {@link ViewHolder#itemView} to reflect the item at the given
    // position.
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = userList[position]
        //set the full name in finnish if the party is something that the currentt item is it will set the full name
        val partyFullname = when (currentItem) {
            "kok" -> "Kokoomus"
            "kesk" -> "Keskusta"
            "kd" -> "Kristillisdemokraatit"
            "r" -> "Sfp Rkp"
            "liik" -> "Liike Nyt"
            "vas" -> "Vasemmisto"
            "ps" -> "Perussuomalaisten"
            "sd" -> "Sosialidemokraatit"
            "vihr" -> "Viheät De Gröna"
            else -> "nothing"
        }
        holder.itemView.party.text = partyFullname

        //with the currentItem it will set the picture
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
        //click listener for the recyler view to know what you pressed
        holder.itemView.rowLayout.setOnClickListener {
            //send the info that you pressed to the next fragment
            val action = PartyFragmentDirections.actionToNext(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }
    //set the data receive to user and that to  String list
    fun setData(user: List<String>) {
        this.userList = user
        notifyDataSetChanged()
    }
}