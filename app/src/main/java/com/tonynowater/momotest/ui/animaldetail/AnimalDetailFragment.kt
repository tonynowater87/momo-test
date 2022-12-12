package com.tonynowater.momotest.ui.animaldetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.tonynowater.momotest.R
import com.tonynowater.momotest.databinding.FragmentAnimalDetailBinding
import com.tonynowater.momotest.module.DataModule
import com.tonynowater.momotest.ui.util.DateFormatUtil

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AnimalDetailFragment : Fragment() {

    private var _binding: FragmentAnimalDetailBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel by viewModels<AnimalDetailViewModel>(factoryProducer = {
        AnimalDetailViewModelFactory(
            animalRepository = DataModule.getInstance().animalRepository
        )
    })

    private val args: AnimalDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAnimalDetailBinding.inflate(inflater, container, false)
        viewModel.uiState.observe(viewLifecycleOwner) {
            when (it) {
                is AnimalDetailUiState.Loading -> {
                    binding.progressBar.isVisible = true
                }
                is AnimalDetailUiState.Success -> {
                    binding.progressBar.isVisible = false
                    dataBinding(it)
                }
                is AnimalDetailUiState.Error -> {
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
        viewModel.load(args.animalId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun dataBinding(it: AnimalDetailUiState.Success) {
        binding.textviewName.text = it.data.aNameCh
        binding.textviewNameEn.text = it.data.aNameEn
        binding.textViewAlsoKnown.text = it.data.aAlsoKnown.ifEmpty {
            getString(R.string.noAlsoKnown)
        }
        binding.textViewBehavior.text = it.data.aBehavior
        binding.textViewFeature.text = it.data.aFeature
        binding.textViewLastUpdate.text = getString(
            R.string.lastUpdate,
            DateFormatUtil.formatLastUpdateDate(it.data.aImportantDate)
        )
        Glide.with(requireContext())
            .load(it.data.aPicture1Url)
            .centerCrop()
            .into(binding.imageViewPicture)
    }
}