package se.linerotech.module206.project1

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch
import se.linerotech.module206.project1.common.CountryData
import se.linerotech.module206.project1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: CountryViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observerState()
    }

    private fun observerState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.state.collect {
                    when (it) {
                        CountryUIState.Loading -> showProgressBar()
                        is CountryUIState.Loaded -> showCountry(it.countries)
                        CountryUIState.Failure -> showErrorMessage()
                    }

                }
            }
        }
    }

    private fun showErrorMessage() {
        Toast.makeText(this, getString(R.string.unable_to_retrieve), Toast.LENGTH_LONG).show()
    }

    private fun showCountry(countries: List<CountryData>) {
        hideProgressBar()
        binding.recyclerView.apply {
            visibility = View.VISIBLE
            adapter = CountryRecyclerViewAdapter(countries)
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
        }
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
    }
}
