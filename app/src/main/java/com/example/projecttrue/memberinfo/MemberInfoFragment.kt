package com.example.projecttrue.memberinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projecttrue.R
import com.example.projecttrue.databinding.FragmentMemberInfoBinding
import com.example.projecttrue.viewModels.ViewModels

// Name: Johnkai Cortez
// Student id: 2012960
// 11.10.2021

class MemberInfoFragment : Fragment() {
    //argument receive from navigation
    private val args by navArgs<MemberInfoFragmentArgs>()

    //Variable our viewmodel
    lateinit var viewModels: ViewModels

    //Variable for binding
    lateinit var binding: FragmentMemberInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //initialize my binding
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_member_info, container, false)

        // Recyclerview
        //activate the info adapter and set it to adapter variable
        val adapter = MemberAdapter()
        //set my custom edit recycler
        val recyclerView = binding.textGrid
        // set the variable adapter to recyclerView.adapter
        recyclerView.adapter = adapter
        //set the layoutManager to linearLayoutmanager
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        //initialize my viewmodel
        viewModels = ViewModelProvider(this).get(ViewModels::class.java)
        //observer the data
        viewModels.readAllData.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            //sets the data ot adapter and filters it to the party i picked
            adapter.setData(it.filter { it.party == args.picked })
        })
        return binding.root

    }


}