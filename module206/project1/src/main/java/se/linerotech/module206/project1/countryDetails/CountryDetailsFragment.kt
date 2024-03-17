package se.linerotech.module206.project1.countryDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import se.linerotech.module206.project1.R
import se.linerotech.module206.project1.common.CountryData
import se.linerotech.module206.project1.databinding.FragmentCountryDetailsBinding


class CountryDetailsFragment : Fragment() {
    private var _binding: FragmentCountryDetailsBinding? = null
    private val binding get() = _binding!!
    private val args: CountryDetailsFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCountryDetailsBinding.inflate(layoutInflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val country = args.country
        showDetails(country)
    }

    private fun showDetails(countryData: CountryData) {
        binding.countryName.text = countryData.country
        binding.countryPopulation.text = getString(R.string.population).plus(' ').plus(countryData.population)
        binding.countryLanguage.text = countryData.language
        binding.countryRegion.text = countryData.region
        loadImage(countryData.flagUrl)

    }

    private fun loadImage(url: String) {
        Glide
            .with(binding.flagImage)
            .load(url)
            .apply(RequestOptions().transform(CenterCrop()))
            .into(binding.flagImage)
    }
}