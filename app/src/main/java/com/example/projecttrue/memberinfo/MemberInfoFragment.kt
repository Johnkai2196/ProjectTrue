package com.example.projecttrue.memberinfo

import android.os.Bundle
import android.util.Log
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
import com.example.projecttrue.party.Adapter
import com.example.projecttrue.viewModels.ViewModels


class MemberInfoFragment : Fragment() {
    private val args by navArgs<MemberInfoFragmentArgs>()
    lateinit var viewModels: ViewModels
    lateinit var binding: FragmentMemberInfoBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_member_info, container, false)
        val adapter = MemberAdapter()
        val recyclerView = binding.textGrid
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        viewModels = ViewModelProvider(this).get(ViewModels::class.java)
        viewModels.readAllData.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            adapter.setData(it.filter { it.party ==args.picked})
        })
        return binding.root

    }


}