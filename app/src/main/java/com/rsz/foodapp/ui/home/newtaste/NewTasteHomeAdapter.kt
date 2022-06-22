package com.rsz.foodapp.ui.home.newtaste

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rsz.foodapp.R
import com.rsz.foodapp.helper.Helpers.formatPrice
import com.rsz.foodapp.model.response.home.Data
import com.rsz.foodapp.ui.home.popular.PopularHomeFragment
import com.rsz.foodapp.ui.home.recommended.RecomendedHomeFragment
import kotlinx.android.synthetic.main.item_home_vertical.view.*

class NewTasteHomeAdapter(
    private val listData: List<Data>,
    private val itemAdapterCallback: ItemAdapterCallback
    ) : RecyclerView.Adapter<NewTasteHomeAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewTasteHomeAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_home_vertical, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewTasteHomeAdapter.ViewHolder, position: Int) {
        holder.bind(listData[position], itemAdapterCallback)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    class ViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView){
        fun bind(data : Data, itemAdapterCallback : ItemAdapterCallback){
            itemView.apply {
                tv_home_food_title_vertical.text = data.name
                tv_home_food_price_vertical.formatPrice(data.price.toString())
                rb_home_food_rating_vertical.rating = data.rate.toFloat() ?: 0f

                Glide.with(context)
                    .load(data.picturePath)
                    .into(iv_home_food_vertical)

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