package eu.napcode.resume.ui.projects

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import eu.napcode.resume.R
import eu.napcode.resume.model.Project

class ProjectsAdapter(private val projects: List<Project>?) : RecyclerView.Adapter<ProjectsAdapter.ViewHolder>() {

    private fun ViewGroup.inflate(layoutRes: Int): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, false)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, index: Int) = ViewHolder(viewGroup.inflate(R.layout.item_project))

    override fun getItemCount() = projects!!.size

    override fun onBindViewHolder(holder: ViewHolder, index: Int) = holder.bind(projects!![index])

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(project: Project) = with(itemView) {

        }
    }

}