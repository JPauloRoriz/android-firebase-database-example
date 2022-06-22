package br.com.jpstudent.appmessage.ui.adapter.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.jpstudent.appmessage.R
import br.com.jpstudent.appmessage.model.entity.Post
import br.com.jpstudent.appmessage.ui.component.CustomProfileUser

class UserPostsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val tvMessagePost: TextView by lazy { itemView.findViewById(R.id.tv_message_post) }
    private val profile: CustomProfileUser by lazy { itemView.findViewById(R.id.profile) }

    fun bind(
        post: Post,
        clickText: ((Post) -> Unit)?,
        clickProfile: ((String) -> Unit)?
    ) {
        tvMessagePost.text = post.text
        profile.textNameUser = post.nameUser

        tvMessagePost.setOnClickListener {
            clickText?.invoke(post)
        }
        profile.setOnClickListener {
            clickProfile?.invoke(post.idUser)
        }
    }
}