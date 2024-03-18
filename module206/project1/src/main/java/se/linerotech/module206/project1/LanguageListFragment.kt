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

class LanguageListFragment : Fragment() {
    private var _binding: FragmentLanguageListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CountryViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLanguageListBinding.inflate(layoutInflater, container, false)
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
                        is CountryUIState.Loaded -> {showLanguage(it.countries)}
                        CountryUIState.Failure -> {}
                    }

                }
            }
        }
    }

    private fun showLanguage(language: List<CountryData>) {
        binding.recyclerView.apply {
            visibility = View.VISIBLE
            adapter = LanguageRecyclerViewAdapter(items = language, onCellClicked = {})
            layoutManager = LinearLayoutManager(this@LanguageListFragment.context)
            setHasFixedSize(true)
        }
    }
}