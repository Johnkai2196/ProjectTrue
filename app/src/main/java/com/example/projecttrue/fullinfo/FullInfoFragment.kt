package com.example.projecttrue.fullinfo

import android.os.Bundle
import android.text.TextUtils
import android.text.method.LinkMovementMethod
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
import kotlinx.android.synthetic.main.card_layout.view.*
import java.util.*

// Name: Johnkai Cortez
// Student id: 2012960
//for the url base when getting image
private const val BASE_URL = "https://avoindata.eduskunta.fi/"

/**
 * A simple [Fragment] subclass.
 * Use the [FullInfoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FullInfoFragment : Fragment() {
    //argument receive from navigation
    private val args by navArgs<FullInfoFragmentArgs>()

    //initialize our viewmodel
    lateinit var viewModels: ViewModels

    //initialize our binding
    lateinit var binding: FragmentFullInfoBinding

    //Just to count how old
    val currentYear: Int = Calendar.getInstance().get(Calendar.YEAR)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_full_info, container, false)

        var text = when (args.info.minister) {
            true -> "Minister\n"
            else -> "Member of parliament\n"
        } + args.info.first + " " + args.info.last + " \n" + (currentYear - args.info.bornYear) + " Years old \nConstituency " + args.info.constituency + "\n" + when (args.info.twitter) {
            "" -> "No twitter account"
            else -> args.info.twitter
        }

        binding.allinfo.setMovementMethod(LinkMovementMethod.getInstance())
        binding.allinfo.setText(text)

        Picasso.get().load(BASE_URL + args.info.picture).into(binding.imageView)

        // Recyclerview
        val adapter = CommentAdapter()
        val recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModels = ViewModelProvider(this).get(ViewModels::class.java)
        binding.comment.setOnClickListener {
            insertCommentDatabase()
            binding.editText.text.clear()
            binding.editTextNumber.text.clear()
        }
        viewModels.readComment.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            adapter.setData(it.filter { it.personNumber == args.info.personNumber })
        })
        return binding.root
    }

    private fun insertCommentDatabase() {
        val personNumber: Int = args.info.personNumber
        val comment = binding.editText.text.toString()
        val rate = when (binding.editTextNumber.text.toString()) {
            "" -> 0
            else -> Integer.parseInt(binding.editTextNumber.text.toString())
        }

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
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()
            // Navigate Back

        } else {
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_LONG)
                .show()
        }
    }

    private fun inputCheck(comment: String, rate: Int): Boolean {
        return !(TextUtils.isEmpty(comment) && rate == 5)
    }

}

