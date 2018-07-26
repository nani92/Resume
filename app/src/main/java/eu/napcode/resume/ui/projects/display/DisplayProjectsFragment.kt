package eu.napcode.resume.ui.projects.display

import android.app.ActivityOptions
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.AndroidSupportInjection
import eu.napcode.resume.R
import eu.napcode.resume.model.Project
import eu.napcode.resume.model.ProjectType
import eu.napcode.resume.ui.project_details.ProjectActivity
import eu.napcode.resume.utils.getProjectTypeString
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.activity_project_details.view.*
import kotlinx.android.synthetic.main.fragment_display_projects.*
import kotlinx.android.synthetic.main.fragment_display_projects_empty.*
import kotlinx.android.synthetic.main.item_project.view.*
import javax.inject.Inject
import android.util.Pair as AndroidPair

class DisplayProjectsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var projectsViewModel: DisplayProjectsViewModel

    companion object {
        private const val ARG_PROJECT_TYPE = "type"

        fun newInstance(type: ProjectType): DisplayProjectsFragment {
            val args = Bundle()
            args.putSerializable(ARG_PROJECT_TYPE, type)

            val fragment = DisplayProjectsFragment()
            fragment.arguments = args

            return fragment
        }
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)

        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_display_projects, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        projectsRecyclerView.layoutManager = LinearLayoutManager(context)

        projectsViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(DisplayProjectsViewModel::class.java)

        projectsViewModel
                .getProjects(getProjectType())
                .observe(this, Observer { projects ->

                    if (projects!!.isEmpty()) {
                        displayNoProjects()
                    } else {
                        displayProjects(projects)
                    }
                })
    }

    private fun getProjectType(): ProjectType {
        return arguments!!.getSerializable(ARG_PROJECT_TYPE) as ProjectType
    }

    private fun displayNoProjects() {
        emptyLayout.visibility = View.VISIBLE
        noProjectsTextView.text = context!!.getString(
                R.string.no_projects,
                getProjectTypeString(context!!, getProjectType()))
    }

    private fun displayProjects(projects: List<Project>) {
        emptyLayout.visibility = View.GONE
        projectsRecyclerView.adapter = DisplayProjectsAdapter(projects) {
            project: Project, view: View -> showProjectDetailsActivity(project, view)
        }
    }

    private fun showProjectDetailsActivity(project: Project, view: View) {
        var intent = Intent(context, ProjectActivity::class.java)
        intent.putExtra(ProjectActivity.ARG_PROJECT, project)

        var titlePair = AndroidPair<View, String>(view.listProjectNameTextView, getString(R.string.anim_proj_title))
        var imagePair = AndroidPair<View, String>(view.listProjectImageView, getString(R.string.anim_proj_image))
        val transitionActivityOptions = ActivityOptions
                .makeSceneTransitionAnimation(activity, titlePair, imagePair)

        startActivity(intent, transitionActivityOptions.toBundle())
    }
}