package com.tonynowater.momotest.ui.animaldetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.tonynowater.momotest.databinding.FragmentAnimalDetailBinding
import com.tonynowater.momotest.module.DataModule

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
            println("uiState = $it")
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Toast.makeText(requireContext(), "animalId=${args.animalId}", Toast.LENGTH_SHORT).show()
        binding.textviewThird.setOnClickListener {
            findNavController().popBackStack()
        }

        viewModel.load(args.animalId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}