package eu.napcode.resume.utils

import android.graphics.Typeface
import android.os.Build
import android.text.SpannableString
import android.text.style.StyleSpan
import java.text.SimpleDateFormat
import java.time.Month
import java.time.format.TextStyle
import java.util.*


fun getDateSpannableString(month: Int?, year: Int?): SpannableString? {
    var monthSpannableString: SpannableString? = null

    if (month != null) {
        var monthString = getMonthName(month)
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

private fun getMonthName(month: Int): String {

    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        Month.of(month).getDisplayName(TextStyle.FULL_STANDALONE, Locale.getDefault())
    } else {
        val date = Date()
        date.month = month - 1
        val dateFormat = SimpleDateFormat("LLLL", Locale.getDefault())
        dateFormat.format(date)
    }
}

fun getFullDatesSpannable(startMonth: Int?, startYear: Int?, endMonth: Int?, endYear: Int?): SpannableString? {
    var startSpannableString = getDateSpannableString(startMonth, startYear)
    var endSpannableString = getDateSpannableString(endMonth, endYear)

    return when {
        startSpannableString == null -> endSpannableString

        endSpannableString == null -> startSpannableString

        else -> SpannableString("$startSpannableString - $endSpannableString")
    }
}