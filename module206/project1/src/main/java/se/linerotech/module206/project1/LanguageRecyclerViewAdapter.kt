package se.linerotech.module206.project1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import se.linerotech.module206.project1.common.CountryData
import se.linerotech.module206.project1.databinding.LayoutCellBinding

class LanguageRecyclerViewAdapter (
    private val items: List<CountryData>,
    private val onCellClicked: (CountryData) -> Unit
    ): RecyclerView.Adapter<LanguageRecyclerViewAdapter.LanguageViewHolder>() {
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): LanguageViewHolder {
            return LanguageViewHolder.create(parent, onCellClicked)
        }

        override fun onBindViewHolder(
            holder: LanguageViewHolder,
            position: Int
        ) {
            val countries = items[position]
            holder.bind(countries)
        }

        override fun getItemCount(): Int {
            return items.size
        }

        class LanguageViewHolder (
            private val binding: LayoutCellBinding,
            private val onCellClicked: (CountryData) -> Unit,
        ) : RecyclerView.ViewHolder(binding.root) {
            fun bind(country: CountryData) {
                binding.countryName.text = country.language
                binding.language.visibility = View.GONE
                binding.imgFlag.visibility = View.GONE
                binding.countryCell.setOnClickListener { onCellClicked(country) }
            }

            companion object {
                fun create(parent: ViewGroup, onCellClicked: (CountryData) -> Unit): LanguageViewHolder {
                    val binding =
                        LayoutCellBinding
                            .inflate(LayoutInflater.from(parent.context), parent, false)

                    return LanguageViewHolder(binding, onCellClicked)
                }
            }
        }
    }