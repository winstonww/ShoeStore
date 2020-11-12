package com.udacity.shoestore.screens.instructions

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewPropertyAnimator
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.DataBinderMapperImpl
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentInstructionsBinding
import com.udacity.shoestore.models.LoginState
import com.udacity.shoestore.models.LoginViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [InstructionsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InstructionsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding : FragmentInstructionsBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_instructions, container, false)


        binding.nextButtonFragmentInstructions.setOnClickListener {
            onLogin()
        }

        return binding.root
    }

    private fun onLogin() {
        findNavController().navigate(
            InstructionsFragmentDirections.actionInstructionsFragmentToShoeListFragment())
    }

}