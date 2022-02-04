package com.mentos.mentosandroid.ui.account

import android.content.Intent
import android.graphics.Bitmap
import android.Manifest
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.activity.result.ActivityResult
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mentos.mentosandroid.databinding.FragmentAccountPhotoBinding
import com.mentos.mentosandroid.ui.main.MainActivity
import java.io.ByteArrayOutputStream

class AccountPhotoFragment : Fragment() {
    private lateinit var binding: FragmentAccountPhotoBinding
    private val accountViewModel by viewModels<AccountViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAccountPhotoBinding.inflate(inflater, container, false)
        binding.viewModel = accountViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        setBtnClickListener()
        setNewPhotoClickListener()
        return binding.root
    }

    private fun setBtnClickListener() {
        binding.accountPhotoBtnComplete.setOnClickListener {
            startActivity(Intent(requireContext(), MainActivity::class.java))
            requireActivity().finish()
        }

        binding.accountPhotoBtnNextTv.setOnClickListener {
            startActivity(Intent(requireContext(), MainActivity::class.java))
            requireActivity().finish()
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
            accountViewModel.setImage(requireNotNull(imageUri))
            Log.d("사진", accountViewModel.image.toString())
            setImgMultiPart()
        }
    }

    private fun setImgMultiPart() {
        val imageUri = requireNotNull(accountViewModel.image.value)
        val options = BitmapFactory.Options()
        val inputStream =
            requireContext().contentResolver.openInputStream(imageUri)
        val byteArrayOutputStream = ByteArrayOutputStream()
        BitmapFactory.decodeStream(inputStream, null, options)
            ?.compress(Bitmap.CompressFormat.JPEG, 20, byteArrayOutputStream)
        // val file = File(imageUri.toString())
    }
}