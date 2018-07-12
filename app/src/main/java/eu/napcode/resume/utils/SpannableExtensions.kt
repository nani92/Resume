package eu.napcode.resume.utils

import android.text.SpannableString
import android.text.Spanned

fun SpannableString.spanWith(target: String, apply: SpannableBuilder.() -> Unit) : SpannableString {
    val builder = SpannableBuilder()
    apply(builder)

    val start = this.indexOf(target)
    val end =  start + target.length

    setSpan(builder.what, start, end, builder.flags)

    return this
}

class SpannableBuilder {
    lateinit var what: Any
    var flags: Int = Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
}