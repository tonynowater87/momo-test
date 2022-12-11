package com.tonynowater.momotest.ui.animalcatalog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.tonynowater.momotest.databinding.FragmentAnimalCatalogBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class AnimaCatalogFragment : Fragment() {

    private var _binding: FragmentAnimalCatalogBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAnimalCatalogBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(AnimaCatalogFragmentDirections.actionAnimalCatalogFragmentToAnimalCatalogDetailFragment(catalogId = 1))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}