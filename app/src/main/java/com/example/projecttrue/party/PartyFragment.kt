package com.example.projecttrue.party

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projecttrue.R
import com.example.projecttrue.databinding.FragmentPartyBinding
import com.example.projecttrue.viewModels.ViewModels

// Name: Johnkai Cortez
// Student id: 2012960
// 10.10.2021
class PartyFragment : Fragment() {
    //Variable for binding
    lateinit var binding: FragmentPartyBinding

    //Variable for viewModels
    lateinit var viewModels: ViewModels

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //initialize my binding
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_party,
            container, false
        )
        // Recyclerview
        //activate the party adapter and set it to adapter variable
        val adapter = Adapter()
        //set my custom edit recycler
        val recyclerView = binding.textGrid
        //set the variable adapter to recyclerView.adapter
        recyclerView.adapter = adapter
        //set the layoutManager to linearLayoutmanager
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        //initialize my viewmodel
        viewModels = ViewModelProvider(this).get(ViewModels::class.java)
        //observer the data
        viewModels.readParty.observe(viewLifecycleOwner, Observer {
            //sets the data ot adapter
            adapter.setData(it)

        })

        return binding.root
    }


}