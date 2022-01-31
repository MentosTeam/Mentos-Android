package com.mentos.mentosandroid.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.databinding.FragmentPostListBinding
import com.mentos.mentosandroid.util.navigate
import com.mentos.mentosandroid.util.popBackStack

class PostListFragment: Fragment() {
    lateinit var binding: FragmentPostListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPostListBinding.inflate(inflater, container, false)

        setBtnBackClickListener()
        setBtnWriteClickListener()

        return binding.root
    }

    private fun setBtnBackClickListener() {
        binding.myPostBackIb.setOnClickListener {
            popBackStack()
        }
    }

    private fun setBtnWriteClickListener() {
        binding.myPostWriteIb.setOnClickListener {
            navigate(R.id.action_postListFragment_to_searchCreateFragment)
        }
    }
}