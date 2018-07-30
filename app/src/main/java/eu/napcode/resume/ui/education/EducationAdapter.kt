package eu.napcode.resume.ui.education

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import eu.napcode.resume.R
import eu.napcode.resume.model.Education
import kotlinx.android.synthetic.main.item_education.view.*

class EducationAdapter(private val educations: List<Education>?, public val context: Context) : RecyclerView.Adapter<EducationAdapter.ViewHolder>() {

    fun ViewGroup.inflate(layoutRes: Int): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, false)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, index: Int) = ViewHolder(viewGroup.inflate(R.layout.item_education))

    override fun getItemCount() = educations!!.size

    override fun onBindViewHolder(holder: ViewHolder, index: Int) = holder.bind(educations!![index])

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(education: Education) = with(itemView) {
            var educationStyler = EducationStyler(context, education)

            itemView.educationTextView.text = educationStyler.getEducationSpannable()
            itemView.educationDatesTextView.text = educationStyler.getEducationDatesSpannable()

            if (education.title.isNullOrBlank()) {
                educationTitleTextView.visibility = GONE
            } else{
                educationTitleTextView.visibility = VISIBLE
                educationTitleTextView.text = education.title
            }

            var imageIdName = context.getString(R.string.education_image_id_name, education.id)
            var imageId = context.resources.getIdentifier(imageIdName, "drawable", context.packageName)

            if ( imageId != 0) {
                itemView.educationImageView.setImageDrawable(context.getDrawable(imageId))
            }
        }
    }

}