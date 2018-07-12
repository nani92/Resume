package eu.napcode.resume.ui.education

import android.content.Context
import android.graphics.Typeface
import android.support.v4.content.ContextCompat
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.TextUtils
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import eu.napcode.resume.R
import eu.napcode.resume.model.Education
import eu.napcode.resume.utils.spanWith
import java.text.DateFormatSymbols
import java.time.Month

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

    fun getDatesSpannable(): SpannableString? {
        var startSpannableString = getDateSpannableString(education.startMonth, education.startYear)
        var endSpannableString = getDateSpannableString(education.endMonth, education.endYear)

        return when {
            startSpannableString == null -> endSpannableString

            endSpannableString == null -> startSpannableString

            else -> SpannableString("$startSpannableString - $endSpannableString")
        }
    }

    private fun getDateSpannableString(month: Int?, year: Int?): SpannableString? {
        var monthSpannableString: SpannableString? = null

        if (month != null) {
            var monthString = DateFormatSymbols().months[month - 1]
            monthSpannableString = SpannableString(monthString)
                    .spanWith(monthString) {
                        what = StyleSpan(Typeface.ITALIC)
                    }
        }

        return when {
            year == null -> return null

            monthSpannableString == null -> SpannableString(year.toString())

            else -> SpannableString("$monthSpannableString $year")
        }
    }
}
