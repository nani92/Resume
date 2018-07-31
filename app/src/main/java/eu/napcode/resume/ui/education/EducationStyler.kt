package eu.napcode.resume.ui.education

import android.content.Context
import android.graphics.Typeface
import android.os.Build
import android.support.v4.content.ContextCompat
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import eu.napcode.resume.R
import eu.napcode.resume.model.Education
import eu.napcode.resume.utils.getDateSpannableString
import eu.napcode.resume.utils.getFullDatesSpannable
import eu.napcode.resume.utils.spanWith
import java.text.SimpleDateFormat
import java.time.Month
import java.time.format.TextStyle
import java.util.*

class EducationStyler(var context: Context, val education: Education) {
    var color = ContextCompat.getColor(context, R.color.colorPrimary)

    fun getEducationSpannable(): SpannableString {

        return when {

            education.field.isNullOrBlank()
                    && education.school.isNotBlank() ->

                SpannableString(education.school).spanWith(education.school) {
                    what = ForegroundColorSpan(color)
                }

            education.field!!.isNotBlank()
                    && education.school.isNotBlank() ->
                getEducationWithFieldAndSchoolSpannable(education.school, education.field)

            else -> SpannableString(context.getString(R.string.education_problem))
        }
    }

    private fun getEducationWithFieldAndSchoolSpannable(school: String, field: String): SpannableString {
        return SpannableString(context.getString(R.string.education_field_at_school, field, school))
                .spanWith(school) {
                    what = ForegroundColorSpan(color)
                }.spanWith(field) {
                    what = ForegroundColorSpan(color)
                }.spanWith(field) {
                    what = StyleSpan(Typeface.BOLD)
                }
    }

    fun getEducationDatesSpannable(): SpannableString? {
        return getFullDatesSpannable(education.startMonth, education.startYear, education.endMonth, education.endYear)
    }
}
