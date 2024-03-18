package se.linerotech.module206.project1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch
import se.linerotech.module206.project1.common.CountryData
import se.linerotech.module206.project1.country.CountryUIState
import se.linerotech.module206.project1.country.CountryViewModel
import se.linerotech.module206.project1.databinding.FragmentLanguageListBinding
import se.linerotech.module206.project1.databinding.FragmentPopulationListBinding


class PopulationListFragment : Fragment() {
    private var _binding: FragmentPopulationListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CountryViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPopulationListBinding.inflate(layoutInflater, container, false)
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
                        is CountryUIState.Loaded -> {showPopulation(it.countries)}
                        CountryUIState.Failure -> {}
                    }

                }
            }
        }
    }

    private fun showPopulation(population: List<CountryData>) {
        binding.recyclerView.apply {
            visibility = View.VISIBLE
            adapter = PopulationRecyclerViewAdapter(items = population, onCellClicked = {})
            layoutManager = LinearLayoutManager(this@PopulationListFragment.context)
            setHasFixedSize(true)
        }
    }
}