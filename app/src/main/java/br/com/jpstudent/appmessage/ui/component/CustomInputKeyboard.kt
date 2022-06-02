package br.com.jpstudent.appmessage.ui.component

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import br.com.jpstudent.appmessage.R
import br.com.jpstudent.appmessage.databinding.CustomInputKeyboardBinding

class CustomInputKeyboard @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0

) : ConstraintLayout(
    context,
    attrs,
    defStyleAttr
) {
    private val binding: CustomInputKeyboardBinding by lazy {
        CustomInputKeyboardBinding.inflate(LayoutInflater.from(context), this, true)
    }

    var text : String
    get() = binding.keyboard.editText?.text.toString()
    set(value) {
        binding.keyboard.editText?.setText(value)
    }

    var hint: String
        get() = binding.keyboard.hint.toString()
        set(value) {
            binding.keyboard.hint = value
        }

    var icon: Drawable?
        get() = binding.icon.drawable
        set(value) {
            binding.icon.setImageDrawable(value)
        }

    init {
        val atributes =
            context.obtainStyledAttributes(attrs, R.styleable.CustomInputKeyboard, defStyleAttr, 0)

        hint = atributes.getString(R.styleable.CustomInputKeyboard_hint_edt) ?: ""

        icon = ResourcesCompat.getDrawable(
            context.resources, atributes.getResourceId(
                R.styleable.CustomInputKeyboard_icon_keyboard,
                0
            ), null
        )

    }

    fun setOnIconClickListener(click : (String)->Unit){
        binding.icon.setOnClickListener {
            click.invoke(text)
            text = ""
        }
    }

}