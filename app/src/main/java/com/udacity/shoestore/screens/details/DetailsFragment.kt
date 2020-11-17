package com.udacity.shoestore.screens.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentDetailsBinding
import com.udacity.shoestore.models.SaveState
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.models.ShoeViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [DetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailsFragment : Fragment() {

    private val viewModel : ShoeViewModel by activityViewModels()

    private val shoeData = Shoe("", 0.0, "", "")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val binding = FragmentDetailsBinding.inflate(
            inflater, container, false)

        binding.shoeViewModel = viewModel
        binding.lifecycleOwner = this
        binding.shoeData = shoeData

        binding.cancelButton.setOnClickListener {
            val action = DetailsFragmentDirections.actionDetailsFragmentToShoeListFragment()
            findNavController().navigate(action)
        }

        viewModel.saveState.observe(this as LifecycleOwner, Observer{ss ->
            when(ss) {
                SaveState.SAVE -> {
                    navigateToShoeList()
                    viewModel.onEventSaveComplete()
                }
            }
        })

        return binding.root
    }

    private  fun navigateToShoeList() {
        val action = DetailsFragmentDirections.actionDetailsFragmentToShoeListFragment()
        findNavController().navigate(action)

    }

}