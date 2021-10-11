package com.example.projecttrue.memberinfo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.projecttrue.R
import com.example.projecttrue.database.ParlamentMemberData
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.card_layout.view.party
import kotlinx.android.synthetic.main.card_layout.view.partyImage
import kotlinx.android.synthetic.main.card_layout_person.view.*
import java.util.*

//for the url base when getting image
private const val BASE_URL = "https://avoindata.eduskunta.fi/"

// RecyclerView Adapter for setting up the parlamentmemberdata in the list to show.
class MemberAdapter : RecyclerView.Adapter<MemberAdapter.MyViewHolder>() {
    // for showing the list of parlamentmemberdata
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

    // Called by RecyclerView to display the data at the specified position. This method should
    // update the contents of the {@link ViewHolder#itemView} to reflect the item at the given
    // position.
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
         val currentYear: Int = Calendar.getInstance().get(Calendar.YEAR)
        val currentItem = userList[position]
        //make variable text to set all info
        val text =
            currentItem.first + " " + currentItem.last + " \n" + (currentYear - currentItem.bornYear) + " Years old \nConstituency " + currentItem.constituency
        //sets the text to the recyler view text
        holder.itemView.party.text = text
        //get the picture with picasso and adds it to cache
        Picasso.get().load(BASE_URL + currentItem.picture).into(holder.itemView.partyImage)

        //click listener for the recyler view to know what you pressed
        holder.itemView.memberLayout.setOnClickListener {
            /*
            val action= MemberInfoFragmentDirections.actionMemberInfoFragmentToFullInfoFragment(currentItem)
            holder.itemView.findNavController().navigate(action)

             */
        }
    }
//set the data receive to user and that to userlist
    fun setData(user: List<ParlamentMemberData>) {
        this.userList = user
        notifyDataSetChanged()
    }
}