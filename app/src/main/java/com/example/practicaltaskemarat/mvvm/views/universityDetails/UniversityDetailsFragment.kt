package com.example.practicaltaskemarat.mvvm.views.universityDetails

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView

import com.example.practicaltaskemarat.databinding.UniversityDetailsFragmentBinding
import com.example.practicaltaskemarat.mvvm.views.ListingFragmentVM
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList

@AndroidEntryPoint
class UniversityDetailsFragment : Fragment() {

    val viewModel: ListingFragmentVM by viewModels()

    private lateinit var binding: UniversityDetailsFragmentBinding
    private val args: UniversityDetailsFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = UniversityDetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val university = args.university
        binding.txtName.text = university.name
        binding.textCountry.text = university.country
        binding.textState.text = university.state_province
        binding.textWebPage.text = university.web_pages.toString()

        bindClickListner()
    }

    private fun bindClickListner() {
        binding.imgRefresh.setOnClickListener {
            viewModel.deleteAll()
            findNavController().navigateUp()
        }
    }




}
