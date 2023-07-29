package com.lautung.mvidemo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lautung.mvidemo.databinding.ItemWallpaperRvBinding
import com.lautung.mvidemo.model.Vertical

/**
 * @Auther: lautung
 * @datetime: 2023/7/29
 * @desc:
 */
class WallpaperAdapter(private val verticals: ArrayList<Vertical>) :
    RecyclerView.Adapter<WallpaperAdapter.ViewHolder>() {


    fun addData(data: List<Vertical>) {
        val position = this.verticals.size
        verticals.addAll(data)
        notifyItemInserted(position)
    }

    class ViewHolder(val binding: ItemWallpaperRvBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemWallPaperRvBinding =
            ItemWallpaperRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemWallPaperRvBinding)
    }

    override fun getItemCount(): Int = verticals.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView).load(verticals[position].preview)
            .into(holder.binding.ivWallPaper)
    }
}