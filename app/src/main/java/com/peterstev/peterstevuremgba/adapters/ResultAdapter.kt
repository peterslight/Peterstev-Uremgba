package com.peterstev.peterstevuremgba.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.peterstev.peterstevuremgba.R
import com.peterstev.peterstevuremgba.models.FilterResult

class ResultAdapter(
    private val context: Context,
    private val list: ArrayList<FilterResult>
) : RecyclerView.Adapter<ResultAdapter.ResultViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
        return ResultViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.result_item,
                parent,
                false
            )
        )
    }

    fun updateList(item: List<FilterResult>) {
        list.addAll(item)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = list.size


    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) =
        holder.setItems(list[position])


    class ResultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val name = itemView.findViewById<AppCompatTextView>(R.id.result_tv_username)
        private val email = itemView.findViewById<AppCompatTextView>(R.id.result_tv_email)
        private val car = itemView.findViewById<Chip>(R.id.result_chip_car)
        private val gender = itemView.findViewById<Chip>(R.id.result_chip_gender)
        private val country = itemView.findViewById<Chip>(R.id.result_chip_country)
        private val occupation = itemView.findViewById<AppCompatTextView>(R.id.result_tv_job)
        private val bio = itemView.findViewById<AppCompatTextView>(R.id.result_bio_text)

        fun setItems(item: FilterResult) {
            name.text = ("${item.firstName} ${item.lastName}")
            email.text = item.email
            car.text = ("${item.carColor} ${item.carModelYear} ${item.carModel}")
            country.text = item.country
            gender.text = item.gender
            bio.text = item.bio
            occupation.text = item.jobTitle

//            if (item.gender.equals("male",  true))
        }
    }
}