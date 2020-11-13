package com.udacity.shoestore.screens.shoeList

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.models.ShoeViewModel
import kotlinx.android.synthetic.main.shoe_row.view.*
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

            for (shoe in viewModel.shoeList.value!!) {
                val view = layoutInflater.inflate(R.layout.shoe_row, null)
                view.name_text.setText(shoe.name)
                view.company_text.setText(shoe.company)
                view.size_text.setText(shoe.size.toString())
                view.desc_text.setText(shoe.description)
                binding.innerLayout.addView(view)
            }
//            binding.shoeTextview.text = viewModel.shoeList.value?.joinToString(separator = "\n") ?: ""
        })

        binding.addShoeButton.setOnClickListener {
            Timber.i("in addShoeButton listener")
            Timber.i(viewModel.shoeList.value?.joinToString(separator = "\n"))
            val action = ShoeListFragmentDirections.actionShoeListFragmentToDetailsFragment()
            findNavController().navigate(action)
        }

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.logout_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    

}