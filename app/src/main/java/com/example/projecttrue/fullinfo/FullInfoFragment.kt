package com.example.projecttrue.fullinfo

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projecttrue.R
import com.example.projecttrue.database.CommentData
import com.example.projecttrue.databinding.FragmentFullInfoBinding
import com.example.projecttrue.viewModels.ViewModels
import com.squareup.picasso.Picasso
import java.util.*

// Name: Johnkai Cortez
// Student id: 2012960
// 11.10.2021

//for the url base when getting image
private const val BASE_URL = "https://avoindata.eduskunta.fi/"

/**
 * A simple [Fragment] subclass.
 */
class FullInfoFragment : Fragment() {
    //argument receive from navigation
    private val args by navArgs<FullInfoFragmentArgs>()

    //Variable our viewmodel
    lateinit var viewModels: ViewModels

    //Variable for binding
    lateinit var binding: FragmentFullInfoBinding

    //Just to count how old they are
    val currentYear: Int = Calendar.getInstance().get(Calendar.YEAR)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //initialize my binding
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_full_info, container, false)

        //text that show all the info i want to show in fullinfofragment and also the twitter link has been made in xml
        val text = when (args.info.minister) {
            true -> "Minister\n"
            else -> "Member of parliament\n"
        } + args.info.first + " " + args.info.last + " \n" + (currentYear - args.info.bornYear) + " Years old \nConstituency " + args.info.constituency + "\n" + when (args.info.twitter) {
            "" -> "No twitter account"
            else -> args.info.twitter
        }
        //then set the text
        binding.allinfo.setText(text)
        //Picasso get the picture the download it to the cache and also i set it ot the image View
        Picasso.get().load(BASE_URL + args.info.picture).into(binding.imageView)

        // Recyclerview
        //activate the info adapter and set it to adapter variable
        val adapter = CommentAdapter()
        //set my custom edit recycler
        val recyclerView = binding.recyclerView
        // set the variable adapter to recyclerView.adapter
        recyclerView.adapter = adapter
        //set the layoutManager to linearLayoutmanager
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        //initialize my viewmodel
        viewModels = ViewModelProvider(this).get(ViewModels::class.java)
        //sets click listener kun painan comment se lähettä
        binding.comment.setOnClickListener {
            //add to database
            insertCommentDatabase()
            //empty the text after comment button press
            binding.editText.text.clear()
            binding.editTextNumber.text.clear()
        }
        //observer the data
        viewModels.readComment.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            //set the data filter with the person number
            adapter.setData(it.filter { it.personNumber == args.info.personNumber })
        })
        return binding.root
    }

    //insert to database the comment
    private fun insertCommentDatabase() {
        //set variable person number from the info args
        val personNumber: Int = args.info.personNumber
        //set variable comment from the info args
        val comment = binding.editText.text.toString()
        //set variable person number  from the info args
        val rate = when (binding.editTextNumber.text.toString()) {
            "" -> 0
            else -> Integer.parseInt(binding.editTextNumber.text.toString())
        }
//if the input is wrong it will not add
        if (inputCheck(comment, rate)) {
            // Create Comment Object
            val comment = CommentData(
                0,
                personNumber,
                comment,
                Integer.parseInt(rate.toString())
            )
            // Add Data to Database
            viewModels.addComment(comment)

        } else {
            //say to the person to fill right
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_LONG)
                .show()
        }
    }

    private fun inputCheck(comment: String, rate: Int): Boolean {
//return not if the requirement fulfil
        return !(TextUtils.isEmpty(comment) && rate < 5 || rate > 5 || TextUtils.isEmpty(comment) || rate < 0 || rate == 0)
    }

}

