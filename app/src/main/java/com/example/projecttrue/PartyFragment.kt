package com.example.projecttrue

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.projecttrue.database.ParlamentMemberData
import com.example.projecttrue.databinding.FragmentPartyBinding
import com.example.projecttrue.viewModels.ViewModels


class PartyFragment : Fragment() {

    lateinit var binding: FragmentPartyBinding
    lateinit var viewModels: ViewModels
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_party,
            container, false)
       viewModels= ViewModelProvider(this).get(ViewModels::class.java)

            adder()

        return binding.root
    }


    fun adder() {
        val test = ParlamentMemberData(15)
        viewModels.insert(test)
        binding.test.text = viewModels.toString()
    }

}