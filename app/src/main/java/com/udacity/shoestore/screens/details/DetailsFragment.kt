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
import com.udacity.shoestore.models.ShoeViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [DetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailsFragment : Fragment() {

//    private lateinit var viewModel : ShoeViewModel
    private val viewModel : ShoeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentDetailsBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_details, container, false)

//        viewModel = ViewModelProvider(this).get(ShoeViewModel::class.java)


        binding.cancelButton.setOnClickListener {
            val action = DetailsFragmentDirections.actionDetailsFragmentToShoeListFragment()
            findNavController().navigate(action)
        }

        binding.saveButton.setOnClickListener {

            viewModel.onEventSave(
                binding.shoeNameEdittext.text.toString(),
                binding.shoeSizeEdittext.text.toString(),
                binding.companyNameEdittext.text.toString(),
                binding.descriptionEdittext.text.toString()
            )
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