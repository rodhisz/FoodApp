package com.rsz.foodapp.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rsz.foodapp.R
import com.rsz.foodapp.model.dummy.HomeModelHorizontal
import com.rsz.foodapp.model.response.home.Data
import kotlinx.android.synthetic.main.item_home_horinzontal.view.*

class HomeAdapter (
    private val listData : List<Data>,
    private val itemAdapterCallback : ItemAdapterCallback
    ) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_home_horinzontal, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeAdapter.ViewHolder, position: Int) {
        holder.bind(listData[position], itemAdapterCallback)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    class ViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView){
        fun bind(data : Data, itemAdapterCallback : ItemAdapterCallback){
            itemView.apply {
                tv_home_food_title_horizontal.text = data.name
                rb_home_food_rating_horizontal.rating = data.rate.toFloat() ?: 0.0f

                Glide.with(context)
                    .load(data.picturePath)
                    .into(iv_home_food_horizontal)

                itemView.setOnClickListener {
                    itemAdapterCallback.onClick(it, data)
                }
            }
        }
    }

    interface ItemAdapterCallback {
        fun onClick(v : View, data:Data)
    }
}