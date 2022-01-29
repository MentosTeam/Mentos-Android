package com.mentos.mentosandroid.ui.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mentos.mentosandroid.databinding.ItemMajorDetailBinding
import com.mentos.mentosandroid.util.setMentosImg17

class ProfileMajorDetailRVAdapter :
    RecyclerView.Adapter<ProfileMajorDetailRVAdapter.MajorDetailViewHolder>() {

    var majorDetailList = mutableListOf<MajorDetail>()

    inner class MajorDetailViewHolder(
        val binding: ItemMajorDetailBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(currentMajorDetail: MajorDetail) {
            binding.itemMajorDetailTitleTv.text = currentMajorDetail.title
            binding.itemMajorDetailMentosIv.setMentosImg17(currentMajorDetail.category)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MajorDetailViewHolder {
        val binding =
            ItemMajorDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MajorDetailViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MajorDetailViewHolder, position: Int) {
        holder.bind(majorDetailList[position])
    }

    override fun getItemCount(): Int {
        return majorDetailList.size
    }
}
data class MajorDetail(
    val category: Int,
    val title: String
)