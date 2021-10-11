package com.example.projecttrue.memberinfo

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projecttrue.R
import com.example.projecttrue.database.ParlamentMemberData
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.card_layout.view.*
import kotlinx.android.synthetic.main.card_layout.view.party
import kotlinx.android.synthetic.main.card_layout.view.partyImage
import kotlinx.android.synthetic.main.card_layout_person.view.*

private const val BASE_URL = "https://avoindata.eduskunta.fi/"

class MemberAdapter : RecyclerView.Adapter<MemberAdapter.MyViewHolder>() {

    private var userList = emptyList<ParlamentMemberData>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.card_layout_person, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]

        val text = currentItem.first + " " + currentItem.last + " \nborn in the year " +
                currentItem.bornYear +"constituency " + currentItem.constituency

        holder.itemView.party.text = text
        Picasso.get().load(BASE_URL + currentItem.picture).into(holder.itemView.partyImage)
        holder.itemView.memberLayout.setOnClickListener {

        }
        Log.i("MONKEY",text)
    }

    fun setData(user: List<ParlamentMemberData>) {
        this.userList = user
        notifyDataSetChanged()
    }
}