package com.mentos.mentosandroid.ui.otheraccount

import android.content.Intent
import android.Manifest
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import androidx.activity.result.ActivityResult
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.mentos.mentosandroid.databinding.FragmentOtherAccountPhotoBinding
import com.mentos.mentosandroid.ui.main.MainActivity
import com.mentos.mentosandroid.util.MultiPartResolver
import com.mentos.mentosandroid.data.local.SharedPreferenceController

class OtherAccountPhotoFragment : Fragment() {
    private lateinit var binding: FragmentOtherAccountPhotoBinding
    private val otherAccountViewModel by activityViewModels<OtherAccountViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOtherAccountPhotoBinding.inflate(inflater, container, false)
        binding.viewModel = otherAccountViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        setNewPhotoClickListener()
        setIsSuccessCreateObserve()

        return binding.root
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
        binding.accountPhotoImgLayout.setOnClickListener {
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
            otherAccountViewModel.setImage(requireNotNull(imageUri))
            otherAccountViewModel.imageMultiPart =
                MultiPartResolver(requireContext()).createImgMultiPart(
                    requireNotNull(otherAccountViewModel.image.value)
                )
            Glide.with(this)
                .load(otherAccountViewModel.image.value)
                .into(binding.accountPhotoImgIv)
        }
    }

    private fun setIsSuccessCreateObserve() {
        otherAccountViewModel.isSuccessCreate.observe(viewLifecycleOwner) { isSuccess ->
            if (isSuccess) {
                when (otherAccountViewModel.selectedState.value) {
                    2 -> SharedPreferenceController.setNowState(1)
                    1 -> SharedPreferenceController.setNowState(0)
                }
                startActivity(Intent(requireContext(), MainActivity::class.java))
                requireActivity().finish()
            }
        }
    }
}