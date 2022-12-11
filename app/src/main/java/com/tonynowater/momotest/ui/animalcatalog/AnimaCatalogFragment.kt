package com.tonynowater.momotest.ui.animalcatalog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.tonynowater.momotest.databinding.FragmentAnimalCatalogBinding
import com.tonynowater.momotest.module.DataModule

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class AnimaCatalogFragment : Fragment() {

    private var _binding: FragmentAnimalCatalogBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel by viewModels<AnimalCatalogViewModel>(factoryProducer = {
        AnimalCatalogViewModelFactory(
            animalRepository = DataModule.getInstance().animalRepository
        )
    })

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
            findNavController().navigate(
                AnimaCatalogFragmentDirections.actionAnimalCatalogFragmentToAnimalCatalogDetailFragment(
                    catalogId = 1
                )
            )
        }

        viewModel.load()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}