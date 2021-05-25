package com.example.mvvmstarterapp.base

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmstarterapp.R

abstract class BaseDialogFragment<DB : ViewDataBinding, VM : BaseViewModel> : DialogFragment() {
   @get:LayoutRes
   protected abstract val layoutId: Int
   protected abstract val classTypeOfVM: Class<VM>
   lateinit var viewModel: VM
   lateinit var binding: DB
   private var isViewCreatedFirstTime = true

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)
      viewModel = ViewModelProvider(this).get(classTypeOfVM)
   }

   override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
      binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
      binding.lifecycleOwner = viewLifecycleOwner
      return binding.root
   }

   override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
      val dialog = super.onCreateDialog(savedInstanceState)
      if (dialog.window != null) {
         dialog.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
         dialog.setCancelable(false)
      }
      return dialog
   }


   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      init()
      setupObservers()
      initLocal()

      if (isViewCreatedFirstTime) {
         initStartRequest()
         isViewCreatedFirstTime = false
      }
   }

   open fun init() {}
   open fun initStartRequest() {}
   open fun setupObservers() {}

   private fun initLocal() {

   }

}