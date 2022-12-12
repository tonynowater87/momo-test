package com.tonynowater.momotest.ui.animalcatalogdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.tonynowater.momotest.R
import com.tonynowater.momotest.databinding.FragmentAnimalCatalogDetailBinding
import com.tonynowater.momotest.module.DataModule

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AnimalCatalogDetailFragment : Fragment() {

    private var _binding: FragmentAnimalCatalogDetailBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel by viewModels<AnimalCatalogDetailViewModel>(factoryProducer = {
        AnimalCatalogDetailViewModelFactory(
            animalRepository = DataModule.getInstance().animalRepository
        )
    })

    private val listAdapter = AnimalDetailListAdapter()
    private val args: AnimalCatalogDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAnimalCatalogDetailBinding.inflate(inflater, container, false)
        viewModel.uiState.observe(viewLifecycleOwner) {
            when (it) {
                is AnimalCatalogDetailUiState.Loading -> {
                    binding.progressBar.isVisible = true
                }
                is AnimalCatalogDetailUiState.Success -> {
                    binding.progressBar.isVisible = false
                    dataBinding(it)
                }
                is AnimalCatalogDetailUiState.Error -> {
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
        viewModel.load(catalogId = args.catalogId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun dataBinding(it: AnimalCatalogDetailUiState.Success) {
        binding.textViewInfo.text = it.data.eInfo
        binding.textViewMemo.text = it.data.eMemo.ifEmpty {
            getString(R.string.noMemo)
        }
        Glide.with(requireContext())
            .load(it.data.ePictureUrl)
            .centerCrop()
            .into(binding.imageViewPicture)
        binding.recyclerViewAnimal.adapter = listAdapter.apply {
            onClickAnimal = { animalId, title ->
                findNavController().navigate(
                    AnimalCatalogDetailFragmentDirections.actionAnimalCatalogDetailFragmentToAnimalDetailFragment(
                        animalId = animalId,
                        title = title
                    )
                )
            }
            submitList(it.data.animals)
        }
    }
}