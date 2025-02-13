package com.example.question1.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.question1.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!
    private val args: SecondFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvName.text = args.name.ifEmpty { "John Doe" }

        val navController = findNavController()
        val savedStateHandle = navController.currentBackStackEntry?.savedStateHandle

        savedStateHandle?.getLiveData<String>("SELECTED_USER")?.observe(viewLifecycleOwner) { selectedUser ->
            if (!selectedUser.isNullOrEmpty()) {
                binding.tvName.text = selectedUser
            }
        }

        binding.btnBack.setOnClickListener { findNavController().navigateUp() }

        binding.btnChooseUser.setOnClickListener {
            val action = SecondFragmentDirections.actionSecondFragmentToThirdFragment(args.name)
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
