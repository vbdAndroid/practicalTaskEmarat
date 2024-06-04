package com.example.practicaltaskemarat.mvvm.views

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.practicaltaskemarat.UniversityItemClickListener
import com.example.practicaltaskemarat.dao.University
import com.example.practicaltaskemarat.databinding.ListingFragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList

@AndroidEntryPoint
class ListingFragment : Fragment() {
    private val viewModel: ListingFragmentVM by viewModels()
    private lateinit var binding: ListingFragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ListingFragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerViewUnivercity.layoutManager = LinearLayoutManager(context)

        viewModel.getListing()

        bindObservers()
    }

    private fun bindObservers() {
        viewModel.universityArrayListResposne.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                val listingAdapter = context?.let { it1 ->
                    ListingAdapter(
                        it1,
                        it as ArrayList<University>, object : UniversityItemClickListener {
                            override fun itemClickListener(university: University, position: Int) {
                                super.itemClickListener(university, position)
                                val action =
                                    ListingFragmentDirections.actionPopularMovieFragmentToUniversityDetailsFragment(
                                        university
                                    )
                                findNavController().navigate(action)
                            }
                        }
                    )
                }
                binding.recyclerViewUnivercity.adapter = listingAdapter
            }
        }
    }
}
