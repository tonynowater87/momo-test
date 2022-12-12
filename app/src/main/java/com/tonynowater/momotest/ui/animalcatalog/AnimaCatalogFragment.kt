package com.tonynowater.momotest.ui.animalcatalog

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.tonynowater.momotest.R
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

    private val adapter = AnimalCatalogListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAnimalCatalogBinding.inflate(inflater, container, false)
        viewModel.uiState.observe(viewLifecycleOwner) {
            when (it) {
                is AnimalCatalogUiState.Loading -> {
                    binding.progressBar.isVisible = true
                }
                is AnimalCatalogUiState.Success -> {
                    binding.progressBar.isVisible = false
                    dataBinding(it)
                }
                is AnimalCatalogUiState.Error -> {
                    binding.progressBar.isVisible = false
                    MaterialAlertDialogBuilder(requireActivity())
                        .setTitle(R.string.errorTitle)
                        .setMessage(it.error.toString())
                        .setPositiveButton(resources.getString(android.R.string.ok), null)
                        .show()
                }
            }
        }
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.load()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun dataBinding(it: AnimalCatalogUiState.Success) {
        binding.recyclerViewCatalog.adapter = adapter.apply {
            onClickCatalog = { catalogId, title ->
                findNavController().navigate(
                    AnimaCatalogFragmentDirections.actionAnimalCatalogFragmentToAnimalCatalogDetailFragment(
                        catalogId = catalogId,
                        title = title
                    )
                )
            }
            submitList(it.data)
        }
    }
}