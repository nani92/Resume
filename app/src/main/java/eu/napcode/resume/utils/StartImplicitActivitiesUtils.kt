package eu.napcode.resume.utils

import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_SENDTO
import android.net.Uri
import eu.napcode.resume.R

fun startSendMailActivity(context: Context, mail: String) {
    val uri = Uri.parse("mailto:$mail?subject=${context.getString(R.string.mail_subject)}")

    context.startActivity(Intent(ACTION_SENDTO, uri))
}

fun startPlayStoreActivity(context: Context, playStore: String) {

    try {
        context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://dev?id=$playStore")))
    } catch (anfe: android.content.ActivityNotFoundException) {
        context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/dev?id=$playStore")))
    }
}

fun startWebActivity(context: Context, webAddress: String) {
    var intent = Intent(Intent.ACTION_VIEW, Uri.parse(webAddress))
    context.startActivity(intent)
}