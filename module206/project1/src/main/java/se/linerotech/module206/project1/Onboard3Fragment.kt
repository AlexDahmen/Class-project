package se.linerotech.module206.project1

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import se.linerotech.module206.project1.databinding.FragmentOnboard3Binding


class Onboard3Fragment : Fragment() {
    private var _binding: FragmentOnboard3Binding? = null
    private val binding get() = _binding!!
        override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
            _binding = FragmentOnboard3Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.buttonShowCountries.setOnClickListener { activity?.startActivity(Intent(context, CountryListActivity::class.java)) }
    }
}