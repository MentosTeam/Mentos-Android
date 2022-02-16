package com.mentos.mentosandroid.ui.setting

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.databinding.FragmentChangeProfileImgBinding
import com.mentos.mentosandroid.util.MultiPartResolver
import com.mentos.mentosandroid.util.makeToast
import com.mentos.mentosandroid.util.popBackStack

class ChangeProfileImgFragment : Fragment() {
    private lateinit var binding: FragmentChangeProfileImgBinding
    private val settingViewModel by activityViewModels<SettingViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChangeProfileImgBinding.inflate(inflater, container, false)
        binding.settingViewModel = settingViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        setBtnBackClickListener()
        setNewPhotoClickListener()
        setSuccessImageObserve()
        initImage()
        return binding.root

    }

    private fun initImage() {
        Glide.with(this)
            .load(settingViewModel.image.value)
            .into(binding.changeImgIv)
    }

    private fun setBtnBackClickListener() {
        binding.changeImgBackIb.setOnClickListener {
            popBackStack()
        }
    }

    private fun setNewPhotoClickListener() {
        val requestPermissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
                if (isGranted) {
                    val intent = Intent(Intent.ACTION_PICK)
                    intent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                    intent.type = "image/*"
                    getImage.launch(intent)
                }
            }
        binding.changeImgLayout.setOnClickListener {
            requestPermissionLauncher.launch(
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
        }
    }

    private val getImage = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.data != null) {
            val imageUri = result.data?.data
            settingViewModel.setImage(requireNotNull(imageUri))
            settingViewModel.imageMultiPart =
                MultiPartResolver(requireContext()).createImgMultiPart(
                    requireNotNull(settingViewModel.image.value)
                )
            Glide.with(this)
                .load(settingViewModel.image.value)
                .into(binding.changeImgIv)
        }
    }

    private fun setSuccessImageObserve() {
        settingViewModel.isSuccessImage.observe(viewLifecycleOwner) { isSuccess ->
            when (isSuccess) {
                true -> {
                    makeToast(requireContext(), R.string.toast_setting_img_success)
                    popBackStack()
                    settingViewModel.initSuccessImage()
                }
                false -> {
                    makeToast(requireContext(), R.string.toast_setting_img_fail)
                    popBackStack()
                    settingViewModel.initSuccessImage()
                }
            }
        }
    }
}