package com.example.practicaltaskemarat.mvvm.views.universityList

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practicaltaskemarat.UniversityItemClickListener
import com.example.practicaltaskemarat.databinding.UniversityListItemBinding
//import com.example.practicaltaskemarat.network.University
import com.example.practicaltaskemarat.dao.University

import java.util.ArrayList

class ListingAdapter(
    val context: Context,
    val arrayList: ArrayList<University>,
    private var universityItemClickListener: UniversityItemClickListener? = null
) : RecyclerView.Adapter<ListingAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = UniversityListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val university: University = arrayList.get(position)
        holder.binding.textUniName.text = university.name
        holder.binding.textUniState.text=university.state_province

        holder.itemView.setOnClickListener {
            universityItemClickListener?.itemClickListener(
                university,
                position
            )
        }

    }

    class MovieViewHolder(val binding: UniversityListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun getItemCount(): Int {
        return arrayList.size
    }
}

