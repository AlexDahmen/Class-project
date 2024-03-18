package se.linerotech.module206.project1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import se.linerotech.module206.project1.common.CountryData
import se.linerotech.module206.project1.databinding.LayoutCellBinding

class PopulationRecyclerViewAdapter(
    private val items: List<CountryData>,
    private val onCellClicked: (CountryData) -> Unit
): RecyclerView.Adapter<PopulationRecyclerViewAdapter.PopulationViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PopulationViewHolder {
        return PopulationViewHolder.create(parent, onCellClicked)
    }

    override fun onBindViewHolder(
        holder: PopulationViewHolder,
        position: Int
    ) {
        val countries = items[position]
        holder.bind(countries)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class PopulationViewHolder (
        private val binding: LayoutCellBinding,
        private val onCellClicked: (CountryData) -> Unit,
        private val context: Context
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(country: CountryData) {
            binding.countryName.text = country.country
            binding.language.text = context.getString(R.string.population).plus(country.population)
            binding.imgFlag.visibility = View.GONE
            binding.countryCell.setOnClickListener { onCellClicked(country) }
        }
        companion object {
            fun create(parent: ViewGroup, onCellClicked: (CountryData) -> Unit): PopulationViewHolder {
                val binding =
                    LayoutCellBinding
                        .inflate(LayoutInflater.from(parent.context), parent, false)

                return PopulationViewHolder(binding, onCellClicked, parent.context)
            }
        }
    }



}