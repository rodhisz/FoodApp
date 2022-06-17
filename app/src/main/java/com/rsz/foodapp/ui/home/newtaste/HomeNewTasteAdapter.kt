package com.rsz.foodapp.ui.home.newtaste

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rsz.foodapp.R
import com.rsz.foodapp.helper.Helpers.formatPrice
import com.rsz.foodapp.model.dummy.HomeModelVertical
import kotlinx.android.synthetic.main.item_home_vertical.view.*

class HomeNewTasteAdapter (
    private val listData : List<HomeModelVertical>,
    private val itemAdapterCallback : ItemAdapterCallback
    ) : RecyclerView.Adapter<HomeNewTasteAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeNewTasteAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_home_vertical, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeNewTasteAdapter.ViewHolder, position: Int) {
        holder.bind(listData[position], itemAdapterCallback)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    class ViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView){
        fun bind(data : HomeModelVertical, itemAdapterCallback : ItemAdapterCallback){
            itemView.apply {
                tv_home_food_title_vertical.text = data.title
                tv_home_food_price_vertical.formatPrice(data.price)
                rb_home_food_rating_vertical.rating = data.rating

//                Glide.with(context)
//                    .load(data.src)
//                    .into(iv_home_food_vertical)

                itemView.setOnClickListener {
                    itemAdapterCallback.onClick(it, data)
                }
            }
        }
    }

    interface ItemAdapterCallback {
        fun onClick(v : View, data:HomeModelVertical)
    }
}