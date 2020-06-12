package com.peterstev.peterstevuremgba.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
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

        private val name = itemView.findViewById<AppCompatTextView>(R.id.result_name)
        private val email = itemView.findViewById<AppCompatTextView>(R.id.result_email)
        private val carDesc = itemView.findViewById<AppCompatTextView>(R.id.result_car_desc)
        private val country = itemView.findViewById<AppCompatTextView>(R.id.result_country)
        private val gender = itemView.findViewById<AppCompatTextView>(R.id.result_gender_job)
        private val bio = itemView.findViewById<AppCompatTextView>(R.id.result_bio_text)

        fun setItems(item: FilterResult) {
            name.text = ("${item.firstName} ${item.lastName}")
            email.text = item.email
            carDesc.text = ("${item.carColor} ${item.carModel} ${item.carModelYear} model")
            country.text = item.country
            gender.text = ("${item.gender}, ${item.jobTitle}")
            bio.text = item.bio
        }
    }
}