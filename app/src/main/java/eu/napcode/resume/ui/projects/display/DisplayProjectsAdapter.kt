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
import kotlinx.android.synthetic.main.item_project.view.*

class DisplayProjectsAdapter(private val projects: List<Project>?) : RecyclerView.Adapter<DisplayProjectsAdapter.ViewHolder>() {

    private fun ViewGroup.inflate(layoutRes: Int): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, false)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, index: Int) = ViewHolder(viewGroup.inflate(R.layout.item_project))

    override fun getItemCount() = projects!!.size

    override fun onBindViewHolder(holder: ViewHolder, index: Int) = holder.bind(projects!![index])

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(project: Project) = with(itemView) {
            itemView.projectNameTextView.text = project.name
            itemView.projectDateTextView.text = getDateSpannableString(project.startMonth, project.startYear)

            manageCompanyDisplaying(context, project)
        }

        private fun manageCompanyDisplaying(context: Context, project: Project) {

            if (project.type == COMMERCIAL) {
                itemView.companyTextView.text = context.getString(R.string.commercial_project_at, project.company)
                itemView.companyTextView.visibility = VISIBLE
            } else {
                itemView.companyTextView.visibility = GONE
            }
        }
    }
}