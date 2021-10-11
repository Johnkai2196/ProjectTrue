package com.example.projecttrue.party

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projecttrue.R
import com.example.projecttrue.databinding.FragmentPartyBinding
import com.example.projecttrue.viewModels.ViewModels

// Name: Johnkai Cortez
// Student id: 2012960
class PartyFragment : Fragment() {
    //initialize our binding
    lateinit var binding: FragmentPartyBinding

    //initialize our viewmodel
    lateinit var viewModels: ViewModels
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_party,
            container, false
        )
        //activate the
        val adapter = Adapter()
        val recyclerView = binding.textGrid
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModels = ViewModelProvider(this).get(ViewModels::class.java)
        viewModels.readParty.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            adapter.setData(it)

        })

        return binding.root
    }


}