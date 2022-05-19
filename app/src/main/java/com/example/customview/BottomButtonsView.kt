package com.example.customview

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.customview.databinding.PartButtonsBinding

enum class BottomButtonsAction {
    POSITIVE, NEGATIVE
}

typealias OnBottomsButtonsActionListener = (BottomButtonsAction) -> Unit

class BottomButtonsView(
    context: Context,
    attr: AttributeSet?,
    defStyleAttr: Int,
    defStyleRes: Int,
) : ConstraintLayout(context, attr, defStyleAttr, defStyleRes) {

    private val binding: PartButtonsBinding
    private var listener: OnBottomsButtonsActionListener? = null
    var isProgressMode: Boolean = false
        set(value) {
            field = value
            with(binding) {
                if (value) {
                    positiveBtn.visibility = View.INVISIBLE
                    negativeBtn.visibility = View.INVISIBLE
                    progressBar.visibility = View.VISIBLE
                } else {
                    positiveBtn.visibility = View.VISIBLE
                    negativeBtn.visibility = View.VISIBLE
                    progressBar.visibility = View.GONE
                }
            }
        }

    constructor(context: Context, attr: AttributeSet?, defStyleAttr: Int) : this(
        context,
        attr,
        defStyleAttr,
        R.style.PartButtonStyle
    )

    constructor(context: Context, attr: AttributeSet?) : this(
        context,
        attr,
        R.attr.bottomButtonsStyleAttr
    )

    constructor(context: Context) : this(context, null)

    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.part_buttons, this, true)
        binding = PartButtonsBinding.bind(this)
        initializeAttributes(attr, defStyleAttr, defStyleRes)
        initListeners()
    }

    private fun initializeAttributes(attr: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) {
        if (attr == null) return
        val typedArray = context.obtainStyledAttributes(
            attr,
            R.styleable.BottomButtonsView,
            defStyleAttr,
            defStyleRes
        )

        with(binding) {
            val posBtnText = typedArray.getString(R.styleable.BottomButtonsView_btnPositiveText)
            setButtonPositiveText(posBtnText)
            val negBtnText = typedArray.getString(R.styleable.BottomButtonsView_btnNegativeText)
            setButtonNegativeText(negBtnText)
            val posBtnColor = typedArray.getColor(
                R.styleable.BottomButtonsView_btnPositiveBackgroundColor,
                Color.BLACK
            )
            positiveBtn.backgroundTintList = ColorStateList.valueOf(posBtnColor)
            val negBtnColor = typedArray.getColor(
                R.styleable.BottomButtonsView_btnNegativeBackgroundColor,
                Color.WHITE
            )
            negativeBtn.backgroundTintList = ColorStateList.valueOf(negBtnColor)

        }
        isProgressMode = typedArray.getBoolean(R.styleable.BottomButtonsView_progressMode, false)
        typedArray.recycle()
    }

    private fun initListeners() {
        binding.positiveBtn.setOnClickListener {
            this.listener?.invoke(BottomButtonsAction.POSITIVE)
        }
        binding.negativeBtn.setOnClickListener {
            this.listener?.invoke(BottomButtonsAction.NEGATIVE)
        }
    }

    fun setListener(listener: OnBottomsButtonsActionListener) {
        this.listener = listener
    }

    fun setButtonPositiveText(text: String?) {
        binding.positiveBtn.text = text ?: "ok"
    }

    fun setButtonNegativeText(text: String?) {
        binding.negativeBtn.text = text ?: "no"
    }
}