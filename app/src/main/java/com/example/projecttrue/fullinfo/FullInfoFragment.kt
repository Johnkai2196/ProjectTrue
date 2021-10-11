package com.example.projecttrue.fullinfo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.projecttrue.R
import com.example.projecttrue.databinding.FragmentFullInfoBinding
import com.example.projecttrue.databinding.FragmentMemberInfoBinding
import com.example.projecttrue.memberinfo.MemberInfoFragmentArgs
import com.example.projecttrue.viewModels.ViewModels
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.card_layout.view.*
import java.util.*



//for the url base when getting image
private const val BASE_URL = "https://avoindata.eduskunta.fi/"
/**
 * A simple [Fragment] subclass.
 * Use the [FullInfoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FullInfoFragment : Fragment() {
    private val args by navArgs<FullInfoFragmentArgs>()
    lateinit var viewModels: ViewModels
    lateinit var binding: FragmentFullInfoBinding
    val currentYear: Int = Calendar.getInstance().get(Calendar.YEAR)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_full_info, container, false)
        binding.textView2.setText(args.info.first + " " + args.info.last + " \n" + (currentYear - args.info.bornYear) + " Years old \nConstituency " + args.info.constituency)
        Picasso.get().load(BASE_URL + args.info.picture).into(binding.imageView)
return binding.root
    }

}

