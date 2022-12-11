package com.tonynowater.momotest.feature.animaldetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.tonynowater.momotest.R
import com.tonynowater.momotest.databinding.FragmentAnimalDetailBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AnimalDetailFragment : Fragment() {

    private var _binding: FragmentAnimalDetailBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAnimalDetailBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textviewThird.setOnClickListener {
            findNavController().navigate(R.id.action_AnimalDetailFragment_to_AnimalCatalogDetailFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}