package br.com.jpstudent.appmessage.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.jpstudent.appmessage.R
import br.com.jpstudent.appmessage.model.entity.Post
import br.com.jpstudent.appmessage.model.entity.User
import br.com.jpstudent.appmessage.ui.component.CustomProfileUser

class ProfileUserAdapter() :
    RecyclerView.Adapter<ProfileUserAdapter.ProfileUserViewHolder>() {
    private var posts: List<Post> = mutableListOf()
    var clickText: ((Post) -> Unit)? = null
    var clickProfile: ((String) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileUserViewHolder {
        return ProfileUserViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_user_posts, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ProfileUserViewHolder, position: Int) {
        holder.binding(posts[position])
    }

    override fun getItemCount() = posts.size

    fun refreshList(posts: List<Post>) {
        this.posts = posts
        notifyDataSetChanged()
    }


    inner class ProfileUserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvMessagePost: TextView by lazy { itemView.findViewById(R.id.tv_message_post) }
        private val profile: CustomProfileUser by lazy { itemView.findViewById(R.id.profile) }

        fun binding(post: Post) {

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
}