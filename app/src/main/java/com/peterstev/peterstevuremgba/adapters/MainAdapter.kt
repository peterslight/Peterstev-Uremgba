package com.peterstev.peterstevuremgba.adapters

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.peterstev.peterstevuremgba.R
import com.peterstev.peterstevuremgba.models.UserAccount
import com.peterstev.peterstevuremgba.utils.ColorFactory
import de.hdodenhof.circleimageview.CircleImageView

class MainAdapter(
    private val context: Context,
    private var list: List<UserAccount>,
    private val listener: MainItemClick
) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            LayoutInflater.from(context).inflate(R.layout.filter_item, parent, false),
            context
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.apply {
            val userAccount = list[position]
            itemView.setOnClickListener {
                listener.onItemClick(userAccount)
            }
            setItems(userAccount)
            setIsRecyclable(false)
        }
    }

    fun updateList(items: List<UserAccount>) {
        list = items
        notifyDataSetChanged()
    }

    class MainViewHolder(itemView: View, private val context: Context) :
        RecyclerView.ViewHolder(itemView) {
        private val avatar = itemView.findViewById<CircleImageView>(R.id.home_img_avatar)
        private val name = itemView.findViewById<AppCompatTextView>(R.id.home_tv_username)
        private val date = itemView.findViewById<AppCompatTextView>(R.id.home_tv_date)
        private val gender = itemView.findViewById<AppCompatTextView>(R.id.home_tv_gender)
        private val separator = itemView.findViewById<View>(R.id.main_separator)
        private val country = itemView.findViewById<ChipGroup>(R.id.main_country_group)
        private val colors = itemView.findViewById<ChipGroup>(R.id.main_color_group)

        fun setItems(userAccount: UserAccount) {
            Glide.with(avatar).load(userAccount.avatar).into(avatar)
            name.text = userAccount.fullName
            gender.text = userAccount.gender
            date.text = userAccount.createdAt

            if (userAccount.colors.isEmpty() or userAccount.countries.isEmpty())
                separator.visibility = View.GONE
            else separator.visibility = View.VISIBLE

            if (userAccount.colors.isEmpty()) colors.visibility = View.INVISIBLE
            else colors.visibility = View.VISIBLE

            if (userAccount.countries.isEmpty()) country.visibility = View.GONE
            else country.visibility = View.VISIBLE

            userAccount.colors.forEach { color ->
                val chip = Chip(colors.context)
                chip.text = color
                chip.chipBackgroundColor = ColorStateList
                    .valueOf(
                        Color.parseColor(
                            ColorFactory()
                                .getMatchingColor(color)
                        )
                    )
                colors.addView(chip)
            }
            userAccount.countries.forEach { color ->
                val chip = Chip(colors.context)
                chip.text = color
                country.addView(chip)
            }
        }
    }

    interface MainItemClick {
        fun onItemClick(userItem: UserAccount)
    }
}