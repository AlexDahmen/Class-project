package se.linerotech.module206.project1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import se.linerotech.module206.project1.common.CountryData
import se.linerotech.module206.project1.databinding.LayoutCellBinding

class CountryRecyclerViewAdapter (
    private val items: List<CountryData>,
    private val onCellClicked: () -> Unit
): RecyclerView.Adapter<CountryRecyclerViewAdapter.CountryViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CountryViewHolder {
        return CountryViewHolder.create(parent)
    }

    override fun onBindViewHolder(
        holder: CountryViewHolder,
        position: Int
    ) {
        val countries = items[position]
        holder.bind(countries)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class CountryViewHolder (
        private val binding: LayoutCellBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(country: CountryData) {
            binding.countryName.text = country.country
            binding.language.text = country.language
            loadImage(country.flagUrl)
        }
        private fun loadImage(url: String) {
            Glide
                .with(binding.imgFlag)
                .load(url)
                .centerCrop()
                .into(binding.imgFlag)
        }
        companion object {
            fun create(parent: ViewGroup): CountryViewHolder {
                val binding =
                    LayoutCellBinding
                        .inflate(LayoutInflater.from(parent.context), parent, false)

                return CountryViewHolder(binding)
            }
        }
    }



}