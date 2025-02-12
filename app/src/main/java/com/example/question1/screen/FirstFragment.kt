package com.example.question1.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.question1.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCheck.setOnClickListener {
            checkPalindrome()
        }

        binding.btnNext.setOnClickListener {
            goToNextScreen()
        }
    }


    private fun checkPalindrome() {
        val word = binding.fieldPalindrome.text.toString()
            .filter { it.isLetterOrDigit() }
            .lowercase()
        val reversedWord = word.reversed()

        val message = if (word == reversedWord) {
            "It's a Palindrome"
        } else {
            "Not a Palindrome"
        }
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }


    private fun goToNextScreen() {
        val name = binding.fieldName.text.toString()

        if (name.isEmpty()) {
            Toast.makeText(requireContext(), "Fill in the name", Toast.LENGTH_SHORT).show()
        } else {
            val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(name)
            findNavController().navigate(action)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}