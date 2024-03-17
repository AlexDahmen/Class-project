package se.linerotech.module206.project1.country

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch
import se.linerotech.module206.project1.common.CountryData
import se.linerotech.module206.project1.databinding.FragmentCountryListBinding


class CountryListFragment : Fragment() {
    private var _binding: FragmentCountryListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CountryViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCountryListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observerState()
    }

    private fun observerState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.state.collect {
                    when (it) {
                        CountryUIState.Loading -> {}
                        is CountryUIState.Loaded -> {showCountry(it.countries)}
                        CountryUIState.Failure -> {}
                    }

                }
            }
        }
    }

    private fun showCountry(countries: List<CountryData>) {
        binding.recyclerView.apply {
            visibility = View.VISIBLE
            adapter = CountryRecyclerViewAdapter(countries, onCellClicked = ::showCountryDetails)
            layoutManager =
                LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
        }
    }

    private fun showCountryDetails(countryData: CountryData) {
        val action = CountryListFragmentDirections.countryDetailsAction(countryData)
        findNavController().navigate(action)
    }
}