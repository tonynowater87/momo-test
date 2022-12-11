package com.tonynowater.momotest.ui.animalcatalogdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.tonynowater.momotest.databinding.FragmentAnimalCatalogDetailBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AnimalCatalogDetailFragment : Fragment() {

    private var _binding: FragmentAnimalCatalogDetailBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val args: AnimalCatalogDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAnimalCatalogDetailBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Toast.makeText(requireContext(), "catalogId=${args.catalogId}", Toast.LENGTH_SHORT).show()

        binding.buttonSecond.setOnClickListener {

        }

        binding.textviewSecond.setOnClickListener {
            findNavController().navigate(
                AnimalCatalogDetailFragmentDirections.actionAnimalCatalogDetailFragmentToAnimalDetailFragment(
                    animalId = 2
                )
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}