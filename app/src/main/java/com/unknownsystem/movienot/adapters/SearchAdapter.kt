package com.unknownsystem.movienot.adapters


import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.unknownsystem.movienot.databinding.ResultItemBinding
import com.unknownsystem.movienot.utils.Helper
import com.unknownsystem.movienot.models.Result

class SearchAdapter(private val listener: onSearchResultClickListener?, results: List<Result>) :
    RecyclerView.Adapter<SearchAdapter.SearchViewHolder?>() {
    private val results: List<Result> = results
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding = ResultItemBinding.inflate(LayoutInflater.from(parent.context))
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        if (position != RecyclerView.NO_POSITION) {
            holder.bindData(results[position])
            holder.itemView.setOnClickListener(View.OnClickListener { v: View? ->
                listener?.onResultClick(
                    results[position]
                )
            })
        }
    }


    inner class SearchViewHolder(var binding: ResultItemBinding) : RecyclerView.ViewHolder(
        binding.root
    ) {
        fun bindData(result: Result?) {
            if (result != null) {
                result.poster_path?.let {
                    Helper.loadImage(binding.root.context,
                        it, binding.resultImage)
                }
                binding.rating.setText(result.vote_average.toString() + "")
                binding.title.setText(result.original_title)
            }
        }
    }

    interface onSearchResultClickListener {
        fun onResultClick(result: Result?)
    }

    override fun getItemCount(): Int {
        return results.size
    }
}