package com.mentos.mentosandroid.ui.search

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.databinding.FragmentSearchCreateBinding
import com.mentos.mentosandroid.util.*
import com.mentos.mentosandroid.util.MentosImgUtil.setMentosImg17

class SearchCreateFragment : Fragment() {
    private lateinit var binding: FragmentSearchCreateBinding
    private val searchViewModel by viewModels<SearchViewModel>()
    private val args by navArgs<SearchCreateFragmentArgs>()

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
        searchViewModel.setIsWritten(false)
        if (args.postMento != null) {
            initModifyView()
        }
        setMentosBtnClickListener()
        setNewPhotoClickListener()
        hideKeyBoard()
        setEtScroll()
        setIsWrittenObserve()
        setImageObserve()
        setIsRegisterObserve()
        return binding.root
    }

    private fun initModifyView() {
        with(searchViewModel) {
            setCreateTitle(args.postMento?.postTitle!!)
            setCreateContent(args.postMento?.postContents!!)
            setCategory(true)
        }
        // 사진 관련 수정 필요
        Glide.with(this)
            .load(args.postMento?.imageUrl)
            .into(binding.searchCreatePhotoIv)
        binding.searchCreateMentosIv.setMentosImg17(args.postMento?.majorCategoryId!!)
        binding.searchCreateBtnComplete.setText(R.string.search_create_modify_complete)
    }

    private fun setMentosBtnClickListener() {
        binding.searchCreateMentosIv.setOnClickListener {
            MentosCategoryDialog { category ->
                binding.searchCreateMentosIv.setMentosImg17(category)
                searchViewModel.createCategory.value = category
                when (category) {
                    -1 -> searchViewModel.setCategory(false)
                    else -> searchViewModel.setCategory(true)
                }
            }.show(childFragmentManager, "SELECT_MENTOR_POST")
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
            searchViewModel.imageMultiPart =
                MultiPartResolver(requireContext()).createImgMultiPart(
                    requireNotNull(searchViewModel.image.value)
                )
            Glide.with(this)
                .load(searchViewModel.image.value)
                .into(binding.searchCreatePhotoIv)
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

    private fun setIsWrittenObserve() {
        searchViewModel.isWritten.observe(viewLifecycleOwner) { isWritten ->
            binding.searchBackIb.setOnClickListener {
                if (isWritten) {
                    DialogUtil(5) {
                        popBackStack()
                    }.show(childFragmentManager, "search_create_stop_write")
                } else {
                    popBackStack()
                }
            }
        }
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
                Toast.makeText(requireContext(), "글 등록이 완료되었습니다.", Toast.LENGTH_SHORT)
                    .show()
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