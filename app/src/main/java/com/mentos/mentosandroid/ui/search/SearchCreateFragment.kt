package com.mentos.mentosandroid.ui.search

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mentos.mentosandroid.databinding.FragmentSearchCreateBinding
import com.mentos.mentosandroid.util.KeyBoardUtil
import com.mentos.mentosandroid.util.MentosCategoryDialog
import com.mentos.mentosandroid.util.popBackStack
import com.mentos.mentosandroid.util.MentosImgUtil.setMentosImg17
import java.io.ByteArrayOutputStream

class SearchCreateFragment : Fragment() {
    private lateinit var binding: FragmentSearchCreateBinding
    private val searchViewModel by viewModels<SearchViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchCreateBinding.inflate(inflater, container, false)
        binding.viewModel = searchViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        searchViewModel.resetIsRegister()
        searchViewModel.resetImage()
        searchViewModel.setCategory(false)
        setMentosBtnClickListener()
        setNewPhotoClickListener()
        setBtnBackClickListener()
        hideKeyBoard()
        setEtScroll()
        setImageObserve()
        setIsRegisterObserve()
        return binding.root
    }

    private fun setMentosBtnClickListener() {
        binding.searchCreateMentosIv.setOnClickListener {
            MentosCategoryDialog { category ->
                binding.searchCreateMentosIv.setMentosImg17(category)
                when (category) {
                    -1 -> searchViewModel.setCategory(false)
                    else -> searchViewModel.setCategory(true)
                }
            }.show(childFragmentManager, "SELECT_MENTO_POST")
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
        binding.searchCreateNewPhotoIb.setOnClickListener {
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
            searchViewModel.setImage(requireNotNull(imageUri))
            setImgMultiPart()
        }
    }

    private fun setImgMultiPart() {
        val imageUri = requireNotNull(searchViewModel.image.value)
        val options = BitmapFactory.Options()
        val inputStream =
            requireContext().contentResolver.openInputStream(imageUri)
        val byteArrayOutputStream = ByteArrayOutputStream()
        BitmapFactory.decodeStream(inputStream, null, options)
            ?.compress(Bitmap.CompressFormat.JPEG, 20, byteArrayOutputStream)
        // val file = File(imageUri.toString())
    }

    private fun setBtnBackClickListener() {
        binding.searchBackIb.setOnClickListener {
            popBackStack()
        }
    }

    private fun hideKeyBoard() {
        binding.searchCreateTabLayout.setOnClickListener {
            KeyBoardUtil.hide(requireActivity())
        }
    }

    private fun setEtScroll() {
        binding.searchCreateContentEt.setTouchForScrollBars()
        binding.searchCreateTitleEt.setTouchForScrollBars()
    }

    private fun setImageObserve() {
        searchViewModel.image.observe(viewLifecycleOwner) {
            with(binding) {
                when (it) {
                    null -> {
                        searchCreateNewPhotoIb.visibility = View.VISIBLE
                        searchCreatePhotoIv.visibility = View.GONE
                        searchBtnDeletePhotoIb.visibility = View.GONE
                    }
                    else -> {
                        searchCreateNewPhotoIb.visibility = View.GONE
                        searchCreatePhotoIv.visibility = View.VISIBLE
                        searchBtnDeletePhotoIb.visibility = View.VISIBLE
                    }
                }
            }

        }
    }

    private fun setIsRegisterObserve() {
        searchViewModel.isRegister.observe(viewLifecycleOwner) { isRegister ->
            if (isRegister) {
                popBackStack()
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun EditText.setTouchForScrollBars() {
        setOnTouchListener { view, event ->
            view.parent.requestDisallowInterceptTouchEvent(true)
            when (event.action and MotionEvent.ACTION_MASK) {
                MotionEvent.ACTION_UP -> view.parent.requestDisallowInterceptTouchEvent(false)
            }
            false
        }
    }
}