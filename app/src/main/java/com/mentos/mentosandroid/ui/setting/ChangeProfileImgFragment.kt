package com.mentos.mentosandroid.ui.setting

import android.Manifest
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.mentos.mentosandroid.databinding.FragmentChangeProfileImgBinding
import com.mentos.mentosandroid.util.MultiPartResolver
import com.mentos.mentosandroid.util.popBackStack
import java.io.ByteArrayOutputStream

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

//    private fun setImgMultiPart() {
//        val imageUri = requireNotNull(settingViewModel.image.value)
//        val options = BitmapFactory.Options()
//        val inputStream =
//            requireContext().contentResolver.openInputStream(imageUri)
//        val byteArrayOutputStream = ByteArrayOutputStream()
//        BitmapFactory.decodeStream(inputStream, null, options)
//            ?.compress(Bitmap.CompressFormat.JPEG, 20, byteArrayOutputStream)
//        // val file = File(imageUri.toString())
//    }

    private fun setSuccessImageObserve() {
        settingViewModel.isSuccessImage.observe(viewLifecycleOwner) { isSuccess ->
            when(isSuccess) {
                true -> {
                    // 등록 성공
                    Log.d("사진 변경", "isSuccessImage true")
                    Toast.makeText(requireContext(), "프로필 사진이 변경되었습니다!", Toast.LENGTH_SHORT).show()
                    popBackStack()
                    settingViewModel.initSuccessImage()
                }
                false -> {
                    Log.d("사진 변경", "isSuccessImage false")
                    Toast.makeText(requireContext(), "프로필 사진 변경을 실패했습니다", Toast.LENGTH_SHORT).show()
                    popBackStack()
                    settingViewModel.initSuccessImage()
                }
            }
        }
    }
}