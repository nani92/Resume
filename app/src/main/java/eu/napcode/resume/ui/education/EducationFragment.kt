package eu.napcode.resume.ui.education

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
import kotlinx.android.synthetic.main.fragment_education.*
import javax.inject.Inject

class EducationFragment: Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var educationViewModel: EducationViewModel

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)

        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_education, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        educationRecyclerView.layoutManager = LinearLayoutManager(context)

        educationViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(EducationViewModel::class.java)

        educationViewModel.educations.observe(this, Observer { educations ->
            educationRecyclerView.adapter = EducationAdapter(educations)
        })
    }

    fun setupRecyclerView() {
        educationRecyclerView.layoutManager = LinearLayoutManager(context)
    }
}
