package eu.napcode.resume.ui.projects.display

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
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
import eu.napcode.resume.utils.getProjectTypeString
import kotlinx.android.synthetic.main.fragment_display_projects.*
import kotlinx.android.synthetic.main.fragment_display_projects_empty.*
import javax.inject.Inject

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
        projectsRecyclerView.adapter = DisplayProjectsAdapter(projects)
    }
}