package com.pvsb.customprogressbutton

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.pvsb.customprogressbutton.databinding.ProgressButtonBinding

class ProgressButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {


    private var title: String? = null
    private var loadingTitle: String? = null

    private val binding = ProgressButtonBinding.inflate(LayoutInflater.from(context), this, true)

    var state: ProgressButtonState = ProgressButtonState.Normal
        private set(value) {
            field = value
            refreshState()
        }

    init {
        setLayout(attrs)
        refreshState()
    }

    private fun setLayout(attrs: AttributeSet?) {
        attrs?.let { attrsSet ->
            val attributes = context.obtainStyledAttributes(attrsSet, R.styleable.ProgressButton)

            setBackgroundResource(R.drawable.primary_button_selector)

            val tittleResId = attributes.getResourceId(R.styleable.ProgressButton_progress_button_title, 0)

            if (tittleResId != 0) {
                title = context.getString(tittleResId)
            }

            val loadingTittleResId = attributes.getResourceId(R.styleable.ProgressButton_progress_button_loading_title, 0)

            if (loadingTittleResId != 0) {
                loadingTitle = context.getString(loadingTittleResId)
            }

            attributes.recycle()
        }
    }

    private fun refreshState() {
        isEnabled = state.isEnabled
        isClickable = state.isEnabled
        refreshDrawableState()

        binding.tvLabel.apply {
            isEnabled = state.isEnabled
            isClickable = state.isEnabled
        }

        binding.progressBar.visibility = state.progressVisibility

        when (state) {
            ProgressButtonState.Loading -> {
                binding.tvLabel.text = loadingTitle
            }
            ProgressButtonState.Normal -> {
                binding.tvLabel.text = title
            }
        }
    }

    fun setLoading() {
        state = ProgressButtonState.Loading
    }

    fun setNormal() {
        state = ProgressButtonState.Normal
    }

    sealed class ProgressButtonState(val isEnabled: Boolean, val progressVisibility: Int) {
        object Normal : ProgressButtonState(true, View.GONE)
        object Loading : ProgressButtonState(false, View.VISIBLE)
    }
}