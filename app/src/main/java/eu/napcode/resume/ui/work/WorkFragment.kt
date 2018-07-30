package eu.napcode.resume.ui.work

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
import kotlinx.android.synthetic.main.fragment_work.*
import javax.inject.Inject

class WorkFragment: Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var workViewModel: WorkViewModel

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)

        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_work, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        workRecyclerView.layoutManager = LinearLayoutManager(context)

        workViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(WorkViewModel::class.java)

        workViewModel.works.observe(this, Observer { works ->
            workRecyclerView.adapter = WorkAdapter(works, context!!)
        })
    }
}
