package br.com.jpstudent.appmessage.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.jpstudent.appmessage.R
import br.com.jpstudent.appmessage.model.entity.Post
import br.com.jpstudent.appmessage.ui.adapter.viewholder.OwnPostsViewHolder
import br.com.jpstudent.appmessage.ui.adapter.viewholder.UserPostsViewHolder

class ProfileUserAdapter(
    private val idUser: String?
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var posts: List<Post> = mutableListOf()
    var clickText: ((Post) -> Unit)? = null
    var clickProfile: ((String) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == USERSPOSTS) {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_user_posts, parent, false)
            UserPostsViewHolder(view)
        } else {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_own_post, parent, false)
            OwnPostsViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == USERSPOSTS) {
            (holder as? (UserPostsViewHolder))?.bind(posts[position], clickText, clickProfile)
        } else {
            (holder as? (OwnPostsViewHolder))?.bind(posts[position], clickText, clickProfile)
        }
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (posts[position].idUser == idUser) OWNPOSTS else USERSPOSTS
    }

    fun refreshList(posts: List<Post>) {
        this.posts = posts
        notifyDataSetChanged()
    }

    companion object {
        private const val USERSPOSTS = 0
        private const val OWNPOSTS = 1
    }


}