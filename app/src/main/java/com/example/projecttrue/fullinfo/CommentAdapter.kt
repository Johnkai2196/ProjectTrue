package com.example.projecttrue.fullinfo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projecttrue.R
import com.example.projecttrue.database.CommentData
import kotlinx.android.synthetic.main.comment_layout.view.*

// Name: Johnkai Cortez
// Student id: 2012960

// RecyclerView Adapter for setting up the Comment in the list to show.
class CommentAdapter : RecyclerView.Adapter<CommentAdapter.MyViewHolder>() {
    // for showing the list of comment
    private var commentList = emptyList<CommentData>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

    // create new recyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.comment_layout, parent, false)
        )
    }
    //get count
    override fun getItemCount(): Int {
        return commentList.size
    }

    // Called by RecyclerView to display the data at the specified position. This method should
    // update the contents of the {@link ViewHolder#itemView} to reflect the item at the given
    // position.
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = commentList[position]
        //make variable text to set all info
        holder.itemView.commentShow.text = currentItem.comment + " Rating: " + currentItem.rating
        //sets the text to the recyler view text

    }

    //set the data receive to user and that to userlist
    fun setData(comment: List<CommentData>) {
        this.commentList = comment
        notifyDataSetChanged()
    }
}