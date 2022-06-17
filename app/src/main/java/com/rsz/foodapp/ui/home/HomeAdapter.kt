package com.rsz.foodapp.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rsz.foodapp.R
import com.rsz.foodapp.model.dummy.HomeModelHorizontal
import kotlinx.android.synthetic.main.item_home_horinzontal.view.*

class HomeAdapter (
    private val listData : List<HomeModelHorizontal>,
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
        fun bind(data : HomeModelHorizontal, itemAdapterCallback : ItemAdapterCallback){
            itemView.apply {
                tv_home_food_title_horizontal.text = data.title
                rb_home_food_rating_horizontal.rating = data.rating

//                Glide.with(context)
//                    .load(data.src)
//                    .into(iv_home_food_horizontal)

                itemView.setOnClickListener {
                    itemAdapterCallback.onClick(it, data)
                }
            }
        }
    }

    interface ItemAdapterCallback {
        fun onClick(v : View, data:HomeModelHorizontal)
    }
}