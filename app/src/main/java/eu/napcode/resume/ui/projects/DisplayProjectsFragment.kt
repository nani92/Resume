package eu.napcode.resume.ui.projects

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
import eu.napcode.resume.model.ProjectType
import kotlinx.android.synthetic.main.fragment_display_projects.*
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
                .getProjects(arguments!!.getSerializable(ARG_PROJECT_TYPE) as ProjectType)
                .observe(this, Observer { projects ->
                    projectsRecyclerView.adapter = ProjectsAdapter(projects)
                })
    }
}