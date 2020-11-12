package com.udacity.shoestore.screens.shoeList

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
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.models.ShoeViewModel
import timber.log.Timber

/**
 * A simple [Fragment] subclass.
 * Use the [ShoeListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ShoeListFragment : Fragment() {

    private val viewModel : ShoeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val binding : FragmentShoeListBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoe_list, container, false)

//        val viewModel = ViewModelProvider(this).get(ShoeViewModel::class.java)

//        binding.shoeTextview.text = viewModel.shoeList.value?.joinToString() ?: ""
        viewModel.shoeList.observe(this as LifecycleOwner, Observer {
            binding.shoeTextview.text = viewModel.shoeList.value?.joinToString() ?: ""
        })

        binding.addShoeButton.setOnClickListener {
            Timber.i("in addShoeButton listener")
            Timber.i(viewModel.shoeList.value?.joinToString())
            val action = ShoeListFragmentDirections.actionShoeListFragmentToDetailsFragment()
            findNavController().navigate(action)
        }

        return binding.root
    }

    

}