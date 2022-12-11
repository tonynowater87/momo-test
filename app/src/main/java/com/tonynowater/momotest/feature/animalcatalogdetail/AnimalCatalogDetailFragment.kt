package com.tonynowater.momotest.feature.animalcatalogdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.tonynowater.momotest.R
import com.tonynowater.momotest.databinding.FragmentAnimalCatalogDetailBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AnimalCatalogDetailFragment : Fragment() {

    private var _binding: FragmentAnimalCatalogDetailBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAnimalCatalogDetailBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_AnimalCatalogDetailFragment_to_AnimalCatalogFragment)
        }

        binding.textviewSecond.setOnClickListener {
            findNavController().navigate(R.id.action_AnimalCatalogDetailFragment_to_AnimalDetailFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}