package com.unknownsystem.movienot.ui.detail

import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigation
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.unknownsystem.movienot.R
import com.unknownsystem.movienot.adapters.GenreAdapter
import com.unknownsystem.movienot.databinding.FragmentDetailBinding
import com.unknownsystem.movienot.models.Result
import com.unknownsystem.movienot.ui.home.HomeViewModel
import com.unknownsystem.movienot.utils.Helper
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.launch
import java.util.ArrayList

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var binding:FragmentDetailBinding
    private lateinit var result:Result
    var genres: ArrayList<String>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
        genres = ArrayList()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail, container, false)
        binding = FragmentDetailBinding.bind(view)
        result = DetailFragmentArgs.fromBundle(requireArguments()).result
        bindView()
        return binding.root
    }

    private fun bindView() {
        if (result != null) {
            result.backdrop_path?.let { Helper.loadImage(requireContext(), it, binding.coverImage) }
            result.poster_path?.let { Helper.loadImage(requireContext(), it, binding.posterImage) }
            result.genre_ids?.let { Helper.getGenresFromIds(it)?.let { genres!!.addAll(it) } }
            binding.rating.text = result.vote_average.toString() + ""
            binding.title.text = result.title
            binding.topTitle.text = result.title
            binding.overView.text = result.overview
            binding.voters.text = result.vote_count.toString() + ""
        }
        val layoutManager = FlexboxLayoutManager(requireContext())
        layoutManager.flexDirection = FlexDirection.ROW
        layoutManager.justifyContent = JustifyContent.FLEX_START
        binding.generesRecyclerview.layoutManager = layoutManager
        val adapter = genres?.let { GenreAdapter(it) }
        binding.generesRecyclerview.adapter = adapter
        binding.backButton.setOnClickListener { closeFragment() }

        binding.netflix.setOnClickListener {
            openNetflix(result.title)
        }
    }

    private fun openNetflix(judul: String?){
        val packageManager: PackageManager = requireActivity().packageManager
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.netflix.com/search?q=$judul"))
        val resolveInfo: ResolveInfo? = packageManager.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY)
//       intent.setPackage("com.netflix.mediaclient")

        if (resolveInfo != null){
            startActivity(intent)
        }else{
            val intentPlayStore = Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.netflix.mediaclient"))
            startActivity(intentPlayStore)
        }


    }


    private fun closeFragment() {
        Navigation.findNavController(binding.root).popBackStack()
    }


}