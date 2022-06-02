package br.com.jpstudent.appmessage.ui.component

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import br.com.jpstudent.appmessage.R
import br.com.jpstudent.appmessage.databinding.CustomProfileUserBinding

class CustomProfileUser @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    private val binding: CustomProfileUserBinding by lazy {
        CustomProfileUserBinding.inflate(LayoutInflater.from(context), this, true)
    }

    var textNameUser: String
        get() = binding.textNameUser.text.toString()
        set(value) {
            binding.textNameUser.text = value
        }

    var imgProfile: Drawable?
        get() = binding.imageProfile.drawable
        set(value) {
            binding.imageProfile.setImageDrawable(value)
        }

    init {
        val attributes =
            context.obtainStyledAttributes(attrs, R.styleable.CustomProfileUser, defStyleAttr, 0)

        textNameUser = attributes.getString(R.styleable.CustomProfileUser_text_name_user) ?: ""

        imgProfile = ResourcesCompat.getDrawable(
            context.resources, attributes.getResourceId(
                R.styleable.CustomProfileUser_img_profile,
                0
            ), null
        )
    }
}