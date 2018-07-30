package eu.napcode.resume.ui.projects.display

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import eu.napcode.resume.R
import eu.napcode.resume.model.Project
import eu.napcode.resume.model.ProjectType.COMMERCIAL
import eu.napcode.resume.utils.getDateSpannableString
import kotlinx.android.synthetic.main.activity_project_details.view.*
import kotlinx.android.synthetic.main.item_education.view.*
import kotlinx.android.synthetic.main.item_project.view.*

class DisplayProjectsAdapter(private val projects: List<Project>?, val listener: (Project, View) -> Unit) : RecyclerView.Adapter<DisplayProjectsAdapter.ViewHolder>() {

    private fun ViewGroup.inflate(layoutRes: Int): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, false)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, index: Int) = ViewHolder(viewGroup.inflate(R.layout.item_project))

    override fun getItemCount() = projects!!.size

    override fun onBindViewHolder(holder: ViewHolder, index: Int) = holder.bind(projects!![index], listener)

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(project: Project, listener: (Project, View) -> Unit) = with(itemView) {
            itemView.listProjectNameTextView.text = project.name
            itemView.listProjectDateTextView.text = getDateSpannableString(project.startMonth, project.startYear)

            manageCompanyDisplaying(context, project)

            var imageIdName = context.getString(R.string.project_image_id_name, project.id)
            var imageId = context.resources.getIdentifier(imageIdName, "drawable", context.packageName)

            if ( imageId != 0) {
                itemView.listProjectImageView.setImageDrawable(context.getDrawable(imageId))
            } else{
                itemView.listProjectImageView.setImageDrawable(context.getDrawable(R.drawable.ic_project_placeholder))
            }

            setOnClickListener { listener(project, itemView) }
        }

        private fun manageCompanyDisplaying(context: Context, project: Project) {

            if (project.type == COMMERCIAL) {
                itemView.listCompanyTextView.text = context.getString(R.string.commercial_project_at, project.company)
                itemView.listCompanyTextView.visibility = VISIBLE
            } else {
                itemView.listCompanyTextView.visibility = GONE
            }
        }
    }
}