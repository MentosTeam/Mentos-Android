package com.mentos.mentosandroid.ui.otherprofile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mentos.mentosandroid.data.response.SearchMentor
import com.mentos.mentosandroid.databinding.ItemMajorDetailBinding
import com.mentos.mentosandroid.ui.search.SearchDetailDialog.Companion.FROM_OTHER_PROFILE
import com.mentos.mentosandroid.util.MentosImgUtil.setMentosImg17
import com.mentos.mentosandroid.util.navigateWithData

class MentorProfilePostRVAdapter :
    RecyclerView.Adapter<MentorProfilePostRVAdapter.MajorDetailViewHolder>() {

    var majorDetailList = mutableListOf<SearchMentor>()

    inner class MajorDetailViewHolder(
        val binding: ItemMajorDetailBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(currentMajorDetail: SearchMentor) {
            binding.itemMajorDetailTitleTv.text = currentMajorDetail.postTitle
            binding.itemMajorDetailMentosIv.setMentosImg17(currentMajorDetail.majorCategoryId)

            binding.itemMajorDetailLayout.setOnClickListener {
                it.navigateWithData(
                    OneMentorProfileFragmentDirections.actionOneMentorProfileFragmentToSearchDetailDialog(
                        postMento = currentMajorDetail,
                        from = FROM_OTHER_PROFILE
                    )
                )
            }
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