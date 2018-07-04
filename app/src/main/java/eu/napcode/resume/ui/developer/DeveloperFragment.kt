package eu.napcode.resume.ui.developer

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Html
import android.text.Spanned
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.AndroidSupportInjection
import eu.napcode.resume.R
import kotlinx.android.synthetic.main.fragment_developer.*
import javax.inject.Inject
import android.content.Intent
import android.content.Intent.ACTION_SENDTO
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import kotlinx.android.synthetic.main.dev_contact.*


class DeveloperFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var developerViewModel: DeveloperViewModel

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)

        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_developer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        developerViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(DeveloperViewModel::class.java)

        developerViewModel.developer.observe(this, Observer { developer ->
            devNameTextView.text = getString(R.string.dev_name, developer!!.name, developer.surname)
            devRoleTextView.text = developer.role
            devSummaryTextView.text = getSummarySpanned(developer.summary)
            devHighlightsTextView.text = getHighlightsSpanned(developer.highlights)
            mailFAB.setOnClickListener { startSendMailActivity(developer.mail) }
            playstoreImageView.setOnClickListener { startPlayStoreActivity(developer.playStore) }
            githubImageView.setOnClickListener { startWebActivity("https://github.com/${developer.github}") }
            homeImageView.setOnClickListener { startWebActivity(developer.home) }
            gitlabImageView.setOnClickListener{ startWebActivity("https://gitlab.com/${developer.gitlab}")}
        })
    }

    private fun getSummarySpanned(summary: String): Spanned {

        return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            Html.fromHtml(getString(R.string.dev_summary, summary))
        } else {
            Html.fromHtml(getString(R.string.dev_summary, summary), Html.FROM_HTML_MODE_COMPACT)
        }
    }

    private fun getHighlightsSpanned(highlights: String): Spanned {

        return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            Html.fromHtml(getString(R.string.dev_highlights, getHighlightsBredString(highlights.split(";"))))
        } else {
            Html.fromHtml(getString(R.string.dev_highlights, getHighlightsULString(highlights.split(";"))),
                    Html.FROM_HTML_MODE_COMPACT)
        }
    }

    private fun getHighlightsULString(highlights: List<String>): String {
        return highlights.joinToString(
                prefix = "<ul><li>",
                separator = "</li><li>",
                postfix = "</li></ul>")
    }

    private fun getHighlightsBredString(highlights: List<String>) =
            highlights.joinToString(prefix = "- ", separator = "<br/>- ")

    private fun startSendMailActivity(mail: String) {
        val uri = Uri.parse("mailto:$mail")
                .buildUpon()
                .appendQueryParameter("subject", getString(R.string.mail_subject))
                .build()

        startActivity(Intent(ACTION_SENDTO, uri))
    }

    private fun startPlayStoreActivity(playStore: String) {

        try {
            startActivity(Intent(ACTION_VIEW, Uri.parse("market://dev?id=$playStore")))
        } catch (anfe: android.content.ActivityNotFoundException) {
            startActivity(Intent(ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/dev?id=$playStore")))
        }
    }

    private fun startWebActivity(webAddress: String) {
        var intent = Intent(ACTION_VIEW, Uri.parse(webAddress))
        startActivity(intent)
    }
}
